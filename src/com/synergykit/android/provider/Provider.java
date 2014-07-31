package com.synergykit.android.provider;

import java.util.List;

import android.util.Log;

import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.request.Request;
import com.synergykit.android.requesturl.RequestUrl;
import com.synergykit.android.resource.ISynergykitResponseListener;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class Provider{

	/* Attributes */
	private static Provider mInstance = null;
	private Config mConfig=null;
	
	
	/* Instance getter */
	public static Provider getInstance(){
		if(mInstance==null){
			mInstance=new Provider();
		}
		
		return mInstance;
	}
	
	/* Initialization */
	public void init(String tenant, String appKey){		
		mConfig = new Config(tenant, appKey);
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
	public void setAppKey(String appKey) {
		mConfig.setApplicationKey(appKey);
		
	}

	/* Application key getter */
	public String getApplicationKey() {
		return mConfig.getApplicationKey();
	}
	
	/* All record getter */
	public boolean getAllRecords(String collectionUrl, List<?> list, Class<?> classOf) {
		// TODO Auto-generated method stub
		return false;
	}

	/* Get record */
	public void getRecord(String collectionUrl, String recordId, ISynergykitResponseListener listener, Class<?> classOfT) throws NotInitializedException {
		
		//initialization check
		this.initCheck();
				
		//json & url initialization
		String url = RequestUrl.getRecordUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl, recordId);
		
		//request GET
		Request.get(url, listener, classOfT);
	}
	
	/* Create record */
	public void createRecord(String collectionUrl, Object object, ISynergykitResponseListener listener, Class<?> classOfT) throws NotInitializedException {
		
		//initialization check
		this.initCheck();
		
		//json & url initialization
		String json = GsonWrapper.getInstance().getGson().toJson(object);
		String url = RequestUrl.postRecordUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl);

		//request POST
		Request.post(url, json,listener,classOfT);
	}

	public void updateRecord(String collectionUrl, String recordId, Object object, ISynergykitResponseListener listener, Class<?> classOfT) throws NotInitializedException {
		
		//initialization check
		this.initCheck();
				
		//json & url initialization
		String json = GsonWrapper.getInstance().getGson().toJson(object);
		String url = RequestUrl.putRecordUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl,recordId);
		
		Log.e("PUT",json);
		
		//request PUT
		Request.put(url, json, listener, classOfT);
	}

	public boolean deleteRecord(String collectionUrl, String recordId) {
		// TODO Auto-generated method stub
		return false;
	}


	/* initialization check */
	public void initCheck() throws NotInitializedException{
		if(mConfig==null)
			throw new NotInitializedException();
	}
}
