package com.letsgood.synergykit;

import java.lang.reflect.Type;

import com.letsgood.synergykit.builders.UriBuilder;
import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.builders.uri.Resource;
import com.letsgood.synergykit.interfaces.IRecords;
import com.letsgood.synergykit.listeners.DeleteResponseListener;
import com.letsgood.synergykit.listeners.RecordsResponseListener;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.log.SynergyKITLog;
import com.letsgood.synergykit.request.RecordRequestGet;
import com.letsgood.synergykit.request.RecordRequestPost;
import com.letsgood.synergykit.request.RecordRequestPut;
import com.letsgood.synergykit.request.RecordsRequestGet;
import com.letsgood.synergykit.request.RequestDelete;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITObject;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Records implements IRecords {
	
	/* Constants */
	private static final int TOP = 100;

	/* Get record*/
	@Override
	public void getRecord(SynergyKITConfig config, ResponseListener listener) {
		RecordRequestGet request = new RecordRequestGet();		
		request.setConfig(config);
		request.setListener(listener); 
		SynergyKIT.synergylize(request, config.isParallelMode());
	}
	
	/* Get record */
	@Override
	public void getRecord(String collectionUrl, String recordId, Type type, ResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = SynergyKIT.getConfig();

		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl)
								.setRecordId(recordId);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);

		//redirect
		this.getRecord(config, listener);
		
		
		
	}

	/* Records getter */
	@Override
	public void getRecords(SynergyKITConfig config,	RecordsResponseListener listener) {
		RecordsRequestGet request = new RecordsRequestGet();		
		request.setConfig(config);
		request.setListener(listener);
		SynergyKIT.synergylize(request, config.isParallelMode());
		
	}

	/* Records getter */
	@Override
	public void getRecords(String collectionUrl, Type type,	RecordsResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();

		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl)
								.setTop(TOP);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);
		

		//redirect
		this.getRecords(config, listener);
		
	}

	/* Record creater */
	@Override
	public void createRecord(String collectionUrl, SynergyKITObject object,	ResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		RecordRequestPost request = new RecordRequestPost();

		//Object check
		if(object == null){
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
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(object.getClass());
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(object);
		
		//execute
		SynergyKIT.synergylize(request, parallelMode);
		
	}

	/* Record updater */
	@Override
	public void updateRecord(String collectionUrl, SynergyKITObject object, ResponseListener listener,	boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		RecordRequestPut request = new RecordRequestPut();
		
		//Object check
		if(object == null){
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
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl)
								.setRecordId(object.get_id());
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(object.getClass());
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(object);
		
		//execute
		SynergyKIT.synergylize(request, parallelMode);
		
	}

	/* Record deleter */
	@Override
	public void deleteRecord(String collectionUrl, String recordId,	DeleteResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		RequestDelete request = new RequestDelete();
		
		//Object check
		if(collectionUrl==null || recordId == null){
			//Log
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);	
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergyKITError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
			else if(SynergyKIT.isDebugModeEnabled())
				SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setCollection(collectionUrl)
								.setRecordId(recordId);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		
		//execute
		SynergyKIT.synergylize(request, parallelMode);
		
	}

}
