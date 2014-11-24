package com.letsgood.synergykit;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.letsgood.synergykit.builders.UriBuilder;
import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.builders.uri.Resource;
import com.letsgood.synergykit.interfaces.IFiles;
import com.letsgood.synergykit.listeners.BitmapResponseListener;
import com.letsgood.synergykit.listeners.BytesResponseListener;
import com.letsgood.synergykit.listeners.FileDataResponseListener;
import com.letsgood.synergykit.log.SynergyKITLog;
import com.letsgood.synergykit.request.FileRequestGet;
import com.letsgood.synergykit.request.FileRequestPost;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITFileData;
import com.letsgood.synergykit.resources.SynergyKITUri;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Files implements IFiles{

	/* Create file */
	@Override
	public void uploadFile(byte[] data, FileDataResponseListener listener) {
		SynergyKITConfig config = new SynergyKITConfig();
		FileRequestPost request = new FileRequestPost();


		//Object check
		if(data == null){
			//Log
			SynergyKITLog.print(Errors.MSG_NO_OBJECT);	
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKITError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKIT.isDebugModeEnabled())
				SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_FILES);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(true);
		config.setType(SynergyKITFileData.class);
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setData(data);
		
		//execute
		SynergyKIT.synergylize(request, true);
		
	}

	/* Upload bitmap */
	@Override
	public void uploadBitmap(Bitmap bitmap, FileDataResponseListener listener) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		//Object check
		if(bitmap == null){
			//Log
			SynergyKITLog.print(Errors.MSG_NO_OBJECT);	
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKITError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKIT.isDebugModeEnabled())
				SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);

		
		this.uploadFile(byteArrayOutputStream.toByteArray(), listener); //create file
		
		
	}
	
	/* Dowload Bitmap */
	@Override
	public void downloadBitmap(String uri, final BitmapResponseListener listener) {
		
		//Download file
		SynergyKIT.downloadFile(uri, new BytesResponseListener() {
			
			//Error callback
			@Override
			public void errorCallback(int statusCode, SynergyKITError errorObject) {
				listener.errorCallback(statusCode, errorObject);				
			}
			
			//Done callback
			@Override
			public void doneCallback(int statusCode, byte[] data) {
				Bitmap bitmap = null;
				
				//build bitmap
				if(data!=null){
					bitmap = BitmapFactory.decodeByteArray(data , 0, data .length);
				}
				
				
				listener.doneCallback(statusCode, bitmap);
			}
		});
		
	}

	/* Download file */
	@Override
	public void downloadFile(String uri, BytesResponseListener listener) {
		FileRequestGet request = new FileRequestGet();
		SynergyKITConfig config = SynergyKIT.getConfig();		
				
		
		//Uri builder
		SynergyKITUri synergyKITUri = new SynergyKITUri(uri);
		
		//set config
		config.setUri(synergyKITUri);
		config.setParallelMode(true);

		request.setConfig(config);
		request.setListener(listener); 
		SynergyKIT.synergylize(request, config.isParallelMode());	
	}




}
