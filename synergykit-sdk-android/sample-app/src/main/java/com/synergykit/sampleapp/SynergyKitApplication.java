package com.synergykit.sampleapp;

import android.app.Application;

import com.letsgood.synergykitandroidsdk.SynergyKit;
import com.letsgood.synergykitandroidsdk.log.SynergyKitLog;


/**
 * Created by Marek on 1/13/15.
 */
public class SynergyKitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(!SynergyKit.isInit()) {
            SynergyKit.init("synergykit", "4df126bc-d8a2-408e-8f4e-be2b7c506f5a");
            SynergyKit.setDebugModeEnabled(true);
            SynergyKitLog.getInstance().setEnabled(true);
        }
    }
}
