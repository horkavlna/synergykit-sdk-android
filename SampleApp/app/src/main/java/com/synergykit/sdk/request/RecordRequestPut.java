package com.synergykit.sdk.request;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.log.SynergyKitLog;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitResponse;

import org.apache.http.HttpStatus;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class RecordRequestPut extends SynergyKitRequest {
	/* Attributes */
	private SynergyKitConfig config = null;
	private ResponseListener listener = null;
	private Object object = null;;
	
	/* Config setter */
	public void setConfig(SynergyKitConfig config){
		this.config = config;
	} 
	
	/* Listener setter */
	public void setListener(ResponseListener listener){
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
			listener.doneCallback(dataHolder.statusCode, dataHolder.object);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}
}
