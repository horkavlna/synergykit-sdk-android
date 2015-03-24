package com.synergykit.sdk;

import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.interfaces.IAuthorization;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.log.SynergyKitLog;
import com.synergykit.sdk.request.UserRequestPost;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitUser;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Authorization implements IAuthorization{

	/* Register user */
	//ONLY FOR BETTER VIEW - Same method as createUser
	@Override
	public void registerUser(SynergyKitUser user, UserResponseListener listener) {
		SynergyKit.createUser(user, listener, true);
		
	}

	/* Login user */
	@Override
	public void loginUser(SynergyKitUser user, UserResponseListener listener) {
		SynergyKitConfig config = new SynergyKitConfig();
		UserRequestPost request = new UserRequestPost();
		
		//User check
		if(user == null){
			SynergyKitLog.print(Errors.MSG_NO_OBJECT);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKit.isDebugModeEnabled())
				SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
		SynergyKit.synergylize(request, true);
		
	}

}
