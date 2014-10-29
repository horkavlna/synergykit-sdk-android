package com.letsgood.synergykit.request;

import java.lang.reflect.Type;

import org.apache.http.HttpStatus;

import com.letsgood.synergykit.builders.ResultObjectBuilder;
import com.letsgood.synergykit.requestmethods.Get;
import com.letsgood.synergykit.requestmethods.Post;
import com.letsgood.synergykit.requestmethods.Put;
import com.letsgood.synergykit.requestmethods.RequestMethod;
import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITObject;
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

	/* Request method GET */
	protected static SynergyKITResponse get(SynergyKITUri uri){
		SynergyKITResponse response = new SynergyKITResponse();
		Get get = new Get(uri); //request method get
		
		response.setBufferedReader(get.execute());
		response.setStatusCode(get.getStatusCode());		
		
		return response;
	}
	
	/* Request method POST */
	protected static SynergyKITResponse post(SynergyKITUri uri, Object object){
		SynergyKITResponse response = new SynergyKITResponse();
		Post post = new Post(uri,object);
		
		response.setBufferedReader(post.execute());
		response.setStatusCode(post.getStatusCode());
		
		return response;
	}
	
	/* Request method PUT */
	protected static SynergyKITResponse put(SynergyKITUri uri, Object object){
		SynergyKITResponse response = new SynergyKITResponse();
		Put put = new Put(uri,object);
		
		response.setBufferedReader(put.execute());
		response.setStatusCode(put.getStatusCode());
		
		return response;
	}
	
	/* Manage response */
	protected ResponseDataHolder manageObjectResponse(SynergyKITResponse response, Type type){
		ResponseDataHolder dataHolder = new ResponseDataHolder();
		
		if(response==null 
			|| response.getStatusCode()>=HttpStatus.SC_INTERNAL_SERVER_ERROR 
			|| response.getStatusCode() == RequestMethod.INTERNAL_STATUS_CODE){
			
			dataHolder.errorObject = ResultObjectBuilder.buildError(dataHolder.statusCode);		
			
		}else if(response.getStatusCode()>= HttpStatus.SC_OK 
				 && response.getStatusCode() < HttpStatus.SC_MULTIPLE_CHOICES){
			
			dataHolder.statusCode = response.getStatusCode();
			dataHolder.object = ResultObjectBuilder.buildObject(dataHolder.statusCode, response.getBufferedReader(),type);
			
		}else{
			
			dataHolder.statusCode = response.getStatusCode();
			dataHolder.errorObject = ResultObjectBuilder.buildError(dataHolder.statusCode, response.getBufferedReader());
			
		}		
		
		return dataHolder;
	} 
	
	/* Manage response */
	protected ResponseDataHolder manageObjectsResponse(SynergyKITResponse response, Type type){
		ResponseDataHolder dataHolder = new ResponseDataHolder();
		
		if(response==null 
			|| dataHolder.statusCode>=HttpStatus.SC_INTERNAL_SERVER_ERROR 
			|| dataHolder.statusCode == RequestMethod.INTERNAL_STATUS_CODE){
			
			dataHolder.errorObject = ResultObjectBuilder.buildError(dataHolder.statusCode);		
			
		}else if(dataHolder.statusCode>= HttpStatus.SC_OK 
				 && dataHolder.statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
			
			dataHolder.statusCode = response.getStatusCode();
			dataHolder.objects = ResultObjectBuilder.buildObjects(dataHolder.statusCode, response.getBufferedReader(),type);
			
		}else{
			
			dataHolder.statusCode = response.getStatusCode();
			dataHolder.errorObject = ResultObjectBuilder.buildError(dataHolder.statusCode, response.getBufferedReader());
			
		}		
		
		return dataHolder;
	} 
	
	//----------------------------------------------------------------------------------
	protected class ResponseDataHolder{
		/* Attributes */
		public SynergyKITError errorObject;
		public SynergyKITObject object;
		public SynergyKITObject[] objects;
		public int statusCode;
		
		/* Constructor */
		public ResponseDataHolder() {
			statusCode = -1;
			errorObject = null;
			object = null;
			objects = null;
		}
	}
}
