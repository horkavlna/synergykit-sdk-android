package com.letsgood.synergykitandroidsdk.request;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.listeners.FileDataResponseListener;
import com.letsgood.synergykitandroidsdk.log.SynergyKitLog;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitFileData;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitResponse;

import org.apache.http.HttpStatus;

public class FileRequestPost extends SynergyKitRequest {

	/* Attributes */
	private SynergyKitConfig config = null;
	private FileDataResponseListener listener = null;
	private byte[] data = null;
	
	/* Config getter */
	public SynergyKitConfig getConfig() {
		return config;
	}

	/* Config setter */
	public void setConfig(SynergyKitConfig config) {
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
		SynergyKitResponse response = null;
		
		SynergyKitLog.print(data.hashCode() + " " + data.length);
		
		//do request
		response = SynergyKitRequest.postFile(config.getUri(), data);
		
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
			SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			return;
		}	
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode,(SynergyKitFileData) dataHolder.object);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}

}
