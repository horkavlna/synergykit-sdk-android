package com.letsgood.synergykit.request;

import org.apache.http.HttpStatus;

import android.util.Log;

import com.letsgood.synergykit.SynergyKIT;
import com.letsgood.synergykit.SynergyKITSdk;
import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.listeners.DeleteResponseListener;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITResponse;

public class RequestDelete extends SynergyKITRequest{
	/* Attributes */
	private SynergyKITConfig config;
	private DeleteResponseListener listener;
	
	/* Config setter */
	public void setConfig(SynergyKITConfig config){
		this.config = config;
	}
	
	/* Listener setter */
	public void setListener(DeleteResponseListener listener){
		this.listener =listener;
	}
	

	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergyKITResponse response = null;
		
		//do request
		response = delete(config.getUri());
		
		//manage response
		dataHolder = manageResponseToObject(response, config.getType());
		
		
		
		return dataHolder;
	}

	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder dataHolder = (ResponseDataHolder) object;
		
		//null listener 
		if(listener==null){
			if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NO_CALLBACK_LISTENER);
			return;
		}
			
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}
}