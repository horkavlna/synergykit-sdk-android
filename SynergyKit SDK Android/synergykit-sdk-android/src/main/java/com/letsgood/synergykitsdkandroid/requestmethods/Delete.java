package com.letsgood.synergykitsdkandroid.requestmethods;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import android.util.Base64;

import com.letsgood.synergykitsdkandroid.SynergyKit;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergyKitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitUri;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Delete extends RequestMethod {

	/* Constants */
	protected static final String REQUEST_METHOD = "DELETE";

	
	/* Constructor */	
	public Delete(SynergyKitUri uri) {
		super();
		
		setUri(uri);
	} 
	
	/* Execute */
	@Override
	public BufferedReader execute() {
		String uri = null;
		
		//init check
		if(!SynergyKit.isInit()){
			
			SynergyKitLog.print(Errors.MSG_SK_NOT_INITIALIZED);
			statusCode = Errors.SC_SK_NOT_INITIALIZED;
			return null;
		}
		
		//URI check
		uri = getUri().toString();
		
		if(uri==null){
			statusCode = Errors.SC_URI_NOT_VALID;
			return null;
		}
		
			
		try {
			url = new URL(uri); // init url
			
			httpURLConnection = (HttpURLConnection) url.openConnection(); //open connection
			httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT); //set connect timeout
			httpURLConnection.setReadTimeout(READ_TIMEOUT); //set read timeout
			httpURLConnection.setRequestMethod(REQUEST_METHOD); //set method
			httpURLConnection.addRequestProperty(PROPERTY_USER_AGENT, PROPERTY_USER_AGENT_VALUE); //set property
            httpURLConnection.addRequestProperty("Content-Type","application/json");
			httpURLConnection.addRequestProperty(PROPERTY_AUTHORIZATION, "Basic " 
												 + Base64.encodeToString(
                    (SynergyKit.getTenant() + ":" + SynergyKit.getApplicationKey()).getBytes(),
                    Base64.NO_WRAP)); //set authorization

			
			statusCode = httpURLConnection.getResponseCode(); //get status code

			//read stream
			if(statusCode>=HttpURLConnection.HTTP_OK && statusCode<HttpURLConnection.HTTP_MULT_CHOICE){
				return null;
			}else{
				return readStream(httpURLConnection.getErrorStream());
			}
			
		} catch (Exception e) {
			statusCode = Errors.SC_UNSPECIFIED_ERROR;
			e.printStackTrace();
			return null;
		}
		
	}

}
