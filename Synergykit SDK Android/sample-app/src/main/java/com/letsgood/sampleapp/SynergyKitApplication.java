package com.letsgood.sampleapp;

import android.app.Application;

import com.letsgood.synergykitsdkandroid.Synergykit;


/**
 * Created by Marek on 1/13/15.
 */
public class SynergyKitApplication extends Application {
    private static final String APPLICATION_TENANT = "synergykit-sample-app";
    private static final String APPLICATION_KEY = "7cbb9eed-17dd-4f75-a7bd-c92f2f6faef9";
//    private static final String APPLICATION_TENANT = "synergykit";
//    private static final String APPLICATION_KEY = "4df126bc-d8a2-408e-8f4e-be2b7c506f5a";

    @Override
    public void onCreate() {
        super.onCreate();
        if(!Synergykit.isInit()) {
            Synergykit.init(APPLICATION_TENANT, APPLICATION_KEY);
            Synergykit.setDebugModeEnabled(BuildConfig.DEBUG);
        }
    }
}
