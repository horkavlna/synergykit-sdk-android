package com.synergykit.android.usermanager;

import java.lang.reflect.Type;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.util.Log;

import com.synergykit.android.requestmanager.GetUsersRequest;
import com.synergykit.android.requestmanager.PostRequest;
import com.synergykit.android.requestmanager.RequestManager;
import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.resource.BaseUser;
import com.synergykit.android.resource.SynergyKITErrorObject;
import com.synergykit.android.resource.SynergylizeRequestAsyncTask;
import com.synergykit.android.response.BaseUserResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetUsersResponseListener;
import com.synergykit.android.responsemanager.ErrorMessages;
import com.synergykit.android.responsemanager.ResponseManager;
import com.synergykit.android.urlbuilder.Url;
import com.synergykit.android.urlbuilder.UrlBuilder;

public class UserManager {
	/* Constructor */
	private static final String STATUS_CODE = "status_code";
	private static final String OBJECT = "object";
	
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
			/*	HttpResponse httpResponse = (HttpResponse)object;
				ResponseManager responseManager = new ResponseManager();
				responseManager.manageResult(httpResponse, listener, type);
				
				*/
			}
			
			@Override
			protected Object doInBackground(Void... params) {
				UrlBuilder urlBuilder = new UrlBuilder();
				urlBuilder.setResource(UrlBuilder.RESOURCE_USERS)
						  .setFilter("email eq '" + baseUser.getEmail() + "'");
				
				
				Url url = urlBuilder.build();
				
				String strUrl = url.getUrl();
				Log.e("SynergyKIT",strUrl);
				
				HttpResponse httpResponse = requestGet(url);
				
				
				return httpResponse;
			}
		});
			
			
	
	}
	
	
	//------------------------------------------------------------------------------------------------------
	
}

