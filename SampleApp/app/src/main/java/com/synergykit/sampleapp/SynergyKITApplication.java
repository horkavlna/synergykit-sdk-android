package com.synergykit.sampleapp;

import android.app.Application;

import com.synergykit.sdk.SynergyKIT;
import com.synergykit.sdk.log.SynergyKITLog;

/**
 * Created by Marek on 1/13/15.
 */
public class SynergyKITApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(!SynergyKIT.isInit()) {
            SynergyKIT.init("synergykit-sample-app", "7cbb9eed-17dd-4f75-a7bd-c92f2f6faef9");
            SynergyKIT.setDebugModeEnabled(true);
            SynergyKITLog.getInstance().setEnabled(true);
        }
    }
}
