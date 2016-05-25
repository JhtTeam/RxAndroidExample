package com.rxandroidsample.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.rxandroidsample.data.model.Profile;
import com.rxandroidsample.data.model.Ribot;
import com.rxandroidsample.data.remote.RibotService;
import com.rxandroidsample.ui.interfaces.OnObservableRetrievedListener;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by anhndt on 5/25/16.
 */
public class MainWorkerFragment extends Fragment{
    private static final String TAG_FRAGMENT = "MainWorkerFragment";
    public static Fragment create(FragmentManager fragmentManager) {
        Fragment f = fragmentManager.findFragmentByTag(TAG_FRAGMENT);
        if (f == null) {
            f = new MainWorkerFragment();
            fragmentManager.beginTransaction().add(f, TAG_FRAGMENT).commit();
        }
        return f;
    }

    private CompositeSubscription mSubscription = new CompositeSubscription();
    private PublishSubject mSubject = PublishSubject.create();
    private OnObservableRetrievedListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Observable<List<Ribot>> observable = RibotService.CREATOR.newRibotService().getRibots()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        mSubscription.add(observable.subscribe(mSubject));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnObservableRetrievedListener)getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onObservableRetrieved(mSubject.asObservable());
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
    }
}
