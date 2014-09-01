package com.synergykit.android.resource;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;



import com.synergykit.android.SynergyKIT;
import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetRecordsResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;
import com.synergykit.android.responsemanager.ResultObjectBuilder;
import com.synergykit.android.urlbuilder.Url;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public abstract class SynergylizeRequestAsyncTask extends BaseRequestAsyncTask {

	/* Attribute */
	private ResponseManager mResponseManager = new ResponseManager();
	
	/* Post */
	protected void post(Url url,Object object, BaseResponseListener listener, Type type){	

		
		//init check

		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		

		
		ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder		
		HttpResponse httpResponse = requestPost(url, object); //request
		responseDataHolder.mStatusCode = httpResponse.getStatusLine().getStatusCode(); //set status code
		
		try {
			BufferedReader data = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
						
			//create response objects
			if(responseDataHolder.mStatusCode>= HttpStatus.SC_OK && responseDataHolder.mStatusCode < HttpStatus.SC_MULTIPLE_CHOICES){
				responseDataHolder.mObject = ResultObjectBuilder.buildBaseObject(responseDataHolder.mStatusCode, data,type);
			}else{
				responseDataHolder.mErrorObject = ResultObjectBuilder.buildErrorObject(responseDataHolder.mStatusCode, data);

			}
			
		} catch (ParseException e) {
			//empty
			e.printStackTrace();
		} catch (IOException e) {
			// empty
			e.printStackTrace();
		}
		
		mResponseManager.manageResult(responseDataHolder.mStatusCode,
									  (SynergyKITBaseObject)responseDataHolder.mObject,
									  responseDataHolder.mErrorObject,
									  listener,
									  type);		

		
	}
	
	/* Put */
	protected void put(Url url, Object object, BaseResponseListener listener, Type type){	


		//init check

		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}

		
		
		ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder		
		HttpResponse httpResponse = requestPut(url, object); //request
		responseDataHolder.mStatusCode = httpResponse.getStatusLine().getStatusCode(); //set status code
		
		try {
			BufferedReader data = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
						
			//create response objects
			if(responseDataHolder.mStatusCode>= HttpStatus.SC_OK && responseDataHolder.mStatusCode < HttpStatus.SC_MULTIPLE_CHOICES){
				responseDataHolder.mObject = ResultObjectBuilder.buildBaseObject(responseDataHolder.mStatusCode, data,type);
			}else{
				responseDataHolder.mErrorObject = ResultObjectBuilder.buildErrorObject(responseDataHolder.mStatusCode, data);

			}
			
		} catch (ParseException e) {
			// empty
			e.printStackTrace();
		} catch (IOException e) {
			// empty
			e.printStackTrace();
		}
		
		mResponseManager.manageResult(responseDataHolder.mStatusCode,
									  (SynergyKITBaseObject)responseDataHolder.mObject,
									  responseDataHolder.mErrorObject,
									  listener,
									  type);		

		
	}
	
	/* Get */
	protected void get(Url url, BaseResponseListener listener, Type type){


		//init check

		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		

		ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder		
		HttpResponse httpResponse = requestGet(url); //request
		responseDataHolder.mStatusCode = httpResponse.getStatusLine().getStatusCode(); //set status code
		
		try {
			BufferedReader data = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
						
			//create response objects
			if(responseDataHolder.mStatusCode>= HttpStatus.SC_OK && responseDataHolder.mStatusCode < HttpStatus.SC_MULTIPLE_CHOICES){
				responseDataHolder.mObject = ResultObjectBuilder.buildBaseObject(responseDataHolder.mStatusCode, data,type);
			}else{
				responseDataHolder.mErrorObject = ResultObjectBuilder.buildErrorObject(responseDataHolder.mStatusCode, data);

			}
			
		} catch (ParseException e) {
			// empty
			e.printStackTrace();
		} catch (IOException e) {
			// empty
			e.printStackTrace();
		}
		
		mResponseManager.manageResult(responseDataHolder.mStatusCode,
									  (SynergyKITBaseObject)responseDataHolder.mObject,
									  responseDataHolder.mErrorObject,
									  listener,
									  type);		
		

	}
	
	/* Get all */
	protected void get(Url url, GetRecordsResponseListener listener, Type type){


		//init check

		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}

		
		ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder		
		HttpResponse httpResponse = requestGet(url); //request
		responseDataHolder.mStatusCode = httpResponse.getStatusLine().getStatusCode(); //set status code
		
		try {
			BufferedReader data = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
						
			//create response objects
			if(responseDataHolder.mStatusCode>= HttpStatus.SC_OK && responseDataHolder.mStatusCode < HttpStatus.SC_MULTIPLE_CHOICES){
				responseDataHolder.mObject = ResultObjectBuilder.buildBaseObjects(responseDataHolder.mStatusCode, data,type);
			}else{
				responseDataHolder.mErrorObject = ResultObjectBuilder.buildErrorObject(responseDataHolder.mStatusCode, data);

			}
			
		} catch (ParseException e) {
			// empty
			e.printStackTrace();
		} catch (IOException e) {
			// empty
			e.printStackTrace();
		}
		
		mResponseManager.manageResult(responseDataHolder.mStatusCode,
									  (SynergyKITBaseObject[])responseDataHolder.mObject,
									  responseDataHolder.mErrorObject,
									  listener,
									  type);		
		

	}

	/* Delete */
	protected void delete(Url url, DeleteResponseListener listener){


		//init check

		try {
			this.initCheck();
		} catch (NotInitializedException e) {
			e.printStackTrace();
			return;
		}
		

		
		ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder		
		HttpResponse httpResponse = requestDelete(url); //request
		responseDataHolder.mStatusCode = httpResponse.getStatusLine().getStatusCode(); //set status code
		
		try {
			BufferedReader data = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
						
			//create response objects
			if(responseDataHolder.mStatusCode>= HttpStatus.SC_OK && responseDataHolder.mStatusCode < HttpStatus.SC_MULTIPLE_CHOICES){
				//empty
			}else{
				responseDataHolder.mErrorObject = ResultObjectBuilder.buildErrorObject(responseDataHolder.mStatusCode, data);

			}
			
		} catch (ParseException e) {
			// empty
			e.printStackTrace();
		} catch (IOException e) {
			// empty
			e.printStackTrace();
		}
		
		mResponseManager.manageResult(responseDataHolder.mStatusCode,
									  responseDataHolder.mErrorObject,
									  listener);		
		

	}
	
	/* initialization check */
	public void initCheck() throws NotInitializedException{
		if(!SynergyKIT.isInit())
			throw new NotInitializedException();
	}
}
