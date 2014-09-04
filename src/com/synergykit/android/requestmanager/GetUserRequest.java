package com.synergykit.android.requestmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.resource.BaseUser;
import com.synergykit.android.response.BaseUserResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;
import com.synergykit.android.responsemanager.ResultObjectBuilder;

public class GetUserRequest extends BaseRequestAsyncTask {
	/* Attributes */
	private BaseUserResponseListener mListener;
	private Type mType;
	
	
	
	public Type getType() {
		return mType;
	}

	public void setType(Type type) {
		this.mType = type;
	}

	/* Listener setter */
	public void setListener(BaseUserResponseListener listener){
		mListener =listener;
	}

	/* Do  in background */
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder
		
		HttpResponse httpResponse= requestGet(getUrl()); //request
		
		//if no network connection
		if(httpResponse == null){
			responseDataHolder.mStatusCode = -1;				
			return responseDataHolder;
		}
		
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
									(BaseUser) responseDataHolder.mObject,
										responseDataHolder.mErrorObject,
										mListener,
										mType);
	}

}

