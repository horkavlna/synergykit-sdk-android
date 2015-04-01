package com.letsgood.synergykitsdkandroid;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.interfaces.IAuthorization;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergyKitLog;
import com.letsgood.synergykitsdkandroid.request.UserRequestPost;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitUser;

public class Authorization implements IAuthorization {

	/* Register user */
	//ONLY FOR BETTER VIEW - As same method as createUser
	@Override
	public void registerUser(SynergyKitUser user, UserResponseListener listener) {
        SynergyKit.createUser(user, listener, true);
		
	}

	/* Login user */
	@Override
	public void loginUser(SynergyKitUser user,final UserResponseListener listener) {
		SynergyKitConfig config = new SynergyKitConfig();
		UserRequestPost request = new UserRequestPost();
        UserResponseListener innerListener = null;
		
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


        //inner listener
        innerListener = new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergyKitUser user) {

                if(user!=null){
                    SynergyKit.setToken(user.getSessionToken());
                }

                //error callback
                if(listener!=null)
                    listener.doneCallback(statusCode,user);
                else if(SynergyKit.isDebugModeEnabled())
                    SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
            }

            @Override
            public void errorCallback(int statusCode, SynergyKitError errorObject) {

                //error callback
                if(listener!=null)
                    listener.errorCallback(statusCode,errorObject);
                else if(SynergyKit.isDebugModeEnabled())
                    SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            }
        };

		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(true);
		config.setType(user.getClass());

		
		//set request
		request.setConfig(config);
		request.setListener(innerListener);
		request.setObject(user);
		
		//execute
		SynergyKit.synergylize(request, true);
		
	}

}
