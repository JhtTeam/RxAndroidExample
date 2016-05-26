package com.rxandroidsample.util;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by anhndt on 5/26/16.
 */
public class RealmUtils {
    private static final String REALM_DB_NAME = "rxandroidsample.realm";

    private static RealmConfiguration getRealmConfiguration(Context context) {
        return new RealmConfiguration.Builder(context)
                .name(REALM_DB_NAME)
                .schemaVersion(1)
                .build();
    }
    public static void configRealm(Context context) {
        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
        Realm.setDefaultConfiguration(getRealmConfiguration(context));
    }
    public static void deleteRealm(Context context) {
        Realm.deleteRealm(getRealmConfiguration(context));
    }
}
