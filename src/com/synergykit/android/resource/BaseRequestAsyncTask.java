package com.synergykit.android.resource;


import org.apache.http.HttpResponse;

import android.os.AsyncTask;

public abstract class BaseRequestAsyncTask extends AsyncTask<Void, Void, HttpResponse>{

	/* Attribute */
	protected String mUrl;
	
	/* Url setter */
	public void setUrl(String url){
		mUrl = url;
	}
	
	/* Url getter */
	public String getUrl(){
		return mUrl;
	}
	
	/* Do in background */
	@Override
	protected abstract HttpResponse doInBackground(Void... params);
	
	/* On post execute */
	@Override
	protected abstract void onPostExecute(HttpResponse httpResponse);
}
