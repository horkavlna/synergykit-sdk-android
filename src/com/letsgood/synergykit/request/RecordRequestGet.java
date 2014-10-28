package com.letsgood.synergykit.request;

import org.apache.http.HttpStatus;

import com.letsgood.synergykit.builders.ResultObjectBuilder;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.requestmethods.RequestMethod;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITResponse;


public class RecordRequestGet extends SynergyKITRequest{

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
		ResponseDataHolder dataHolder = new ResponseDataHolder();
		SynergyKITResponse response = null;
		
		//do request
		response = get(config.getUri());
		
		//set status code
		dataHolder.statusCode=response.getStatusCode();
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			dataHolder.object = ResultObjectBuilder.buildObject(dataHolder.statusCode, response.getBufferedReader(),config.getType());
		}
		else if(dataHolder.statusCode>=HttpStatus.SC_INTERNAL_SERVER_ERROR || dataHolder.statusCode == RequestMethod.INTERNAL_STATUS_CODE ){
			dataHolder.errorObject = ResultObjectBuilder.buildError(dataHolder.statusCode);				
		}
		else{
			dataHolder.errorObject = ResultObjectBuilder.buildError(dataHolder.statusCode, response.getBufferedReader());
		}		
		
		return dataHolder;
	}

	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder dataHolder = (ResponseDataHolder) object;
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode, dataHolder.object);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}

}
