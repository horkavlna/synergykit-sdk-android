package com.synergykit.sdk;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.interfaces.ICloudCodes;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.log.SynergyKitLog;
import com.synergykit.sdk.request.RecordRequestPost;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitObject;

/**
 * Created by Marek on 1/14/15.
 */
public class CloudCodes implements ICloudCodes {

    @Override
    public void invokeCloudCode(SynergyKitConfig config, SynergyKitObject object, ResponseListener listener) {
            RecordRequestPost request = new RecordRequestPost();

            //Object check
            if(object == null){
                //Log
                SynergyKitLog.print(Errors.MSG_NO_OBJECT);

                //error callback
                if(listener!=null)
                    listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
                else if(SynergyKit.isDebugModeEnabled())
                    SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

                return;
            }


            //set request
            request.setConfig(config);
            request.setListener(listener);
            request.setObject(object);

            //execute
            SynergyKit.synergylize(request, config.isParallelMode());


    }


}
