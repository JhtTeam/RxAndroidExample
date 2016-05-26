package com.rxandroidsample.ui.interfaces;

import com.rxandroidsample.data.model.Ribot;

import java.util.List;

import rx.Observable;

/**
 * Created by anhndt on 5/25/16.
 */
public interface OnObservableRetrievedListener {
    public void onObservableRetrieved(Observable<List<Ribot>> observable, @Data.Type String type);
}
