package com.synergykit.android.requestmanager;

import java.lang.reflect.Type;

import android.util.Log;

import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetRecordsResponseListener;
import com.synergykit.android.urlbuilder.UrlBuilder;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class RequestManager{


	/* Attributes */
	private static RequestManager mInstance = null;
	private Config mConfig=new Config(null, null);
	
	
	/* Instance getter */
	public static RequestManager getInstance(){
		if(mInstance==null){
			mInstance=new RequestManager();
		}
		
		return mInstance;
	}
	
	/* Initialization */
	public void init(String tenant, String appKey){		
		mConfig.setTenant(tenant);
		mConfig.setApplicationKey(appKey);
	}
	
	/* Reset */
	public void reset(){
		mConfig.setApplicationKey(null);
		mConfig.setTenant(null);
	}
	
	/* Tenant setter */
	public void setTenant(String tenant) {
		mConfig.setTenant(tenant);
	}

	/* Tenant getter */
	public String getTenant() {
		return mConfig.getTenant();
	}

	/* Application key setter */
	public void setApplicationKey(String applicationKey) {
		mConfig.setApplicationKey(applicationKey);
		
	}

	/* Application key getter */
	public String getApplicationKey() {
		return mConfig.getApplicationKey();
	}
	//----------------------------------------------------------------------------------------------------------------------
	/* Get records */
	public void getRecords(String collectionUrl,GetRecordsResponseListener listener,Type type){
		UrlBuilder urlBuilder = new UrlBuilder();
		GetAllRequest getAsyncTask = new GetAllRequest();	
		
		
		try {
			//init check
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
			
		//build url
		urlBuilder.setResource(UrlBuilder.RESOURCE_DATA)
				  .setResourceUrl(collectionUrl);
		
		Log.e("Synergykit",urlBuilder.build().getUrl());
				  
		//set request
		getAsyncTask.setUrl(urlBuilder.build());
		getAsyncTask.setType(type);
		getAsyncTask.setListener(listener);
		
		//send request
		this.synergylize(getAsyncTask);		
	}
	
	/* Get record */
	public void getRecord(String collectionUrl, String recordId,BaseResponseListener listener,Type type){
		UrlBuilder urlBuilder = new UrlBuilder();
		GetRequest getAsyncTask = new GetRequest();	
		
		
		try {
			//init check
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		
		//build url
		urlBuilder.setResource(UrlBuilder.RESOURCE_DATA)
				  .setResourceUrl(collectionUrl)
				  .setResourceId(recordId);
		
		
		//set request
		getAsyncTask.setUrl(urlBuilder.build());
		getAsyncTask.setType(type);
		getAsyncTask.setListener(listener);
		
		//send request
		this.synergylize(getAsyncTask);
			
	
		
		
	}
	
	/* Create record */
	public void createRecord(String collectionUrl, Object object, BaseResponseListener listener,Type type){
		UrlBuilder urlBuilder =	new UrlBuilder();
		PostRequest postAsyncTask = new PostRequest();
		
		try {
			//init check
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		
		
		urlBuilder.setResource(UrlBuilder.RESOURCE_DATA)
				  .setResourceUrl(collectionUrl);
		
		//set request
		postAsyncTask.setUrl(urlBuilder.build());
		postAsyncTask.setObject(object);
		postAsyncTask.setListener(listener);
		postAsyncTask.setType(type);
		
		//send request
		this.synergylize(postAsyncTask);
		
	}
	
	/* Update record */
	public void updateRecord(String collectionUrl, String recordId, Object object, BaseResponseListener listener, Type type){
		UrlBuilder urlBuilder =	new UrlBuilder();
		PutRequest putAsyncTask = new PutRequest();
		
		try {
			//init check
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		
		urlBuilder.setResource(UrlBuilder.RESOURCE_DATA)
				  .setResourceUrl(collectionUrl)
				  .setResourceId(recordId);
		
		//set request
		putAsyncTask.setUrl(urlBuilder.build());
		putAsyncTask.setObject(object);
		putAsyncTask.setListener(listener);
		putAsyncTask.setType(type);
		
		//send request
		this.synergylize(putAsyncTask);
	}
	
	/* Delete record */
	public void deleteRecord(String collectionUrl, String recordId, DeleteResponseListener listener){
		UrlBuilder urlBuilder =	new UrlBuilder();
		DeleteRequest deleteAsyncTask = new DeleteRequest();
		
		try {
			//init check
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		
		urlBuilder.setResource(UrlBuilder.RESOURCE_DATA)
		  		   .setResourceUrl(collectionUrl)
		  		   .setResourceId(recordId);
		
		
		//set request
		deleteAsyncTask.setUrl(urlBuilder.build());
		deleteAsyncTask.setListener(listener);
				
		//send request
		this.synergylize(deleteAsyncTask);
		
	}
	//----------------------------------------------------------------------------------------------------------------------
	/* Serialize */
	public void synergylize(BaseRequestAsyncTask baseRequestAsyncTask){
		baseRequestAsyncTask.execute();
	}

	/* Is Init */
	public boolean isInit(){
		if( mConfig.getTenant()==null || mConfig.getApplicationKey()==null)
			return false;
		
		return true;
	}

	/* initialization check */
	public void initCheck() throws NotInitializedException{
		if(!this.isInit())
			throw new NotInitializedException();
	}
}
