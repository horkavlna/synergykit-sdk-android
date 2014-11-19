package com.letsgood.synergykit.request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.HttpStatus;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.listeners.BytesResponseListener;
import com.letsgood.synergykit.log.SynergyKITLog;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITResponse;


public class FileRequestGet extends SynergyKITRequest{

	/* Attributes */
	private SynergyKITConfig config;
	private BytesResponseListener listener;
	 
	/* Config setter */
	public void setConfig(SynergyKITConfig config){
		this.config = config;
	}
	
	/* Listener setter */
	public void setListener(BytesResponseListener listener){
		this.listener =listener;
	}
	

	
	@Override
	protected Object doInBackground(Void... params) {
		ResponseDataHolder dataHolder = null;
		SynergyKITResponse response = null;
		ByteArrayOutputStream byteArrayOutputStream;
		byte[] buffer = null;
		int readInt = 0;
		
		
		//do request
		response = getFile(config.getUri());
		
		//manage response
		if(response.getStatusCode()>=HttpURLConnection.HTTP_OK && response.getStatusCode()<HttpURLConnection.HTTP_MULT_CHOICE){
			
			byteArrayOutputStream = new ByteArrayOutputStream(); //init output stream
			buffer = new byte[1000];
			
			//convert to bytes
			try {
				while(response.getInputStream()!=null && (readInt = response.getInputStream().read(buffer)) != -1){
					byteArrayOutputStream.write(buffer,0,readInt);
				}
				
				
				dataHolder = new ResponseDataHolder(); //init
				dataHolder.statusCode = response.getStatusCode(); //set status code
				dataHolder.data = byteArrayOutputStream.toByteArray(); //convert to byte array
				
				SynergyKITLog.print(dataHolder.data.hashCode() + " " + dataHolder.data.length);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			dataHolder = manageResponseToObject(response, config.getType());
		}
		
		
		
		
		return dataHolder;
	}

	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder dataHolder = (ResponseDataHolder) object;
		
		//null listener 
		if(listener==null){
			SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}	
		
		if(dataHolder.statusCode>= HttpStatus.SC_OK && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			listener.doneCallback(dataHolder.statusCode, dataHolder.data);
		}else{
			listener.errorCallback(dataHolder.statusCode, dataHolder.errorObject);
		}
		
	}

}
