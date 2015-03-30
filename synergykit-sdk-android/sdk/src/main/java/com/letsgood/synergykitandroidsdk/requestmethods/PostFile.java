package com.letsgood.synergykitandroidsdk.requestmethods;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import android.util.Base64;

import com.letsgood.synergykitandroidsdk.SynergyKit;
import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.log.SynergyKitLog;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitUri;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostFile extends RequestMethod {
	/* Constants */
	protected static final String REQUEST_METHOD = "POST";
	protected static final String ATTACHMENT_NAME = "file";
	protected static final String ATTACHMENT_FILE_NAME = "file.jpg";
	protected static final String CRLF = "\r\n";
	protected static final String TWO_HYPHENS = "--";
	protected static final String BOUNDARY =  "*****";
		
	/* Attributes */
	private byte[] data = null;
	private DataOutputStream dataOutputStream = null;
	
	/* Constructor */
	public PostFile(SynergyKitUri uri, byte[] data) {
		super();
		
		setUri(uri);
		this.data = data;
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
			httpURLConnection.addRequestProperty(PROPERTY_USER_AGENT, PROPERTY_USER_AGENT_VALUE); //set property accept
			httpURLConnection.addRequestProperty(PROPERTY_ACCEPT, ACCEPT_APPLICATION_VALUE); //set property accept
			httpURLConnection.addRequestProperty("Content-Type","multipart/form-data;boundary=" + BOUNDARY);
			httpURLConnection.addRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			
			httpURLConnection.addRequestProperty(PROPERTY_AUTHORIZATION, "Basic " 
												 + Base64.encodeToString(
                    (SynergyKit.getTenant() + ":" + SynergyKit.getApplicationKey()).getBytes(),
                    Base64.NO_WRAP)); //set authorization


			httpURLConnection.connect();
			
			
			
			//write data
			if(data!=null){
				dataOutputStream = new DataOutputStream( httpURLConnection.getOutputStream());

				dataOutputStream.writeBytes(TWO_HYPHENS + BOUNDARY + CRLF);
				dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + ATTACHMENT_NAME + "\";filename=\"" + ATTACHMENT_FILE_NAME + "\"" + CRLF);
				dataOutputStream.writeBytes(CRLF);
				dataOutputStream.write(data);
				dataOutputStream.writeBytes(CRLF);
				dataOutputStream.writeBytes(TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + CRLF);
				dataOutputStream.flush();
				dataOutputStream.close();				
			}
			
			statusCode = httpURLConnection.getResponseCode(); //get status code
			SynergyKitLog.print(Integer.toString(statusCode));
			
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
