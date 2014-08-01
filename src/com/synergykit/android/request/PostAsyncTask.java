package com.synergykit.android.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;

import android.os.AsyncTask;

import com.synergykit.android.response.BaseResponseListener;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class PostAsyncTask extends AsyncTask<Void, Void, Void>{

	/*Attributes */
	private BaseResponseListener mListener;
	private String mUrl;
	private String mJson;
	private Type mType;
	
	/* Url setter */
	public void setUrl(String url){
		mUrl = url;
	}
	
	/* Json setter */
	public void setJson(String json){
		mJson = json;
	}
	
	/* Listener setter */
	public void setListener(BaseResponseListener listener){
		mListener =listener;
	}
	
	/* ClassOf setter */
	public void setType(Type type){
		mType = type;
	}
	
	
	
	/* Do  in background */
	@Override
	protected Void doInBackground(Void... params) {
		Post post = new Post(mUrl){};
		HttpResponse httpResponse;
		
		try {
			//post data
			httpResponse = post.execute(new StringEntity(mJson, "UTF-8"));
			
			//Listener check
			if(mListener==null)
				return null;
			
			//callback result
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)				
				mListener.doneCallback(httpResponse, ResultObjectBuilder.buildBaseObject(httpResponse, mType));
			else
				mListener.errorCallback(httpResponse, ResultObjectBuilder.buildErrorObject(httpResponse));
		
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
