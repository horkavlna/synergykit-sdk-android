package com.synergykit.android.request;

import java.lang.reflect.Type;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.BaseResponseListener;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class PostAsyncTask extends BaseRequestAsyncTask{

	/*Attributes */
	private BaseResponseListener mListener;
	private String mJson;
	private Type mType;
	
	/* Json setter */
	public void setJson(String json){
		mJson = json;
	}
	
	/* Listener setter */
	public void setListener(BaseResponseListener listener){
		mListener =listener;
	}
	
	/* ClassOf setter */
	public void setType(Type type){
		mType = type;
	}
		
	
	/* Do  in background */
	@Override
	protected HttpResponse doInBackground(Void... params) {
		Post post = new Post(mUrl){};
		HttpResponse httpResponse;
		
		try {
			//post data
			httpResponse = post.execute(new StringEntity(mJson, "UTF-8"));
			return httpResponse;
		
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return null;
	}

	/* On post execute */
	@Override
	protected void onPostExecute(HttpResponse httpResponse) {
		
		//Listener check
		if(mListener==null || httpResponse==null)
			return;
		
		//callback result
		if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)				
			mListener.doneCallback(httpResponse, ResultObjectBuilder.buildBaseObject(httpResponse, mType));
		else
			mListener.errorCallback(httpResponse, ResultObjectBuilder.buildErrorObject(httpResponse));
		
	}

}
