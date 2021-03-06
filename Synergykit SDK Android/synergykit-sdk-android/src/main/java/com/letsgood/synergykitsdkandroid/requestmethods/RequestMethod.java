package com.letsgood.synergykitsdkandroid.requestmethods;






/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */



import com.letsgood.synergykitsdkandroid.interfaces.IRequestMethod;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

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
    protected static final String PROPERTY_CONTENT_TYPE = "Content-Type";
    protected static final String PROPERTY_SESSION_TOKEN = "SessionToken";
	
	/* Attributes */
	protected SynergykitUri uri = null;
	protected URL url = null;
	protected HttpURLConnection httpURLConnection = null;
	protected int statusCode = 0;
	
	/* Status code getter */
	public int getStatusCode() {
		return statusCode;
	}

	/* Uri getter */
	public SynergykitUri getUri() {
		return uri;
	}
	
	/* Uri setter */
	public void setUri(SynergykitUri uri) {
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
