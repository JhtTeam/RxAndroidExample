package com.rxandroidsample.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.rxandroidsample.data.model.Ribot;
import com.rxandroidsample.data.remote.RibotService;
import com.rxandroidsample.ui.interfaces.Data;
import com.rxandroidsample.ui.interfaces.OnObservableRetrievedListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by anhndt on 5/25/16.
 */
public class MainWorkerFragment extends Fragment{
    private static final String TAG_FRAGMENT = "MainWorkerFragment";
    public static Fragment create(FragmentManager fragmentManager, OnObservableRetrievedListener listener) {
        MainWorkerFragment f = (MainWorkerFragment)fragmentManager.findFragmentByTag(TAG_FRAGMENT);
        if (f == null) {
            f = new MainWorkerFragment();
            fragmentManager.beginTransaction().add(f, TAG_FRAGMENT).commit();
        }
        f.setListener(listener);
        return f;
    }

    private CompositeSubscription mSubscription = new CompositeSubscription();
    private Subject mSubjectDataOnline = ReplaySubject.create();
    private Subject mSubjectDataDB = ReplaySubject.create();
    private OnObservableRetrievedListener mListener;
    private Realm mRealm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mRealm = Realm.getDefaultInstance();

        Observable<List<Ribot>> observableDB = mRealm.where(Ribot.class)
                .findAll().asObservable()
                .flatMap(ribots -> {
                    List<Ribot> results = new ArrayList<Ribot>();
                    results.addAll(ribots);
                    return Observable.just(results);
                })
                .observeOn(AndroidSchedulers.mainThread());

        Observable<List<Ribot>> observable = RibotService.CREATOR.newRibotService().getRibots()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        mSubscription.add(observableDB.subscribe(mSubjectDataDB));
        mSubscription.add(observable.subscribe(mSubjectDataOnline));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onObservableRetrieved(mSubjectDataDB.asObservable(), Data.DATABASE);
            mListener.onObservableRetrieved(mSubjectDataOnline.asObservable(), Data.ONLINE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
        mRealm.close();
    }

    public void setListener(OnObservableRetrievedListener mListener) {
        this.mListener = mListener;
    }
}
