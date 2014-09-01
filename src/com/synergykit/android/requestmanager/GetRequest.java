package com.synergykit.android.requestmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.resource.SynergyKITBaseObject;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;
import com.synergykit.android.responsemanager.ResultObjectBuilder;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class GetRequest  extends BaseRequestAsyncTask{
	
	/* Attributes */
	private Type mType;
	private BaseResponseListener mListener;
	
	/* Listener setter */
	public void setListener(BaseResponseListener listener){
		mListener =listener;
	}
	
	/* Type setter */
	public void setType(Type type){
		mType = type;
	}	
	

	/* Do in background */
	@Override
	protected Object doInBackground(Void... params) {

		ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder
		
		HttpResponse httpResponse= requestGet(getUrl()); //request
		responseDataHolder.mStatusCode = httpResponse.getStatusLine().getStatusCode(); //set status code
		
		try {
			BufferedReader data = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
			
			//create response objects
			if(responseDataHolder.mStatusCode>= HttpStatus.SC_OK && responseDataHolder.mStatusCode < HttpStatus.SC_MULTIPLE_CHOICES){
				responseDataHolder.mObject = ResultObjectBuilder.buildBaseObject(responseDataHolder.mStatusCode, data,mType);
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
		
		responseManager.manageResult(responseDataHolder.mStatusCode,
									 (SynergyKITBaseObject) responseDataHolder.mObject,
									 responseDataHolder.mErrorObject,
									 mListener,
									 mType);

	}



	

}
