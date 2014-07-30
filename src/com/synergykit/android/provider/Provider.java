package com.synergykit.android.provider;

import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.synergykit.android.Synergykit;
import com.synergykit.android.request.IRequest;
import com.synergykit.android.request.Request;
import com.synergykit.android.requesturl.RequestUrl;

public class Provider implements IProvider{

	/* Attributes */
	private Gson mGson = new Gson();
	private IRequest mSynergyKit;
	private RequestUrl mRequestUrl;
	private Config mConfig;
	
	/* Constructor */
	public Provider(String tenant, String appKey) {
		mConfig = new Config(tenant, appKey);
		mSynergyKit = new Request();
		mRequestUrl = new RequestUrl();
		
	}
	
	/* Tenant setter */
	@Override
	public void setTenant(String tenant) {
		mConfig.setTenant(tenant);
	}

	/* Tenant getter */
	@Override
	public String getTenant() {
		return mConfig.getTenant();
	}

	/* Application key setter */
	@Override
	public void setAppKey(String appKey) {
		mConfig.setApplicationKey(appKey);
		
	}

	/* Application key getter */
	@Override
	public String getApplicationKey() {
		return mConfig.getApplicationKey();
	}
	
	/* All record getter */
	@Override
	public boolean getAllRecords(String collectionUrl, List<?> list, Class<?> classOf) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getRecord(String collectionUrl, String recordId,
			Class<?> classOf) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createRecord(String collectionUrl, Object object) {
		String json = mGson.toJson(object);
		String url = mRequestUrl.postRecordUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl);
		
		Log.e("SK",json);
		Log.e("SK",url);
		
		mSynergyKit.post(url, json);
		
		return true;
	}

	@Override
	public boolean updateRecord(String collectionUrl, String recordId,
			Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRecord(String collectionUrl, String recordId) {
		// TODO Auto-generated method stub
		return false;
	}



}
