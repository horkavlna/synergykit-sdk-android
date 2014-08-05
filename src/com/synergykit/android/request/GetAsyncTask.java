package com.synergykit.android.request;

import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.BaseResponseListener;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class GetAsyncTask  extends BaseRequestAsyncTask{
	
	/* Attributes */
	private String mUrl;
	private Type mType;
	private BaseResponseListener mListener;
	
	/* Url setter */
	public void setUrl(String url){
		mUrl = url;
	}
	
	/* Listener setter */
	public void setListener(BaseResponseListener listener){
		mListener =listener;
	}
	
	/* Type setter */
	public void setType(Type type){
		mType = type;
	}	
	

	/* Do in background */
	@Override
	protected HttpResponse doInBackground(Void... params) {
		Get request = new Get(mUrl){};
		HttpResponse httpResponse;
		
		try {
			//send request
			httpResponse = request.execute();
			return httpResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	

	/* On post execute */
	@Override
	protected void onPostExecute(HttpResponse httpResponse) {
		if(mListener==null || httpResponse==null)
			return;
		
		//callback result
		if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)				
			mListener.doneCallback(httpResponse, ResultObjectBuilder.buildBaseObject(httpResponse, mType));
		else
			mListener.errorCallback(httpResponse, ResultObjectBuilder.buildErrorObject(httpResponse));
		
	}



	

}
