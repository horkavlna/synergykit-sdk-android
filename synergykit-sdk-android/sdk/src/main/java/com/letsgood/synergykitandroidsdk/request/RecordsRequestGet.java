package com.letsgood.synergykitandroidsdk.request;




/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.listeners.RecordsResponseListener;
import com.letsgood.synergykitandroidsdk.log.SynergyKitLog;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitResponse;

import org.apache.http.HttpStatus;

public class RecordsRequestGet extends SynergyKitRequest {

	/* Attributes */
	private SynergyKitConfig config;
	private RecordsResponseListener listener;
	
	/* Config setter */
	public void setConfig(SynergyKitConfig config){
		this.config = config;
	} 
	
	/* Listener setter */
	public void setListener(RecordsResponseListener listener){
		this.listener =listener;
	}
	

	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergyKitResponse response = null;
		
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
			SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			return;
		}	
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode, dataHolder.objects);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
	}

}
