package com.letsgood.synergykit.requestmethods;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Base64;

import com.letsgood.synergykit.SynergyKIT;
import com.letsgood.synergykit.addons.GsonWrapper;
import com.letsgood.synergykit.resources.SynergyKITUri;

public class Put extends RequestMethod{

	/* Constants */
	protected static final String REQUEST_METHOD = "PUT";
	
	/* Attributes */
	private Object object = null;
	private DataOutputStream dataOutputStream = null;
	
	/* Constructor */
	public Put(SynergyKITUri uri, Object object) {
		super();
		
		setUri(uri);
		this.object = object;
		
	}
	
	/* Execute */
	@Override
	public BufferedReader execute() {
		String jSon = null;
		
		try {
			url = new URL(getUri().getUri()); // init url
			
			httpURLConnection = (HttpURLConnection) url.openConnection(); //open connection
			httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT); //set connect timeout
			httpURLConnection.setReadTimeout(READ_TIMEOUT); //set read timeout
			httpURLConnection.setRequestMethod(REQUEST_METHOD); //set method
			httpURLConnection.addRequestProperty(PROPERTY_USER_AGENT, PROPERTY_USER_AGENT_VALUE); //set property accept
			httpURLConnection.addRequestProperty(PROPERTY_ACCEPT, PROPERTY_ACCEPT_VALUE); //set property accept
			httpURLConnection.addRequestProperty("Content-Type","application/json");
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			
			httpURLConnection.addRequestProperty(PROPERTY_AUTHORIZATION, "Basic " 
												 + Base64.encodeToString(
												(SynergyKIT.getTenant() + ":" + SynergyKIT.getApplicationKey()).getBytes(),
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
