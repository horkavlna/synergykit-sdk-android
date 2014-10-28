package com.letsgood.synergykit.requestmethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.letsgood.synergykit.SynergyKIT;

import android.util.Base64;
import android.util.Log;

public class Get extends RequestMethod {
	/* Constants */
	private static final String REQUEST_METHOD = "GET";
	private static final String PROPERTY_USER_AGENT = "User-Agent";
	private static final String PROPERTY_USER_AGENT_VALUE = "Android";
	private static final String PROPERTY_AUTHORIZATION = "Authorization";

	/* Execute */
	@Override
	public BufferedReader execute() {
	
		
		try {
			url = new URL(getUri().getUri()); // init url
			
			httpURLConnection = (HttpURLConnection) url.openConnection(); //open connection
			httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT); //set connect timeout
			httpURLConnection.setReadTimeout(READ_TIMEOUT); //set read timeout
			httpURLConnection.setRequestMethod(REQUEST_METHOD); //set method
			httpURLConnection.addRequestProperty(PROPERTY_USER_AGENT, PROPERTY_USER_AGENT_VALUE); //set property
			
			
			httpURLConnection.addRequestProperty(PROPERTY_AUTHORIZATION, "Basic " 
												 + Base64.encodeToString(
												(SynergyKIT.getTenant() + ":" + SynergyKIT.getApplicationKey()).getBytes(),
												Base64.NO_WRAP)); //set authorization

			
			statusCode = httpURLConnection.getResponseCode(); //get status code

			//read stream
			if(statusCode>=HttpURLConnection.HTTP_OK && statusCode<HttpURLConnection.HTTP_MULT_CHOICE){
				return readStream(httpURLConnection.getInputStream());
			}else{
				return readStream(httpURLConnection.getErrorStream());
			}
			
		} catch (Exception e) {
			// TODO Exception
			statusCode = RequestMethod.INTERNAL_STATUS_CODE;
			e.printStackTrace();
			return null;
		}
		
	}

}
