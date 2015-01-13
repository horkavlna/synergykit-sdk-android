package com.synergykit.sdk.request;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.listeners.EmailResponseListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITEmail;
import com.synergykit.sdk.resources.SynergyKITResponse;

import org.apache.http.HttpStatus;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class EmailRequestPost extends SynergyKITRequest{

	/* Attributes */
	private SynergyKITConfig config = null;
	private EmailResponseListener listener = null;
	private SynergyKITEmail email = null;;
	
	/* Config setter */
	public void setConfig(SynergyKITConfig config){
		this.config = config; 
	}
	
	/* Listener setter */
	public void setListener(EmailResponseListener listener){
		this.listener =listener;
	}
	
		
	/* Email getter */
	public SynergyKITEmail getEmail() {
		return email;
	}

	/* Object setter */
	public void setObject(SynergyKITEmail email) {
		this.email = email;
	}
	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergyKITResponse response = null;
		
		//do request
		response = SynergyKITRequest.post(config.getUri(), email);
		
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
