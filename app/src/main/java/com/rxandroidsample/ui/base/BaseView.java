package com.rxandroidsample.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rxandroidsample.presentation.presenter.BasePresenter;
import com.rxandroidsample.presentation.presenter.MvpView;

/**
 * Created by anhndt on 5/26/16.
 */
public abstract class BaseView<T extends MvpView, V extends BasePresenter> extends AppCompatActivity{
    V mPresenter;

    public abstract V createPresenter();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
        mPresenter.attachView((T)this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        mPresenter = createPresenter();
        mPresenter.dettachView((T)this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.dettachView((T)this);
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    public V getPresenter() {
        return mPresenter;
    }
}
