package com.synergykit.sdk;

import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.interfaces.IAuthorization;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.request.UserRequestPost;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITUser;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Authorization implements IAuthorization{

	/* Register user */
	//ONLY FOR BETTER VIEW - Same method as createUser
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
			SynergyKITLog.print(Errors.MSG_NO_OBJECT);	
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKITError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKIT.isDebugModeEnabled())
				SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
