package com.sasiddiqui.blazefilemanager;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by shahrukhamd on 05/05/18.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Timber only in debug mode
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
