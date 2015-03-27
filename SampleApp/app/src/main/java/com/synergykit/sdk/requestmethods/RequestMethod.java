package com.synergykit.sdk.requestmethods;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.synergykit.sdk.interfaces.IRequestMethod;
import com.synergykit.sdk.resources.SynergyKitUri;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public abstract class RequestMethod implements IRequestMethod {
	/* Constants */
	protected static final String CHARSET = "UTF-8";
	protected static final int CONNECT_TIMEOUT = 5000;
	protected static final int READ_TIMEOUT = 10000;
	protected static final String PROPERTY_USER_AGENT = "User-Agent";
	protected static final String PROPERTY_USER_AGENT_VALUE = "Android";
	protected static final String PROPERTY_AUTHORIZATION = "Authorization";
	protected static final String PROPERTY_ACCEPT = "Accept";
	protected static final String ACCEPT_APPLICATION_VALUE = "application/json";
	
	
	/* Attributes */
	protected SynergyKitUri uri = null;
	protected URL url = null;
	protected HttpURLConnection httpURLConnection = null;
	protected int statusCode = 0;
	
	/* Status code getter */
	public int getStatusCode() {
		return statusCode;
	}

	/* Uri getter */
	public SynergyKitUri getUri() {
		return uri;
	}
	
	/* Uri setter */
	public void setUri(SynergyKitUri uri) {
		this.uri = uri;
	}
	
	/* Read input stream */
	protected BufferedReader readStream(InputStream inputStream){
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
			
		
		if(inputStream==null)
			return bufferedReader;
		
		try {
			inputStreamReader = new InputStreamReader(inputStream, CHARSET);
			bufferedReader = new BufferedReader(inputStreamReader);
		} catch (UnsupportedEncodingException e) {
			//TODO EXCEPTION
			e.printStackTrace();
		}
		
		return bufferedReader;
	}
	
	
}
