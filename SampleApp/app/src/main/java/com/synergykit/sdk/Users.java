package com.synergykit.sdk;

import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.interfaces.IUsers;
import com.synergykit.sdk.listeners.DeleteResponseListener;
import com.synergykit.sdk.listeners.PlatformResponseListener;
import com.synergykit.sdk.listeners.PlatformsResponseListener;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.listeners.UsersResponseListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.request.PlatformRequestGet;
import com.synergykit.sdk.request.PlatformRequestPost;
import com.synergykit.sdk.request.PlatformRequestPut;
import com.synergykit.sdk.request.PlatformsRequestGet;
import com.synergykit.sdk.request.RequestDelete;
import com.synergykit.sdk.request.UserRequestGet;
import com.synergykit.sdk.request.UserRequestPost;
import com.synergykit.sdk.request.UserRequestPut;
import com.synergykit.sdk.request.UsersRequestGet;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITPlatform;
import com.synergykit.sdk.resources.SynergyKITUser;

import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Users implements IUsers{
	/* Constants */
	private static final int TOP = 100;
	
	/* Get user */
	@Override
	public void getUser(SynergyKITConfig config, UserResponseListener listener) {
		UserRequestGet request = new UserRequestGet();		
		request.setConfig(config);
		request.setListener(listener);
		SynergyKIT.synergylize(request, config.isParallelMode());	
	}

	/* Get user */
	@Override
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = SynergyKIT.getConfig();

		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_USERS)
								.setRecordId(userId);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);

		//redirect
		this.getUser(config, listener);		
	}

	/* Get users */
	@Override
	public void getUsers(SynergyKITConfig config, UsersResponseListener listener) {
		UsersRequestGet request = new UsersRequestGet();		
		request.setConfig(config);
		request.setListener(listener);
		SynergyKIT.synergylize(request, config.isParallelMode());	
		
	}

	/* Get users */
	@Override
	public void getUsers(Type type, UsersResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = SynergyKIT.getConfig();

		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_USERS)
								.setTop(TOP);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);


		//redirect
		this.getUsers(config, listener);		
		
	}

	/* Create user */
	@Override
	public void createUser(SynergyKITUser user, UserResponseListener listener,	boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		UserRequestPost request = new UserRequestPost();
		
		//User check
		if(user == null){
			//Log
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
								.setResource(Resource.RESOURCE_USERS);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(user.getClass());
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(user);
		
		//execute
		SynergyKIT.synergylize(request, parallelMode);
		
		
	}

	/* Update user */
	@Override
	public void updateUser(SynergyKITUser user, UserResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		UserRequestPut request = new UserRequestPut();
		
		//User check
		if(user == null){
			//Log
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
								.setResource(Resource.RESOURCE_USERS)
								.setRecordId(user.get_id());
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(user.getClass());
		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(user);
		
		//execute
		SynergyKIT.synergylize(request, parallelMode);		
	}

	/* Delete user */
	@Override
	public void deleteUser(SynergyKITUser user, DeleteResponseListener listener,	boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		RequestDelete request = new RequestDelete();
		
		//Object check
		if(user == null){
			//Log
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);		
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergyKITError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
			else if(SynergyKIT.isDebugModeEnabled())
				SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_USERS)
								.setRecordId(user.get_id());
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		
		//execute
		SynergyKIT.synergylize(request, parallelMode);		
	}

    @Override
    public void addPlatformToUser(SynergyKITUser user, SynergyKITPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKITConfig config = new SynergyKITConfig();
        PlatformRequestPost request = new PlatformRequestPost();

        //User check
        if(user == null || platform == null){
            //Log
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
                .setResource(String.format(Resource.RESOURCE_USERS_PLATFORMS, user.get_id()));

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(platform.getClass());


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(platform);

        //execute
        SynergyKIT.synergylize(request, parallelMode);
    }

    @Override
    public void updatePlatformInUser(SynergyKITUser user, SynergyKITPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKITConfig config = new SynergyKITConfig();
        PlatformRequestPut
                request = new PlatformRequestPut();

        //User check
        if(user == null || platform == null){
            //Log
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
                .setResource(String.format(Resource.RESOURCE_USERS_PLATFORMS,user.get_id()))
                .setRecordId(platform.get_id());

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(platform.getClass());


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(platform);

        //execute
        SynergyKIT.synergylize(request, parallelMode);
    }

    @Override
    public void deletePlatform(SynergyKITUser user, SynergyKITPlatform platform, DeleteResponseListener listener, boolean parallelMode) {
        SynergyKITConfig config = new SynergyKITConfig();
        RequestDelete request = new RequestDelete();

        //Object check
        if(user == null || platform == null){
            //Log
            SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergyKITError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
            else if(SynergyKIT.isDebugModeEnabled())
                SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(String.format(Resource.RESOURCE_USERS_PLATFORMS,user.get_id()))
                .setRecordId(platform.get_id());

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);

        //set request
        request.setConfig(config);
        request.setListener(listener);

        //execute
        SynergyKIT.synergylize(request, parallelMode);
    }

    @Override
    public void getPlatform(SynergyKITUser user, String platformId, PlatformResponseListener listener, boolean parallelMode) {
        PlatformRequestGet request = new PlatformRequestGet();


        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(String.format(Resource.RESOURCE_USERS_PLATFORMS, user.get_id()))
                .setRecordId(platformId);

        SynergyKITConfig config = new SynergyKITConfig();
        config.setType(SynergyKITPlatform.class);
        config.setParallelMode(parallelMode);
        config.setUri(uriBuilder.build());

        request.setConfig(config);
        request.setListener(listener);
        SynergyKIT.synergylize(request, config.isParallelMode());
    }

    @Override
    public void getPlatforms(SynergyKITUser user, PlatformsResponseListener listener, boolean parallelMode) {
        PlatformsRequestGet request = new PlatformsRequestGet();


        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(String.format(Resource.RESOURCE_USERS_PLATFORMS, user.get_id()));

        SynergyKITConfig config = new SynergyKITConfig();
        config.setType(SynergyKITPlatform[].class);
        config.setParallelMode(parallelMode);
        config.setUri(uriBuilder.build());

        request.setConfig(config);
        request.setListener(listener);
        SynergyKIT.synergylize(request, config.isParallelMode());
    }


}
