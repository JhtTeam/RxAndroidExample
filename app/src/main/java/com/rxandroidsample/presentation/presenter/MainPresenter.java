package com.rxandroidsample.presentation.presenter;

import android.util.Log;

import com.rxandroidsample.data.model.Name;
import com.rxandroidsample.data.model.Profile;
import com.rxandroidsample.data.model.Ribot;
import com.rxandroidsample.ui.fragments.MainWorkerFragment;
import com.rxandroidsample.ui.interfaces.Data;
import com.rxandroidsample.ui.interfaces.OnObservableRetrievedListener;
import com.rxandroidsample.ui.views.main.MainView;

import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by anhndt on 5/26/16.
 */
public class MainPresenter extends BasePresenter<MainView> implements OnObservableRetrievedListener {
    private CompositeSubscription mSubscriptions;

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
        MainWorkerFragment.create(getView().getSupportFragmentManager(), this);
    }

    public void loadRibots() {

    }

    @Override
    public void onPause() {
        if (mSubscriptions != null) {
            mSubscriptions.unsubscribe();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mSubscriptions == null || mSubscriptions.isUnsubscribed()) {
            mSubscriptions = new CompositeSubscription();
        }
    }

    @Override
    public void onObservableRetrieved(Observable<List<Ribot>> observable, final @Data.Type String type) {
        mSubscriptions.add(observable.subscribe(new Subscriber<List<Ribot>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e.toString());
            }

            @Override
            public void onNext(List<Ribot> items) {
                //save to DB using Realm
                getView().displayRibots(items);

                if (type == Data.ONLINE) {
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    //transaction in here
                    realm.delete(Ribot.class);
                    realm.delete(Profile.class);
                    realm.delete(Name.class);

                    for (Ribot ribot : items) {
                        realm.copyToRealm(ribot);
                    }
                    realm.commitTransaction();
                }
            }
        }));
    }
}
