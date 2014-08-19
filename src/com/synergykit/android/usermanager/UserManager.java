package com.synergykit.android.usermanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.synergykit.android.requestmanager.RequestManager;
import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.resource.BaseUser;
import com.synergykit.android.response.BaseUserResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetUsersResponseListener;
import com.synergykit.android.responsemanager.ResponseManager;
import com.synergykit.android.responsemanager.ResultObjectBuilder;
import com.synergykit.android.urlbuilder.Url;
import com.synergykit.android.urlbuilder.UrlBuilder;

public class UserManager {
	/* Constants */
	private static final String STATUS_CODE = "status_code";
	private static final String OBJECT = "object";
	private static final int LOGGIN_URL_TOP  = 1;
	
	/* Attributes */
	private static UserManager mInstance = null;
	
	/* Instance getter */
	public static UserManager getInstance(){
		if(mInstance==null)
			mInstance=new UserManager();
		
		return mInstance;
		
	}
		
	/* Get users */
	public  void getUsers(GetUsersResponseListener listener,Type type){
		RequestManager.getInstance().getUsers(listener, type);
	}
	
	/* Get user */
	public void getUser(String userId, BaseUserResponseListener listener, Type type){
		RequestManager.getInstance().getUser(userId, listener, type);
	}
	
	/* Create user */
	public void createUser(BaseUser baseUser, BaseUserResponseListener listener, Type type){
		RequestManager.getInstance().createUser(baseUser, listener, type);
	}
	
	/* Update user */
	public void updateUser(String userId, BaseUser baseUser, BaseUserResponseListener listener, Type type){
		RequestManager.getInstance().updateUser(userId, baseUser, listener, type);
	}
	
	/* Delete user */
	public void deleteUser(String userId, DeleteResponseListener listener){
		RequestManager.getInstance().deleteUser(userId, listener);
	}

	/* Register user */
	public void registerUser(BaseUser baseUser, BaseUserResponseListener listener, Type type){
		this.createUser(baseUser, listener, type);
	}
	
	/* Login user */
	public void loginUser(final BaseUser baseUser, final BaseUserResponseListener listener, final Type type){
		
		RequestManager.getInstance().synergylize(new BaseRequestAsyncTask() {
			
			@Override
			protected void onPostExecute(Object object) {
				ResponseDataHolder responseDataHolder = (ResponseDataHolder)object;
				ResponseManager responseManager = new ResponseManager();
				
				
				responseManager.manageResult(responseDataHolder.mStatusCode,
											(BaseUser) responseDataHolder.mObject,
											responseDataHolder.mErrorObject,
											listener,
											type);				
			}
			
			@Override
			protected Object doInBackground(Void... params) {
				//Build url
				UrlBuilder urlBuilder = new UrlBuilder();
				urlBuilder.setResource(UrlBuilder.RESOURCE_USER_LOGIN);				
				Url url = urlBuilder.build();		
				
				
				ResponseDataHolder responseDataHolder = new ResponseDataHolder();	//response data holder
				
				HttpResponse httpResponse= requestPost(getUrl(),baseUser); //request
				responseDataHolder.mStatusCode = httpResponse.getStatusLine().getStatusCode(); //set status code
				
				try {
					BufferedReader data = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
					
					//create response objects
					if(responseDataHolder.mStatusCode>= HttpStatus.SC_OK && responseDataHolder.mStatusCode < HttpStatus.SC_MULTIPLE_CHOICES){
						responseDataHolder.mObject = ResultObjectBuilder.buildBaseObjects(responseDataHolder.mStatusCode, data,type);
					}else{
						responseDataHolder.mErrorObject = ResultObjectBuilder.buildErrorObject(responseDataHolder.mStatusCode, data);

					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				

				return responseDataHolder;
			}
		});
			
			
	
	}
	
	
	
	
	//------------------------------------------------------------------------------------------------------
	
}

