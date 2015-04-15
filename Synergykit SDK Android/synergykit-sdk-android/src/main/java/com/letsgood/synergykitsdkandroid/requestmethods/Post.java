package com.letsgood.synergykitsdkandroid.requestmethods;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import android.util.Base64;

import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import org.apache.http.HttpStatus;

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
	public Post(SynergykitUri uri, Object object) {
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
		if(!Synergykit.isInit()){
			SynergykitLog.print(Errors.MSG_SK_NOT_INITIALIZED);
			
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
			httpURLConnection.addRequestProperty(PROPERTY_CONTENT_TYPE,ACCEPT_APPLICATION_VALUE);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			
			httpURLConnection.addRequestProperty(PROPERTY_AUTHORIZATION, "Basic " 
												 + Base64.encodeToString(
                    (Synergykit.getTenant() + ":" + Synergykit.getApplicationKey()).getBytes(),
                    Base64.NO_WRAP)); //set authorization

            if(Synergykit.getToken()!=null)
                httpURLConnection.addRequestProperty(PROPERTY_SESSION_TOKEN, Synergykit.getToken());

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
            statusCode = HttpStatus.SC_SERVICE_UNAVAILABLE;
			e.printStackTrace();
			return null;
		}
		
	}

}
