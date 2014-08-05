package com.synergykit.android.request;

import java.lang.reflect.Type;

import android.util.Log;

import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetRecordsResponseListener;
import com.synergykit.android.urlbuilder.UrlBuilder;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class Request{

	/* Attributes */
	private static Request mInstance = null;
	private Config mConfig=null;
	
	
	/* Instance getter */
	public static Request getInstance(){
		if(mInstance==null){
			mInstance=new Request();
		}
		
		return mInstance;
	}
	
	/* Initialization */
	public void init(String tenant, String appKey){		
		mConfig = new Config(tenant, appKey);
	}
	
	/* Reset */
	public void reset(){
		mConfig=null;
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
	
	/* Get records */
	public void getRecords(String collectionUrl,GetRecordsResponseListener listener,Type type){
		UrlBuilder urlBuilder = new UrlBuilder();
		GetRecordsAsyncTask getAsyncTask = new GetRecordsAsyncTask();	
		
		
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
		
		Log.e("Synergykit",urlBuilder.build().getUrl(getTenant(), getApplicationKey()));
				  
		//set request
		getAsyncTask.setUrl(urlBuilder.build().getUrl(getTenant(), getApplicationKey()));
		getAsyncTask.setType(type);
		getAsyncTask.setListener(listener);
		
		//send request
		this.synergylize(getAsyncTask);		
	}
	
	/* Get record */
	public void getRecord(String collectionUrl, String recordId,BaseResponseListener listener,Type type){
		UrlBuilder urlBuilder = new UrlBuilder();
		GetAsyncTask getAsyncTask = new GetAsyncTask();	
		
		
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
		getAsyncTask.setUrl(urlBuilder.build().getUrl(getTenant(), getApplicationKey()));
		getAsyncTask.setType(type);
		getAsyncTask.setListener(listener);
		
		//send request
		this.synergylize(getAsyncTask);
			
	
		
		
	}
	
	/* Create record */
	public void createRecord(String collectionUrl, Object object, BaseResponseListener listener,Type type){
		UrlBuilder urlBuilder =	new UrlBuilder();
		PostAsyncTask postAsyncTask = new PostAsyncTask();
		
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
		postAsyncTask.setUrl(urlBuilder.build().getUrl(getTenant(), getApplicationKey()));
		postAsyncTask.setJson(GsonWrapper.getInstance().getGson().toJson(object));
		postAsyncTask.setListener(listener);
		postAsyncTask.setType(type);
		
		//send request
		this.synergylize(postAsyncTask);
		
	}
	
	/* Update record */
	public void updateRecord(String collectionUrl, String recordId, Object object, BaseResponseListener listener, Type type){
		UrlBuilder urlBuilder =	new UrlBuilder();
		PutAsyncTask putAsyncTask = new PutAsyncTask();
		
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
		putAsyncTask.setUrl(urlBuilder.build().getUrl(getTenant(), getApplicationKey()));
		putAsyncTask.setJson(GsonWrapper.getInstance().getGson().toJson(object));
		putAsyncTask.setListener(listener);
		putAsyncTask.setType(type);
		
		//send request
		this.synergylize(putAsyncTask);
	}
	
	/* Delete record */
	public void deleteRecord(String collectionUrl, String recordId, DeleteResponseListener listener){
		UrlBuilder urlBuilder =	new UrlBuilder();
		DeleteAsyncTask deleteAsyncTask = new DeleteAsyncTask();
		
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
		deleteAsyncTask.setUrl(urlBuilder.build().getUrl(getTenant(), getApplicationKey()));
		deleteAsyncTask.setListener(listener);
				
		//send request
		this.synergylize(deleteAsyncTask);
		
	}
	
	/* Serialize */
	public void synergylize(BaseRequestAsyncTask baseRequestAsyncTask){
		baseRequestAsyncTask.execute();
	}


	/* initialization check */
	public void initCheck() throws NotInitializedException{
		if(mConfig==null)
			throw new NotInitializedException();
	}
}
