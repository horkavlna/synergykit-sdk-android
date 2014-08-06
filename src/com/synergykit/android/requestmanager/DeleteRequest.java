package com.synergykit.android.requestmanager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.resource.SynergykitErrorObject;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;

public class DeleteRequest extends BaseRequestAsyncTask{
	/* Attributes */
	private DeleteResponseListener mListener;
	
	/* Listener setter */
	public void setListener(DeleteResponseListener listener){
		mListener = listener;
	}
	
	/* Do in background */
	@Override
	protected Object doInBackground(Void... params) {
		return requestDelete(getUrl());
	}

	/* On post execute */
	@Override
	protected void onPostExecute(Object object) {
		ResponseManager responseManager = new ResponseManager();
		responseManager.manageResult((HttpResponse) object, mListener);		
	}

}
