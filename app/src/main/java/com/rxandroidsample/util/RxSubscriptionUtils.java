package com.rxandroidsample.util;

import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by anhndt on 5/25/16.
 */
public class RxSubscriptionUtils {
    public static CompositeSubscription createFrom(@Nullable CompositeSubscription source) {
        if (source == null) {
            source = new CompositeSubscription();
        }
        return source;
    }
    public static void safeUnsubscribeFrom(@Nullable CompositeSubscription source) {
        if (source != null) {
            source.unsubscribe();
        }
    }
}
