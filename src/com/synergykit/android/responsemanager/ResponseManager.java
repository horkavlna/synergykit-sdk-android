package com.synergykit.android.responsemanager;

import java.io.BufferedReader;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.util.Log;

import com.synergykit.android.resource.BaseUser;
import com.synergykit.android.resource.SynergyKITBaseObject;
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
	private void emptyHttpResponse( BaseResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);		
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	/* Empty http response */
	private void emptyHttpResponse(GetRecordsResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);		
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	/* Empty http response */
	private void emptyHttpResponse(DeleteResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);
				
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	/* Empty http response */
	private void emptyHttpResponse( GetUsersResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);
				
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	/* Empty http response */
	private void emptyHttpResponse( BaseUserResponseListener listener){
		SynergyKITErrorObject errorObject= new SynergyKITErrorObject();
		errorObject.setStatus(Integer.toString(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE));
		errorObject.setMessage(ErrorMessages.NETWORK_CONNECTION_ERROR_MESSAGE);
				
		listener.errorCallback(ErrorMessages.NETWORK_CONNECTION_ERROR_CODE, errorObject);
		return;
	}
	
	
	//--------------------------------------------------------------------------------------------------------
	/* Manage Result */
	public void manageResult(int statusCode, SynergyKITBaseObject baseObject, SynergyKITErrorObject errorObject, BaseResponseListener listener, Type type){
		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if(statusCode>=HttpStatus.SC_INTERNAL_SERVER_ERROR){
			this.emptyHttpResponse(listener);
			return;
		}

		//
		//callback result
		if(statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)				
			listener.doneCallback(statusCode,baseObject);
		else
			listener.errorCallback(statusCode,errorObject);
			
		
	}

	/* Manage results */
	public void manageResult(int statusCode, SynergyKITErrorObject errorObject, DeleteResponseListener listener){

		
		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if( statusCode>=HttpStatus.SC_INTERNAL_SERVER_ERROR || statusCode==-1){
			this.emptyHttpResponse( listener);
			return;
		}
		Log.e("SynergyKIT",Integer.toString(statusCode));
		//
		//callback result
		if(statusCode>= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)				
			listener.doneCallback(statusCode);
		else
			listener.errorCallback(statusCode,errorObject);
			
		
	}
	
	/* Manage result */
	public void manageResult(int statusCode, SynergyKITBaseObject[] baseObjects, SynergyKITErrorObject errorObject, GetRecordsResponseListener listener, Type type){

		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if(statusCode>=HttpStatus.SC_INTERNAL_SERVER_ERROR || statusCode==-1){
			this.emptyHttpResponse( listener);
			return;
		}
		Log.e("SynergyKIT",Integer.toString(statusCode));
		
		//
		//callback result
		if(statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)				
			listener.doneCallback(statusCode, baseObjects);
		else
			listener.errorCallback(statusCode, errorObject);
			
		
	}

	/* Manage result */
	public void manageResult(int statusCode, BaseUser baseUser, SynergyKITErrorObject errorObject, BaseUserResponseListener listener, Type type){
		
		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if( statusCode>=HttpStatus.SC_INTERNAL_SERVER_ERROR || statusCode==-1){
			this.emptyHttpResponse(listener);
			return;
		}
		Log.e("SynergyKIT",Integer.toString(statusCode));
		//
		//callback result
		if(statusCode>= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)			
			listener.doneCallback(statusCode,baseUser);
		
		else
			listener.errorCallback(statusCode,errorObject);
			
	}
	
	/* Manage result */
	public void manageResult(int statusCode,BaseUser[] baseUsers, SynergyKITErrorObject errorObject, GetUsersResponseListener listener, Type type){
		
		//Empty listener
		if(listener == null)
			return;
		
		//Empty http response
		if(statusCode>=HttpStatus.SC_INTERNAL_SERVER_ERROR || statusCode==-1){
			this.emptyHttpResponse( listener);
			return;
		}
		Log.e("SynergyKIT",Integer.toString(statusCode));
		//
		//callback result
		if(statusCode>= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES)				
			listener.doneCallback(statusCode,baseUsers);
		else
			listener.errorCallback(statusCode,errorObject);
			
	}
	
}
