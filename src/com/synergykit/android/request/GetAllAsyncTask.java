package com.synergykit.android.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;

import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.GetAllResponseListener;

/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class GetAllAsyncTask extends AsyncTask<Void, Void, Void>{
	/* Attributes */
	protected GetAllResponseListener mListener;
	protected String mUrl;
	protected Type mType;
	
	
	/* Url setter */
	public void setUrl(String url){
		mUrl = url;
	}
	
	/* Listener setter */
	public void setListener(GetAllResponseListener listener){
		mListener =listener;
	}
	
	/* Type setter */
	public void setType(Type type){
		mType = type;
	}	
	
	/* Do in background */
	@Override
	protected Void doInBackground(Void... params) {
		Get get = new Get(mUrl) {};
		HttpResponse httpResponse;
		

		try {
			//post data
			httpResponse = get.execute();
			
			//Listener check
			if(mListener==null)
				return null;
			
			//callback result
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)				
				mListener.doneCallback(httpResponse, ResultObjectBuilder.buildBaseObjects(httpResponse, mType));
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
