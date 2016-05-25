package com.rxandroidsample;

import android.app.Application;
import android.os.Build;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by anhndt on 5/19/16.
 */
public class MyApplication extends Application{
    @Override public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
