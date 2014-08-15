package com.synergykit.android.requestmanager;

import java.lang.reflect.Type;

import org.apache.http.HttpResponse;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class PutRequest extends BaseRequestAsyncTask {


	/*Attributes */
	private BaseResponseListener mListener;
	private Object mObject;
	private Type mType;
	
	
	
	/* Json setter */
	public void setObject(Object object){
		mObject = object;
	}
	
	/* Listener setter */
	public void setListener(BaseResponseListener listener){
		mListener =listener;
	}
	
	/* ClassOf setter */
	public void setType(Type type){
		mType = type;
	}
	
	
	/* Do in background */
	@Override
	protected Object doInBackground(Void... params) {
		return requestPut(getUrl(), mObject);
	}

	/* On post execute */
	@Override
	protected void onPostExecute(Object object) {
		ResponseManager responseManager = new ResponseManager();
		responseManager.manageResult((HttpResponse) object, mListener, mType);
	}

}
