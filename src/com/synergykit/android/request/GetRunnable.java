package com.synergykit.android.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;

import com.synergykit.android.resource.ISynergykitResponseListener;

public class GetRunnable implements Runnable{

	/* Attributes */
	public ISynergykitResponseListener mListener;
	public String mUrl;
	public Class<?> mClassOfT;
	
	/* Run */
	@Override
	public void run() {
		Get get = new Get(mUrl) {};
		HttpResponse httpResponse;
		
		//Listener check
		if(mListener==null){
			throw new NullPointerException();
		}
		
		try {
			//post data
			httpResponse = get.execute();
			
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
