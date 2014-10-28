package com.letsgood.synergykit.request;

import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITUri;

import android.os.AsyncTask;

public abstract class SynergyKITRequest extends AsyncTask<Void, Void, Object> {

	/* Do in background */
	@Override
	protected abstract Object doInBackground(Void... params);
	
	/* On post execute */
	@Override
	protected abstract void onPostExecute(Object object);

	
	//----------------------------------------------------------------------------------
	protected class ResponseDataHolder{
		/* Attributes */
		public SynergyKITError errorObject;
		public Object object;
		public int statusCode;
		
		/* Constructor */
		public ResponseDataHolder() {
			statusCode = -1;
			errorObject = null;
			object = null;
		}
	}
}
