package com.letsgood.synergykitsdkandroid.request;

import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergykitResponse;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

import org.apache.http.HttpStatus;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 8. 4. 2015.
 */
public class RoleRequestDelete extends SynergykitRequest {
    /* Attributes */
    private SynergykitConfig config;
    private UserResponseListener listener;

    /* Config setter */
    public void setConfig(SynergykitConfig config){
        this.config = config;
    }

    /* Listener setter */
    public void setListener(UserResponseListener listener){
        this.listener =listener;
    }

    @Override
    protected Object doInBackground(Void... params) {
        SynergykitRequest.ResponseDataHolder dataHolder = null;
        SynergykitResponse response = null;

        //do request
        response = delete(config.getUri());

        //manage response
        dataHolder = manageResponseToObject(response, config.getType());



        return dataHolder;
    }

    @Override
    protected void onPostExecute(Object object) {
        SynergykitRequest.ResponseDataHolder dataHolder = (SynergykitRequest.ResponseDataHolder) object;

        //null listener
        if(listener==null){
            SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
            return;
        }


        if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
            listener.doneCallback(dataHolder.statusCode,(SynergykitUser)dataHolder.object);
        }else{
            listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
        }

    }
}
