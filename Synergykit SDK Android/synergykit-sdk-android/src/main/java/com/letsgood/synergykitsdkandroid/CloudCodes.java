package com.letsgood.synergykitsdkandroid;


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.interfaces.ICloudCodes;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.RecordRequestPost;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;

/**
 * Created by Marek on 1/14/15.
 */
public class CloudCodes implements ICloudCodes {

    @Override
    public void invokeCloudCode(SynergykitConfig config, SynergykitObject object, ResponseListener listener) {
            RecordRequestPost request = new RecordRequestPost();

            //Object check
            if(object == null){
                //Log
                SynergykitLog.print(Errors.MSG_NO_OBJECT);

                //error callback
                if(listener!=null)
                    listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

                return;
            }


            //set request
            request.setConfig(config);
            request.setListener(listener);
            request.setObject(object);

            //execute
            Synergykit.synergylize(request, config.isParallelMode());


    }


}
