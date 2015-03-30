package com.letsgood.synergykitandroidsdk.request;




/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.listeners.UserResponseListener;
import com.letsgood.synergykitandroidsdk.log.SynergyKitLog;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitResponse;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitUser;

import org.apache.http.HttpStatus;

public class UserRequestPut extends SynergyKitRequest {
	/* Attributes */
	private SynergyKitConfig config = null;
	private UserResponseListener listener = null;
	private Object object = null;;
	
	/* Config setter */
	public void setConfig(SynergyKitConfig config){
		this.config = config;
	}
	
	/* Listener setter */
	public void setListener(UserResponseListener listener){
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
		SynergyKitResponse response = null;
		
		//do request
		response = SynergyKitRequest.put(config.getUri(), object);
		
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
			listener.doneCallback(dataHolder.statusCode,(SynergyKitUser) dataHolder.object);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}
}	
