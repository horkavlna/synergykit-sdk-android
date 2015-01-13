package com.synergykit.sdk.request;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.listeners.NotificationResponseListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITNotification;
import com.synergykit.sdk.resources.SynergyKITResponse;

import org.apache.http.HttpStatus;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class NotificationRequestPost extends SynergyKITRequest{

	/* Attributes */
	private SynergyKITConfig config = null;
	private NotificationResponseListener listener = null;
	private SynergyKITNotification notification = null;;
	
	/* Config setter */
	public void setConfig(SynergyKITConfig config){
		this.config = config; 
	}
	
	/* Listener setter */
	public void setListener(NotificationResponseListener listener){
		this.listener =listener;
	}
	
		
	/* Email getter */
	public SynergyKITNotification getNotification() {
		return notification;
	}

	/* Object setter */
	public void setObject(SynergyKITNotification notification) {
		this.notification = notification;
	}
	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergyKITResponse response = null;
		
		//do request
		response = SynergyKITRequest.post(config.getUri(), notification);
		
		//manage response
		dataHolder = manageResponseToObject(response, config.getType());
		
		
		
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
			listener.doneCallback(dataHolder.statusCode);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}
}