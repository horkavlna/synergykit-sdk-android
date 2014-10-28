package com.letsgood.synergykit.request;

import java.io.BufferedReader;
import java.lang.reflect.Type;

import org.apache.http.HttpStatus;

import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.requestmethods.Get;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.synergykit.android.responsemanager.ResultObjectBuilder;

public class GetRecordRequest extends SynergyKITRequest{

	/* Attributes */
	private SynergyKITConfig config;
	private ResponseListener listener;
	
	/* Config setter */
	public void setConfig(SynergyKITConfig config){
		this.config = config;
	}
	
	/* Listener setter */
	public void setListener(ResponseListener listener){
		this.listener =listener;
	}
	

	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder
		Get get = new Get(); //request method get
		get.setUri(config.getUri()); //set uri
		
		BufferedReader bufferedReader = get.execute();
		responseDataHolder.statusCode = get.getStatusCode();
		
		if(responseDataHolder.statusCode>= HttpStatus.SC_OK && responseDataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			responseDataHolder.object = ResultObjectBuilder.buildBaseObject(responseDataHolder.statusCode, bufferedReader,config.getType());
		}
		else if(responseDataHolder.statusCode>=HttpStatus.SC_INTERNAL_SERVER_ERROR){
			responseDataHolder.statusCode = -1;				
		}
		else{
			responseDataHolder.errorObject = ResultObjectBuilder.buildErrorObject(responseDataHolder.statusCode, bufferedReader);
		
		
		
		return null;
	}

	@Override
	protected void onPostExecute(Object object) {
		// TODO Auto-generated method stub
		
	}

}
