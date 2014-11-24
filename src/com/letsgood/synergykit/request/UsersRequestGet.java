package com.letsgood.synergykit.request;

import org.apache.http.HttpStatus;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.listeners.UsersResponseListener;
import com.letsgood.synergykit.log.SynergyKITLog;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITResponse;
import com.letsgood.synergykit.resources.SynergyKITUser;

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
