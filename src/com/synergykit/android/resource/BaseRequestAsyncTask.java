package com.synergykit.android.resource;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import android.os.AsyncTask;

import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.request.Delete;
import com.synergykit.android.request.Get;
import com.synergykit.android.request.Post;
import com.synergykit.android.request.Put;
import com.synergykit.android.urlbuilder.Url;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public abstract class BaseRequestAsyncTask extends AsyncTask<Void, Void, Object>{

	/* Attribute */
	protected Url mUrl;
	
	/* Url setter */
	public void setUrl(Url url){
		mUrl = url;
	}
	
	/* Url getter */
	public Url getUrl(){
		return mUrl;
	}

	/* Post request */
	protected HttpResponse requestPost(Url url, Object object){
		String json = GsonWrapper.getInstance().getGson().toJson(object);
		Post request = new Post(url.getUrl()){};
		
		try {
			return request.execute(new StringEntity(json, "UTF-8"),"application/json");		
			//return request.execute(new ByteArrayEntity(json.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/* Post with custom Entity request */
	protected HttpResponse requestPostWithEntity(Url url, HttpEntity entity){
		Post request = new Post(url.getUrl()){};
		
		try {
			return request.execute(entity,null);		
			//return request.execute(new ByteArrayEntity(json.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/* Put request */
	protected HttpResponse requestPut(Url url, Object object){
		String json = GsonWrapper.getInstance().getGson().toJson(object);
		Put request = new Put(url.getUrl()){};
		try {
			return request.execute(new StringEntity(json, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/* Get request */
	protected HttpResponse requestGet(Url url){
		Get request = new Get(url.getUrl()){};
		try {
			return request.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/* Delete request */
	protected HttpResponse requestDelete(Url url){
		Delete request = new Delete(url.getUrl()){};
		try {
			return request.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/* Do in background */
	@Override
	protected abstract Object doInBackground(Void... params);
	
	/* On post execute */
	@Override
	protected abstract void onPostExecute(Object object);
	
	//----------------------------------------------------------------------------------
	protected class ResponseDataHolder{
		/* Constructor */
		public ResponseDataHolder() {
			mStatusCode = -1;
			mErrorObject = null;
			mObject = null;
		}
		
		/* Attributes */
		public SynergyKITErrorObject mErrorObject;
		public Object mObject;
		public int mStatusCode;
	}
}
