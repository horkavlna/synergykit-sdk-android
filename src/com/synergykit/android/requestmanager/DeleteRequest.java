package com.synergykit.android.requestmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;
import com.synergykit.android.responsemanager.ResultObjectBuilder;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
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
		
		ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder
		
		HttpResponse httpResponse= requestDelete(getUrl());		//request delete
		
		//if no network connection
		if(httpResponse == null){
							
			return responseDataHolder;
		}
		
		
		responseDataHolder.mStatusCode = httpResponse.getStatusLine().getStatusCode(); //set status code
		
		try {
			BufferedReader data = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
			
			//create response objects
			if(responseDataHolder.mStatusCode>= HttpStatus.SC_OK && responseDataHolder.mStatusCode < HttpStatus.SC_MULTIPLE_CHOICES){
				//empty
			}
			else if(responseDataHolder.mStatusCode>=HttpStatus.SC_INTERNAL_SERVER_ERROR){
				responseDataHolder.mStatusCode = -1;				
			}else{
				responseDataHolder.mErrorObject = ResultObjectBuilder.buildErrorObject(responseDataHolder.mStatusCode, data);

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return responseDataHolder;
	}

	/* On post execute */
	@Override
	protected void onPostExecute(Object object) {
		ResponseManager responseManager = new ResponseManager();
		ResponseDataHolder responseDataHolder = (ResponseDataHolder) object;
		
		responseManager.manageResult(responseDataHolder.mStatusCode,responseDataHolder.mErrorObject, mListener);	
	}

}
