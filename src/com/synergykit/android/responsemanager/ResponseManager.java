package com.synergykit.android.responsemanager;

import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.util.Log;

import com.synergykit.android.requestmanager.ResultObjectBuilder;
import com.synergykit.android.resource.BaseUser;
import com.synergykit.android.resource.SynergyKITErrorObject;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.BaseUserResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetRecordsResponseListener;
import com.synergykit.android.response.GetUsersResponseListener;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class ResponseManager {

	/* Empty http response */
	private void emptyHttpResponse(HttpResponse httpResponse, BaseResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);		
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	/* Empty http response */
	private void emptyHttpResponse(HttpResponse httpResponse,GetRecordsResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);		
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	/* Empty http response */
	private void emptyHttpResponse(HttpResponse httpResponse, DeleteResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);
				
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	/* Empty http response */
	private void emptyHttpResponse(HttpResponse httpResponse, GetUsersResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);
				
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	/* Empty http response */
	private void emptyHttpResponse(HttpResponse httpResponse, BaseUserResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);
				
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	
	//--------------------------------------------------------------------------------------------------------
	/* Manage Result */
	public void manageResult(HttpResponse httpResponse, BaseResponseListener listener, Type type){
		int statusCode;
		
		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if(httpResponse==null){
			this.emptyHttpResponse(httpResponse, listener);
			return;
		}
		
		//get status code
		statusCode = httpResponse.getStatusLine().getStatusCode();

		//
		//callback result
		if(statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)				
			listener.doneCallback(httpResponse.getStatusLine().getStatusCode(), ResultObjectBuilder.buildBaseObject(httpResponse, type));
		else
			listener.errorCallback(httpResponse.getStatusLine().getStatusCode(), ResultObjectBuilder.buildErrorObject(httpResponse));
			
		
	}

	/* Manage results */
	public void manageResult(HttpResponse httpResponse, DeleteResponseListener listener){
		int statusCode;
		
		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if(httpResponse==null){
			this.emptyHttpResponse(httpResponse, listener);
			return;
		}
		
		//get status code
		statusCode = httpResponse.getStatusLine().getStatusCode();
		
		//
		//callback result
		if(statusCode>= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)				
			listener.doneCallback(httpResponse.getStatusLine().getStatusCode());
		else
			listener.errorCallback(httpResponse.getStatusLine().getStatusCode(),ResultObjectBuilder.buildErrorObject(httpResponse));
			
		
	}
	
	/* Manage result */
	public void manageResult(HttpResponse httpResponse, GetRecordsResponseListener listener, Type type){
		int statusCode;
		
		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if(httpResponse==null){
			this.emptyHttpResponse(httpResponse, listener);
			return;
		}
		
		//get status code
		statusCode = httpResponse.getStatusLine().getStatusCode();
		
		
		//
		//callback result
		if(statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)				
			listener.doneCallback(httpResponse.getStatusLine().getStatusCode(), ResultObjectBuilder.buildBaseObjects(httpResponse, type));
		else
			listener.errorCallback(httpResponse.getStatusLine().getStatusCode(), ResultObjectBuilder.buildErrorObject(httpResponse));
			
		
	}

	/* Manage result */
	public void manageResult(HttpResponse httpResponse, BaseUserResponseListener listener, Type type){
		int statusCode;
		
		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if(httpResponse==null){
			this.emptyHttpResponse(httpResponse, listener);
			return;
		}
		
		//get status code
		statusCode = httpResponse.getStatusLine().getStatusCode();
		
		Log.e("Synergykit",Integer.toString(statusCode));
		
		//
		//callback result
		if(statusCode>= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)			
			listener.doneCallback(httpResponse.getStatusLine().getStatusCode(),(BaseUser)(ResultObjectBuilder.buildBaseObject(httpResponse, type)));
		
		else
			listener.errorCallback(httpResponse.getStatusLine().getStatusCode(),(ResultObjectBuilder.buildErrorObject(httpResponse)));
			
	}
	
	/* Manage result */
	public void manageResult(HttpResponse httpResponse, GetUsersResponseListener listener, Type type){
		int statusCode;
		
		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if(httpResponse==null){
			this.emptyHttpResponse(httpResponse, listener);
			return;
		}
		
		//get status code
		statusCode = httpResponse.getStatusLine().getStatusCode();
		
		//
		//callback result
		if(statusCode>= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)				
			listener.doneCallback(httpResponse.getStatusLine().getStatusCode(),(BaseUser[])(ResultObjectBuilder.buildBaseObjects(httpResponse, type)));
		else
			listener.errorCallback(httpResponse.getStatusLine().getStatusCode(),ResultObjectBuilder.buildErrorObject(httpResponse));
			
	}
	
}
