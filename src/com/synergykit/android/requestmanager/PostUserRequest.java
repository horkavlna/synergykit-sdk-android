package com.synergykit.android.requestmanager;

import java.lang.reflect.Type;

import org.apache.http.HttpResponse;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.BaseUserResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;

public class PostUserRequest extends BaseRequestAsyncTask {
	/*Attributes */
	private BaseUserResponseListener mListener;
	private Object mObject;
	private Type mType;
	
	/* Object setter */
	public void setObject(Object object){
		mObject = object;
	}
	
	/* Listener setter */
	public void setListener(BaseUserResponseListener listener){
		mListener =listener;
	}
	
	/* ClassOf setter */
	public void setType(Type type){
		mType = type;
	}
		
	
	/* Do  in background */
	@Override
	protected Object doInBackground(Void... params) {
		return requestPost(getUrl(), mObject);
	}

	/* On post execute */
	@Override
	protected void onPostExecute(Object object) {
		ResponseManager responseManager = new ResponseManager();
		responseManager.manageResult((HttpResponse) object, mListener, mType);
	}

}
