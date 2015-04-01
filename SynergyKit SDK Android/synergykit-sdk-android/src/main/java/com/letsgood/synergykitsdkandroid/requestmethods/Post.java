package com.letsgood.synergykitsdkandroid.requestmethods;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import android.util.Base64;

import com.letsgood.synergykitsdkandroid.SynergyKit;
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergyKitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitUri;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post extends RequestMethod {

	/* Constants */
	protected static final String REQUEST_METHOD = "POST";
		
	/* Attributes */
	private Object object = null;
	private DataOutputStream dataOutputStream = null;
	
	/* Constructor */
	public Post(SynergyKitUri uri, Object object) {
		super();
		
		setUri(uri);
		this.object = object;
	}

	/* Execute */
	@Override
	public BufferedReader execute() {
		String jSon = null;
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
			httpURLConnection.addRequestProperty(PROPERTY_USER_AGENT, PROPERTY_USER_AGENT_VALUE); //set property accept
			httpURLConnection.addRequestProperty(PROPERTY_ACCEPT, ACCEPT_APPLICATION_VALUE); //set property accept
			httpURLConnection.addRequestProperty("Content-Type","application/json");
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			
			httpURLConnection.addRequestProperty(PROPERTY_AUTHORIZATION, "Basic " 
												 + Base64.encodeToString(
                    (SynergyKit.getTenant() + ":" + SynergyKit.getApplicationKey()).getBytes(),
                    Base64.NO_WRAP)); //set authorization


			httpURLConnection.connect();
			
			
			
			//write data
			if(object!=null){
				jSon = GsonWrapper.getGson().toJson(object);
				dataOutputStream = new DataOutputStream( httpURLConnection.getOutputStream());
				dataOutputStream.write(jSon.getBytes(CHARSET));
				dataOutputStream.flush();
				dataOutputStream.close();				
			}
			
			statusCode = httpURLConnection.getResponseCode(); //get status code

			//read stream
			if(statusCode>=HttpURLConnection.HTTP_OK && statusCode<HttpURLConnection.HTTP_MULT_CHOICE){
				return readStream(httpURLConnection.getInputStream());
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
