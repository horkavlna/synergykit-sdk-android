package com.letsgood.synergykit.requestmethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class Get extends RequestMethod {
	/* Constants */
	private static final String REQUEST_METHOD = "GET";
	private static final String PROPERTY_USER_AGENT = "User-Agent";
	private static final String PROPERTY_USER_AGENT_VALUE = "Android";

	/* Execute */
	@Override
	public BufferedReader execute() {
		int statusCode = 0;
		
		
		try {
			//url = new URL(uri.getUri());
			url = new URL("https://synergykit.com");
			httpURLConnection = (HttpURLConnection) url.openConnection();
			//httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
			//httpURLConnection.setReadTimeout(READ_TIMEOUT);	
			httpURLConnection.setRequestMethod(REQUEST_METHOD);
			//httpURLConnection.setRequestProperty(PROPERTY_USER_AGENT, PROPERTY_USER_AGENT_VALUE);
			//httpURLConnection.setDoInput(true);
			httpURLConnection.addRequestProperty("Authorization:", "Basic Zm9vdGJhbGwtdGltZTozY2FjMGU0MC0zMWU4LTExZTQtOWMyNi0zZGM5ODU1MWE4NDg=");

			
			//status code
			statusCode = httpURLConnection.getResponseCode();
			Log.d("SK",url.toString());
			Log.d("SK",Integer.toString(statusCode) + " " + httpURLConnection.getResponseMessage());
			
			//read stream
			if(statusCode>=HttpURLConnection.HTTP_OK && statusCode<HttpURLConnection.HTTP_MULT_CHOICE){
				return readStream(httpURLConnection.getInputStream());
			}else{
				return readStream(httpURLConnection.getErrorStream());
			}
			
		} catch (MalformedURLException e) {
			// TODO Exception
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Exception
			e.printStackTrace();
			return null;
		}
		
	}

}
