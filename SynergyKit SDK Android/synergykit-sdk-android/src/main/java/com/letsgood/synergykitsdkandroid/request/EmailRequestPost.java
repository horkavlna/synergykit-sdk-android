package com.letsgood.synergykitsdkandroid.request;




/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.listeners.EmailResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergyKitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitEmail;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitResponse;

import org.apache.http.HttpStatus;

public class EmailRequestPost extends SynergyKitRequest {

	/* Attributes */
	private SynergyKitConfig config = null;
	private EmailResponseListener listener = null;
	private SynergyKitEmail email = null;;
	
	/* Config setter */
	public void setConfig(SynergyKitConfig config){
		this.config = config; 
	}
	
	/* Listener setter */
	public void setListener(EmailResponseListener listener){
		this.listener =listener;
	}
	
		
	/* Email getter */
	public SynergyKitEmail getEmail() {
		return email;
	}

	/* Object setter */
	public void setObject(SynergyKitEmail email) {
		this.email = email;
	}
	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergyKitResponse response = null;
		
		//do request
		response = SynergyKitRequest.post(config.getUri(), email);
		
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
