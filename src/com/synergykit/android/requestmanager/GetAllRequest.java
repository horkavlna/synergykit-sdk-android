	package com.synergykit.android.requestmanager;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.GetRecordsResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class GetAllRequest extends BaseRequestAsyncTask{
	/* Attributes */
	private Type mType;
	private GetRecordsResponseListener mListener;
	
	/* Listener setter */
	public void setListener(GetRecordsResponseListener listener){
		mListener =listener;
	}
	
	/* Type setter */
	public void setType(Type type){
		mType = type;
	}	
	

	/* Do in background */
	@Override
	protected Object doInBackground(Void... params) {
		return requestGet(getUrl());
	}
	

	/* On post execute */
	@Override
	protected void onPostExecute(Object object) {
		ResponseManager responseManager = new ResponseManager();
		responseManager.manageResult((HttpResponse) object, mListener, mType);
	}	

}
