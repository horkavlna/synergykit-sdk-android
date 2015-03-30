package com.letsgood.synergykitandroidsdk;

import com.letsgood.synergykitandroidsdk.builders.UriBuilder;
import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.builders.uri.Resource;
import com.letsgood.synergykitandroidsdk.interfaces.IRecords;
import com.letsgood.synergykitandroidsdk.listeners.DeleteResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.RecordsResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.ResponseListener;
import com.letsgood.synergykitandroidsdk.log.SynergyKitLog;
import com.letsgood.synergykitandroidsdk.request.RecordRequestGet;
import com.letsgood.synergykitandroidsdk.request.RecordRequestPatch;
import com.letsgood.synergykitandroidsdk.request.RecordRequestPost;
import com.letsgood.synergykitandroidsdk.request.RecordRequestPut;
import com.letsgood.synergykitandroidsdk.request.RecordsRequestGet;
import com.letsgood.synergykitandroidsdk.request.RequestDelete;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitObject;

import java.lang.reflect.Type;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Records implements IRecords {
	
	/* Constants */
	private static final int TOP = 100;

	/* Get record*/
	@Override
	public void getRecord(SynergyKitConfig config, ResponseListener listener) {
		RecordRequestGet request = new RecordRequestGet();
		request.setConfig(config);
		request.setListener(listener); 
		SynergyKit.synergylize(request, config.isParallelMode());
	}
	
	/* Get record */
	@Override
	public void getRecord(String collectionUrl, String recordId, Type type, ResponseListener listener, boolean parallelMode) {
		SynergyKitConfig config = SynergyKit.getConfig();

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
	public void getRecords(SynergyKitConfig config,	RecordsResponseListener listener) {
		RecordsRequestGet request = new RecordsRequestGet();
		request.setConfig(config);
		request.setListener(listener);
		SynergyKit.synergylize(request, config.isParallelMode());
		
	}

	/* Records getter */
	@Override
	public void getRecords(String collectionUrl, Type type,	RecordsResponseListener listener, boolean parallelMode) {
		SynergyKitConfig config = new SynergyKitConfig();

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
	public void createRecord(String collectionUrl, SynergyKitObject object,	ResponseListener listener, boolean parallelMode) {
		SynergyKitConfig config = new SynergyKitConfig();
		RecordRequestPost request = new RecordRequestPost();

		//Object check
		if(object == null){
			//Log
			SynergyKitLog.print(Errors.MSG_NO_OBJECT);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKit.isDebugModeEnabled())
				SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
		SynergyKit.synergylize(request, parallelMode);
		
	}

	/* Record updater */
	@Override
	public void updateRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener,	boolean parallelMode) {
		SynergyKitConfig config = new SynergyKitConfig();
		RecordRequestPut request = new RecordRequestPut();
		
		//Object check
		if(object == null){
			//Log
			SynergyKitLog.print(Errors.MSG_NO_OBJECT);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKit.isDebugModeEnabled())
				SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
		SynergyKit.synergylize(request, parallelMode);
		
	}

    @Override
    public void patchRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener, boolean parallelMode) {
        SynergyKitConfig config = new SynergyKitConfig();
        RecordRequestPatch request = new RecordRequestPatch();

        //Object check
        if(object == null){
            //Log
            SynergyKitLog.print(Errors.MSG_NO_OBJECT);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
            else if(SynergyKit.isDebugModeEnabled())
                SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

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
        SynergyKit.synergylize(request, parallelMode);
    }

    /* Record deleter */
	@Override
	public void deleteRecord(String collectionUrl, String recordId,	DeleteResponseListener listener, boolean parallelMode) {
		SynergyKitConfig config = new SynergyKitConfig();
		RequestDelete request = new RequestDelete();
		
		//Object check
		if(collectionUrl==null || recordId == null){
			//Log
			SynergyKitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergyKitError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
			else if(SynergyKit.isDebugModeEnabled())
				SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
		SynergyKit.synergylize(request, parallelMode);
		
	}

}
