package com.synergykit.sdk;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.interfaces.ICloudCode;
import com.synergykit.sdk.listeners.RecordsResponseListener;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.request.RecordRequestPost;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITObject;

/**
 * Created by Marek on 1/14/15.
 */
public class CloudCode implements ICloudCode {

    @Override
    public void invokeCloudCode(SynergyKITConfig config, SynergyKITObject object, ResponseListener listener) {
            RecordRequestPost request = new RecordRequestPost();

            //Object check
            if(object == null){
                //Log
                SynergyKITLog.print(Errors.MSG_NO_OBJECT);

                //error callback
                if(listener!=null)
                    listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKITError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
                else if(SynergyKIT.isDebugModeEnabled())
                    SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

                return;
            }


            //set request
            request.setConfig(config);
            request.setListener(listener);
            request.setObject(object);

            //execute
            SynergyKIT.synergylize(request, config.isParallelMode());


    }


}
