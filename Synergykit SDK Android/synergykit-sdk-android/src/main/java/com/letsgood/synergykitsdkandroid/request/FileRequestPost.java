package com.letsgood.synergykitsdkandroid.request;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitResponse;

import org.apache.http.HttpStatus;

public class FileRequestPost extends SynergykitRequest {

	/* Attributes */
	private SynergykitConfig config = null;
	private FileResponseListener listener = null;
	private byte[] data = null;
	
	/* Config getter */
	public SynergykitConfig getConfig() {
		return config;
	}

	/* Config setter */
	public void setConfig(SynergykitConfig config) {
		this.config = config;
	}

	/* Listener getter */
	public FileResponseListener getListener() {
		return listener;
	}

	/* Listener setter */
	public void setListener(FileResponseListener listener) {
		this.listener = listener;
	}

	/* File getter */
	public byte[] getData() {
		return data;
	}

	/* File setter */
	public void setData(byte[] data) {
		this.data = data;
	}

	/* Do in background */
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergykitResponse response = null;
		
		SynergykitLog.print(data.hashCode() + " " + data.length);
		
		//do request
		response = SynergykitRequest.postFile(config.getUri(), data);
		
		//manage response
		dataHolder = manageResponseToObject(response, config.getType());
		
		
		
		return dataHolder;
	}

	/* On post execute */
	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder dataHolder = (ResponseDataHolder) object;
		
		//null listener 
		if(listener==null){
			SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			return;
		}	
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode,(SynergykitFile) dataHolder.object);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}

}
