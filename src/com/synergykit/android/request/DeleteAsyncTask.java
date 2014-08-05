package com.synergykit.android.request;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.DeleteResponseListener;

import android.os.AsyncTask;

public class DeleteAsyncTask extends BaseRequestAsyncTask{
	/* Attributes */
	private DeleteResponseListener mListener;
	
	/* Listener setter */
	public void setListener(DeleteResponseListener listener){
		mListener = listener;
	}
	
	/* Do in background */
	@Override
	protected HttpResponse doInBackground(Void... params) {
		Delete delete = new Delete(mUrl) {};
		HttpResponse httpResponse;
		
		try {
			httpResponse = delete.execute();
			return httpResponse;		
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	/* On post execute */
	@Override
	protected void onPostExecute(HttpResponse httpResponse) {
		
		//call callback 
		if(mListener==null || httpResponse==null)
			return;
		
		//callback result
		if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)				
			mListener.doneCallback(httpResponse);
		else
			mListener.errorCallback(httpResponse);
				
		
	}

}
