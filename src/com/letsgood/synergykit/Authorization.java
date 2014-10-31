package com.letsgood.synergykit;

import android.util.Log;

import com.letsgood.synergykit.builders.UriBuilder;
import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.builders.uri.Resource;
import com.letsgood.synergykit.interfaces.IAuthorization;
import com.letsgood.synergykit.listeners.UserResponseListener;
import com.letsgood.synergykit.request.UserRequestPost;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITUser;

public class Authorization implements IAuthorization{

	/* Register user */
	//ONLY FOR BETTER VIEW
	@Override
	public void registerUser(SynergyKITUser user, UserResponseListener listener) {
		SynergyKIT.createUser(user, listener, true);		
	}

	/* Login user */
	@Override
	public void loginUser(SynergyKITUser user, UserResponseListener listener) {
		SynergyKITConfig config = new SynergyKITConfig();
		UserRequestPost request = new UserRequestPost();
		
		//User check
		if(user == null){
			//Log
			if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NO_OBJECT);		
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKITError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NO_CALLBACK_LISTENER);	
			
			return;
		}
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_USER_LOGIN);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(true);
		config.setType(user.getClass());
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(user);
		
		//execute
		SynergyKIT.synergylize(request, true);
		
	}

}
