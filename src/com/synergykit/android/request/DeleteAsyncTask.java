package com.synergykit.android.request;

import java.io.IOException;

import org.apache.http.HttpResponse;

import com.synergykit.android.response.DeleteResponseListener;

import android.os.AsyncTask;

public class DeleteAsyncTask extends AsyncTask<Void, Void, Void>{
	/* Attributes */
	private String mUrl;
	private DeleteResponseListener mListener;
	
	/* Url setter */
	public void setUrl(String url){
		mUrl = url;
	}
	
	/* Listener setter */
	public void setListener(DeleteResponseListener listener){
		mListener = listener;
	}
	
	/* Do in background */
	@Override
	protected Void doInBackground(Void... params) {
				
		
		Delete delete = new Delete(mUrl) {};
		try {
			HttpResponse httpResponse = delete.execute();
			
			//call callback 
			if(mListener!=null)
				mListener.callback(httpResponse);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

}
