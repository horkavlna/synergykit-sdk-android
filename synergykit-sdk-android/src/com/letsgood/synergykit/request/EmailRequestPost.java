package com.letsgood.synergykit.request;

import org.apache.http.HttpStatus;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.listeners.EmailResponseListener;
import com.letsgood.synergykit.log.SynergyKITLog;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITEmail;
import com.letsgood.synergykit.resources.SynergyKITResponse;

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
