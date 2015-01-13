package com.synergykit.sdk.request;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.listeners.FileDataResponseListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITFileData;
import com.synergykit.sdk.resources.SynergyKITResponse;

import org.apache.http.HttpStatus;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class FileRequestPost extends SynergyKITRequest{

	/* Attributes */
	private SynergyKITConfig config = null;
	private FileDataResponseListener listener = null;
	private byte[] data = null;
	
	/* Config getter */
	public SynergyKITConfig getConfig() {
		return config;
	}

	/* Config setter */
	public void setConfig(SynergyKITConfig config) {
		this.config = config;
	}

	/* Listener getter */
	public FileDataResponseListener getListener() {
		return listener;
	}

	/* Listener setter */
	public void setListener(FileDataResponseListener listener) {
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
		SynergyKITResponse response = null;
		
		SynergyKITLog.print(data.hashCode() + " " + data.length);
		
		//do request
		response = SynergyKITRequest.postFile(config.getUri(), data);
		
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
			SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			return;
		}	
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode,(SynergyKITFileData) dataHolder.object);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}

}
