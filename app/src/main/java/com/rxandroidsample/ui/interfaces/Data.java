package com.rxandroidsample.ui.interfaces;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

/**
 * Created by anhndt on 5/26/16.
 */
public interface Data {
    String DATABASE = "Database";
    String ONLINE = "Online";

    @StringDef({DATABASE, ONLINE})
    @interface Type{}

    int DATABASE_ID = 0;
    int ONLINE_ID = 1;
    @IntDef({DATABASE_ID, ONLINE_ID})
    @interface Id{}
}
