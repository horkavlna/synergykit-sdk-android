package com.letsgood.synergykitsdkandroid;


import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.interfaces.ICloudCodes;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.RecordRequestPost;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitCloudCode;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import java.lang.reflect.Type;

/**
 * Created by Marek on 1/14/15.
 */
public class CloudCodes implements ICloudCodes {

    @Override
    public void invokeCloudCode(SynergykitCloudCode cloudCodeObject, Type type, ResponseListener listener, boolean parallelMode) {
            RecordRequestPost request = new RecordRequestPost();
            SynergykitConfig config = null;
            SynergykitUri uri = null;

            //Object check
            if(cloudCodeObject==null || type == null){
                //Log
                SynergykitLog.print(Errors.MSG_NO_OBJECT);

                //error callback
                if(listener!=null)
                    listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

                return;
            }

            uri = UriBuilder.newInstance()
                        .setResource(Resource.RESOURCE_FUNCTIONS)
                        .setFunctionId(cloudCodeObject.getCloudCodeName())
                        .build();

            config = SynergykitConfig.newInstance()
                                        .setParallelMode(parallelMode)
                                        .setType(type)
                                        .setUri(uri);

            //set request
            request.setConfig(config);
            request.setListener(listener);
            request.setObject(cloudCodeObject);

            //execute
            Synergykit.synergylize(request, config.isParallelMode());


    }


}
