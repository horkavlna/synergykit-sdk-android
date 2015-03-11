package com.synergykit.sdk.request;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.listeners.BatchResponseListener;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.resources.SynergyKITBatchResponse;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITResponse;

import org.apache.http.HttpStatus;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public class BatchRequestPost extends SynergyKITRequest{

    /* Attributes */
    private SynergyKITConfig config = null;
    private BatchResponseListener listener = null;
    private Object object = null;;

    /* Config setter */
    public void setConfig(SynergyKITConfig config){
        this.config = config;
    }

    /* Listener setter */
    public void setListener(BatchResponseListener listener){
        this.listener =listener;
    }


    /* Object getter */
    public Object getObject() {
        return object;
    }

    /* Object setter */
    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    protected Object doInBackground(Void... params) {
        ResponseDataHolder dataHolder = null;
        SynergyKITResponse response = null;

        //do request
        response = SynergyKITRequest.post(config.getUri(), object);

        //manage response
        dataHolder = manageResponseToObjects(response,config.getType());



        return dataHolder;
    }

    @Override
    protected void onPostExecute(Object object) {
        ResponseDataHolder dataHolder = (ResponseDataHolder) object;

        //null listener
        if(listener==null){
            SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
            return;
        }

        if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
            listener.doneCallback(dataHolder.statusCode, (SynergyKITBatchResponse[]) dataHolder.objects);
        }else{
            listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
        }

    }
}
