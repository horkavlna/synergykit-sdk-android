package com.synergykit.sdk.requestmethods;

import android.util.Base64;

import com.synergykit.sdk.SynergyKIT;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.resources.SynergyKITUri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Get extends RequestMethod {

	/* Constants */
	protected static final String REQUEST_METHOD = "GET";

    private boolean authorizationEnabled = true;

    public boolean isAuthorizationEnabled() {
        return authorizationEnabled;
    }

    public void setAuthorizationEnabled(boolean authorizationEnabled) {
        this.authorizationEnabled = authorizationEnabled;
    }

    /* Constructor */
	public Get(SynergyKITUri uri) {
		super();
			
		setUri(uri);
	}
	 
	/* Execute */
	@Override
	public BufferedReader execute() {
		InputStream inputStream = halfExecute();

		if(halfExecute()==null){
			return null;
		}
		
		return readStream(inputStream);
	}
	
	/* Get method */
	public InputStream halfExecute(){
			String uri = null;
		
		//init check
		if(!SynergyKIT.isInit()){
			SynergyKITLog.print(Errors.MSG_SK_NOT_INITIALIZED);
			
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
			httpURLConnection.addRequestProperty("Cache-Control", "max-stale=120");
			httpURLConnection.setDoInput(true);
			httpURLConnection.setUseCaches(true);
			httpURLConnection.setDefaultUseCaches(true);

            if(isAuthorizationEnabled()) {
                httpURLConnection.addRequestProperty(PROPERTY_AUTHORIZATION, "Basic "
                        + Base64.encodeToString(
                        (SynergyKIT.getTenant() + ":" + SynergyKIT.getApplicationKey()).getBytes(),
                        Base64.NO_WRAP)); //set authorization
            }



			
			statusCode = httpURLConnection.getResponseCode(); //get status code
			
			//read stream
			if(statusCode>=HttpURLConnection.HTTP_OK && statusCode<HttpURLConnection.HTTP_MULT_CHOICE){
				return httpURLConnection.getInputStream();
			}else{
				return httpURLConnection.getErrorStream();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
