package com.synergykit.android.provider;

import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.request.Request;
import com.synergykit.android.requesturl.RequestUrl;
import com.synergykit.android.resource.ISynergykitResponseListener;

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

	public boolean getRecord(String collectionUrl, String recordId,
			Class<?> classOf) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createRecord(String collectionUrl, Object object, ISynergykitResponseListener listener) throws NotInitializedException {
		
		//initialization check
		this.initCheck();
		
		//Synergykit response listener check
		if(listener==null)
			throw new NullPointerException();
		
		//json & url initialization
		String json = GsonWrapper.getInstance().getGson().toJson(object);
		String url = RequestUrl.postRecordUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl);

		Request.post(url, json,listener);
		
		return true;
	}

	public boolean updateRecord(String collectionUrl, String recordId,
			Object object) {
		// TODO Auto-generated method stub
		return false;
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
