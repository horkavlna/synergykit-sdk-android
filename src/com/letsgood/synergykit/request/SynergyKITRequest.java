package com.letsgood.synergykit.request;

import java.io.BufferedReader;

import com.letsgood.synergykit.requestmethods.Get;
import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITResponse;
import com.letsgood.synergykit.resources.SynergyKITUri;

import android.os.AsyncTask;

public abstract class SynergyKITRequest extends AsyncTask<Void, Void, Object> {

	/* Do in background */
	@Override
	protected abstract Object doInBackground(Void... params);
	
	/* On post execute */
	@Override
	protected abstract void onPostExecute(Object object);

	/* Request method post */
	public static SynergyKITResponse get(SynergyKITUri uri){
		SynergyKITResponse response = new SynergyKITResponse();
		Get get = new Get(); //request method get
		get.setUri(uri); //set uri
		
		response.setBufferedReader(get.execute());
		response.setStatusCode(get.getStatusCode());		
		
		return response;
	}
	
	//----------------------------------------------------------------------------------
	protected class ResponseDataHolder{
		/* Attributes */
		public SynergyKITError errorObject;
		public Object object;
		public int statusCode;
		public BufferedReader bufferedReader;
		
		/* Constructor */
		public ResponseDataHolder() {
			statusCode = -1;
			errorObject = null;
			object = null;
			bufferedReader = null;
		}
	}
}
