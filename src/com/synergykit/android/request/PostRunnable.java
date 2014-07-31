package com.synergykit.android.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;

import com.synergykit.android.resource.ISynergykitResponseListener;

public class PostRunnable implements Runnable{

	/*Attributes */
	public ISynergykitResponseListener mListener;
	public String mUrl;
	public String mJson;
	public Class<?> mClassOfT;
	

	
	
	/* Run */
	@Override
	public void run() {
		Post post = new Post(mUrl){};
		HttpResponse httpResponse;
		
		//Listener check
		if(mListener==null)
			return;
		
		
		try {
			//post data
			httpResponse = post.execute(new StringEntity(mJson, "UTF-8"));
			
			//callback result
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)				
				mListener.doneCallback(httpResponse, ResultObjectBuilder.buildBaseObject(httpResponse, mClassOfT));
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
		
	}	
}
