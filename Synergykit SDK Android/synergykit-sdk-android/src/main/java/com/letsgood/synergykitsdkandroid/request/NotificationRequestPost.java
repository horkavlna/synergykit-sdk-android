package com.letsgood.synergykitsdkandroid.request;




/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.listeners.NotificationResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitNotification;
import com.letsgood.synergykitsdkandroid.resources.SynergykitResponse;

import org.apache.http.HttpStatus;

public class NotificationRequestPost extends SynergykitRequest {

	/* Attributes */
	private SynergykitConfig config = null;
	private NotificationResponseListener listener = null;
	private SynergykitNotification notification = null;;
	
	/* Config setter */
	public void setConfig(SynergykitConfig config){
		this.config = config; 
	}
	
	/* Listener setter */
	public void setListener(NotificationResponseListener listener){
		this.listener =listener;
	}
	
		
	/* Email getter */
	public SynergykitNotification getNotification() {
		return notification;
	}

	/* Object setter */
	public void setObject(SynergykitNotification notification) {
		this.notification = notification;
	}
	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergykitResponse response = null;
		
		//do request
		response = SynergykitRequest.post(config.getUri(), notification);
		
		//manage response
		dataHolder = manageResponseToObject(response, config.getType());
		
		
		
		return dataHolder;
	}

	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder dataHolder = (ResponseDataHolder) object;
		
		//null listener 
		if(listener==null){
			SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}	
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}
}
