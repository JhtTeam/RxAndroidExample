package com.rxandroidsample.presentation.presenter;

/**
 * Created by anhndt on 5/26/16.
 */
public interface Presenter<V extends MvpView> {
    public void attachView(V view);
    public void dettachView(V view);
    public void onResume();
    public void onPause();
}
