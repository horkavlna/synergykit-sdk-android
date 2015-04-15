package com.letsgood.synergykitsdkandroid.request;




/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.listeners.EmailResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEmail;
import com.letsgood.synergykitsdkandroid.resources.SynergykitResponse;

import org.apache.http.HttpStatus;

public class EmailRequestPost extends SynergykitRequest {

	/* Attributes */
	private SynergykitConfig config = null;
	private EmailResponseListener listener = null;
	private SynergykitEmail email = null;;
	
	/* Config setter */
	public void setConfig(SynergykitConfig config){
		this.config = config; 
	}
	
	/* Listener setter */
	public void setListener(EmailResponseListener listener){
		this.listener =listener;
	}
	
		
	/* Email getter */
	public SynergykitEmail getEmail() {
		return email;
	}

	/* Object setter */
	public void setObject(SynergykitEmail email) {
		this.email = email;
	}
	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergykitResponse response = null;
		
		//do request
		response = SynergykitRequest.post(config.getUri(), email);
		
		//manage response
		dataHolder = manageResponseToObject(response, config.getType());
		
		
		
		return dataHolder;
	}

	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder dataHolder = (ResponseDataHolder) object;
		
		//null listener 
		if(listener==null){
			SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}	
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}
}
