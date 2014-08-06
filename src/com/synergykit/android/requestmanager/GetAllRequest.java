package com.synergykit.android.requestmanager;

import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.resource.SynergykitErrorObject;
import com.synergykit.android.response.GetRecordsResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;

/**
 * 
 * @author Pavel Stambrecht
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
