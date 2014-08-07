package com.synergykit.android.requestmanager;

import java.lang.reflect.Type;

import org.apache.http.HttpResponse;

import android.util.Log;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.GetUsersResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;

public class GetUsersRequest extends BaseRequestAsyncTask{
	/* Attributes */
	private GetUsersResponseListener mListener;
	private Type mType;
	
	
	
	public Type getType() {
		return mType;
	}

	public void setType(Type type) {
		this.mType = type;
	}

	/* Listener setter */
	public void setListener(GetUsersResponseListener listener){
		mListener =listener;
	}

	/* Do in background */
	@Override
	protected Object doInBackground(Void... params) {
		Log.e("Synergykit",getUrl().getUrl());
		return requestGet(getUrl());
	}
	

	/* On post execute */
	@Override
	protected void onPostExecute(Object object) {
		ResponseManager responseManager = new ResponseManager();
		responseManager.manageResult((HttpResponse)object, mListener, mType);
	}	

}
