package com.letsgood.synergykit.request;

import java.io.BufferedReader;

import org.apache.http.HttpStatus;

import android.util.Log;

import com.letsgood.synergykit.builders.ResultObjectBuilder;
import com.letsgood.synergykit.listeners.RecordsResponseListener;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.requestmethods.Get;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITResponse;


public class RecordsRequestGet extends SynergyKITRequest{

	/* Attributes */
	private SynergyKITConfig config;
	private RecordsResponseListener listener;
	
	/* Config setter */
	public void setConfig(SynergyKITConfig config){
		this.config = config;
	}
	
	/* Listener setter */
	public void setListener(RecordsResponseListener listener){
		this.listener =listener;
	}
	

	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergyKITResponse response = null;
		
		//do request
		response = get(config.getUri());
		
		//manage response
		dataHolder = manageObjectsResponse(response, config.getType());		
		
		return dataHolder;
	}

	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder dataHolder = (ResponseDataHolder) object;
		
		Log.i("SK",Integer.toString(dataHolder.statusCode));
	}

}
