package com.letsgood.synergykitsdkandroid;


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.interfaces.ICloudCodes;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergyKitLog;
import com.letsgood.synergykitsdkandroid.request.RecordRequestPost;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitObject;

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
