package com.rxandroidsample.ui.views.main;

import android.support.v4.app.FragmentManager;

import com.rxandroidsample.data.model.Ribot;
import com.rxandroidsample.presentation.presenter.MvpView;

import java.util.List;

/**
 * Created by anhndt on 5/26/16.
 */
public interface MainView extends MvpView{
    public void showLoading();
    public void dismissLoading();
    public void displayRibots(List<Ribot> ribots);
    public void showError(String error);
    public FragmentManager getSupportFragmentManager();
}
