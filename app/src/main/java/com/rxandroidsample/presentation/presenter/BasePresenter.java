package com.rxandroidsample.presentation.presenter;

/**
 * Created by anhndt on 5/26/16.
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {
    private T mView;

    public T getView() {
        return mView;
    }
    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void dettachView(T view) {
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public void onResume() {

    }
    public void onPause() {
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
