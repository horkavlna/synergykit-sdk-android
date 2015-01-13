package com.synergykit.sdk.request;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.listeners.UsersResponseListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITResponse;
import com.synergykit.sdk.resources.SynergyKITUser;

import org.apache.http.HttpStatus;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


public class UsersRequestGet extends SynergyKITRequest{

	/* Attributes */
	private SynergyKITConfig config;
	private UsersResponseListener listener;
	
	/* Config setter */
	public void setConfig(SynergyKITConfig config){
		this.config = config;
	}
	
	/* Listener setter */
	public void setListener(UsersResponseListener listener){
		this.listener =listener;
	}
	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergyKITResponse response = null;
		
		//do request
		response = get(config.getUri());
		
		//manage response
		dataHolder = manageResponseToObjects(response, config.getType());		
		
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
			listener.doneCallback(dataHolder.statusCode,(SynergyKITUser[]) dataHolder.objects);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
	}
	
}
