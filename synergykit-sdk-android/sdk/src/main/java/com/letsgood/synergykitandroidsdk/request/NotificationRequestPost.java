package com.letsgood.synergykitandroidsdk.request;




/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.listeners.NotificationResponseListener;
import com.letsgood.synergykitandroidsdk.log.SynergyKitLog;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitNotification;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitResponse;

import org.apache.http.HttpStatus;

public class NotificationRequestPost extends SynergyKitRequest {

	/* Attributes */
	private SynergyKitConfig config = null;
	private NotificationResponseListener listener = null;
	private SynergyKitNotification notification = null;;
	
	/* Config setter */
	public void setConfig(SynergyKitConfig config){
		this.config = config; 
	}
	
	/* Listener setter */
	public void setListener(NotificationResponseListener listener){
		this.listener =listener;
	}
	
		
	/* Email getter */
	public SynergyKitNotification getNotification() {
		return notification;
	}

	/* Object setter */
	public void setObject(SynergyKitNotification notification) {
		this.notification = notification;
	}
	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergyKitResponse response = null;
		
		//do request
		response = SynergyKitRequest.post(config.getUri(), notification);
		
		//manage response
		dataHolder = manageResponseToObject(response, config.getType());
		
		
		
		return dataHolder;
	}

	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder dataHolder = (ResponseDataHolder) object;
		
		//null listener 
		if(listener==null){
			SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}	
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}
}
