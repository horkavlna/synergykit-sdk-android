package com.synergykit.android.resource;

import java.lang.reflect.Type;

import com.synergykit.android.Synergykit;
import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetRecordsResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;
import com.synergykit.android.urlbuilder.Url;


public abstract class SynergylizeRequestAsyncTask extends BaseRequestAsyncTask {

	/* Attribute */
	private ResponseManager mResponseManager = new ResponseManager();
	
	/* Post */
	protected void post(Url url,Object object, BaseResponseListener listener, Type type){	
		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		
		mResponseManager.manageResult(requestPost(url, object), listener, type);		
		
	}
	
	/* Put */
	protected void put(Url url, Object object, BaseResponseListener listener, Type type){	
		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}

		mResponseManager.manageResult(requestPut(url, object), listener, type);	
		
	}
	
	/* Get */
	protected void get(Url url, BaseResponseListener listener, Type type){
		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		
		mResponseManager.manageResult(requestGet(url), listener, type);	
	}
	
	/* Get all */
	protected void get(Url url, GetRecordsResponseListener listener, Type type){
		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		
		mResponseManager.manageResult(requestGet(url), listener, type);	
	}

	/* Delete */
	protected void delete(Url url, DeleteResponseListener listener){
		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		
		mResponseManager.manageResult(requestDelete(url), listener);	
	}
	
	/* initialization check */
	public void initCheck() throws NotInitializedException{
		if(!Synergykit.isInit())
			throw new NotInitializedException();
	}
}
