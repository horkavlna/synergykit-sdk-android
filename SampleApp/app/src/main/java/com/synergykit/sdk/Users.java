package com.synergykit.sdk;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.internal.ge;
import com.synergykit.sdk.addons.GsonWrapper;
import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.interfaces.IUsers;
import com.synergykit.sdk.listeners.DeleteResponseListener;
import com.synergykit.sdk.listeners.PlatformResponseListener;
import com.synergykit.sdk.listeners.PlatformsResponseListener;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.listeners.UsersResponseListener;
import com.synergykit.sdk.log.SynergyKitLog;
import com.synergykit.sdk.request.PlatformRequestGet;
import com.synergykit.sdk.request.PlatformRequestPatch;
import com.synergykit.sdk.request.PlatformRequestPost;
import com.synergykit.sdk.request.PlatformRequestPut;
import com.synergykit.sdk.request.PlatformsRequestGet;
import com.synergykit.sdk.request.RequestDelete;
import com.synergykit.sdk.request.UserRequestGet;
import com.synergykit.sdk.request.UserRequestPatch;
import com.synergykit.sdk.request.UserRequestPost;
import com.synergykit.sdk.request.UserRequestPut;
import com.synergykit.sdk.request.UsersRequestGet;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitPlatform;
import com.synergykit.sdk.resources.SynergyKitUser;

import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Users implements IUsers{
	/* Constants */
	private static final int TOP = 100;

    /* Attributes */

	/* Get user */
	@Override
	public void getUser(SynergyKitConfig config, UserResponseListener listener) {
		UserRequestGet request = new UserRequestGet();		
		request.setConfig(config);
		request.setListener(listener);
		SynergyKit.synergylize(request, config.isParallelMode());
	}

	/* Get user */
	@Override
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode) {
		SynergyKitConfig config = SynergyKit.getConfig();

		
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
	public void getUsers(SynergyKitConfig config, UsersResponseListener listener) {
		UsersRequestGet request = new UsersRequestGet();		
		request.setConfig(config);
		request.setListener(listener);
		SynergyKit.synergylize(request, config.isParallelMode());
		
	}

	/* Get users */
	@Override
	public void getUsers(Type type, UsersResponseListener listener, boolean parallelMode) {
		SynergyKitConfig config = SynergyKit.getConfig();

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
	public void createUser(SynergyKitUser user, UserResponseListener listener,	boolean parallelMode) {
		SynergyKitConfig config = new SynergyKitConfig();
		UserRequestPost request = new UserRequestPost();
		
		//User check
		if(user == null){
			//Log
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
		SynergyKit.synergylize(request, parallelMode);
		
		
	}

	/* Update user */
	@Override
	public void updateUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode) {
		SynergyKitConfig config = new SynergyKitConfig();
		UserRequestPut request = new UserRequestPut();
		
		//User check
		if(user == null){
			//Log
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
		SynergyKit.synergylize(request, parallelMode);
	}

    @Override
    public void patchUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode) {
        SynergyKitConfig config = new SynergyKitConfig();
        UserRequestPatch request = new UserRequestPatch();

        //User check
        if(user == null){
            //Log
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
        SynergyKit.synergylize(request, parallelMode);
    }

    /* Delete user */
	@Override
	public void deleteUser(SynergyKitUser user, DeleteResponseListener listener,	boolean parallelMode) {
		SynergyKitConfig config = new SynergyKitConfig();
		RequestDelete request = new RequestDelete();
		
		//Object check
		if(user == null){
			//Log
			SynergyKitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergyKitError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
			else if(SynergyKit.isDebugModeEnabled())
				SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
		SynergyKit.synergylize(request, parallelMode);
	}

    @Override
    public void addPlatformToUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKitConfig config = new SynergyKitConfig();
        PlatformRequestPost request = new PlatformRequestPost();

        //User check
        if(user == null || platform == null){
            //Log
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
        SynergyKit.synergylize(request, parallelMode);
    }

    @Override
    public void updatePlatformInUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKitConfig config = new SynergyKitConfig();
        PlatformRequestPut request = new PlatformRequestPut();

        //User check
        if(user == null || platform == null){
            //Log
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
        SynergyKit.synergylize(request, parallelMode);
    }

    @Override
    public void patchPlatformInUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKitConfig config = new SynergyKitConfig();
        PlatformRequestPatch request = new PlatformRequestPatch();

        //User check
        if(user == null || platform == null){
            //Log
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
        SynergyKit.synergylize(request, parallelMode);
    }

    @Override
    public void deletePlatform(SynergyKitUser user, SynergyKitPlatform platform, DeleteResponseListener listener, boolean parallelMode) {
        SynergyKitConfig config = new SynergyKitConfig();
        RequestDelete request = new RequestDelete();

        //Object check
        if(user == null || platform == null){
            //Log
            SynergyKitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, new SynergyKitError(Errors.SC_NULL_ARGUMENTS_OR_EMPTY, Errors.MSG_NULL_ARGUMENTS_OR_EMPTY));
            else if(SynergyKit.isDebugModeEnabled())
                SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

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
        SynergyKit.synergylize(request, parallelMode);
    }

    @Override
    public void getPlatform(SynergyKitUser user, String platformId, PlatformResponseListener listener, boolean parallelMode) {
        PlatformRequestGet request = new PlatformRequestGet();


        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(String.format(Resource.RESOURCE_USERS_PLATFORMS, user.get_id()))
                .setRecordId(platformId);

        SynergyKitConfig config = new SynergyKitConfig();
        config.setType(SynergyKitPlatform.class);
        config.setParallelMode(parallelMode);
        config.setUri(uriBuilder.build());

        request.setConfig(config);
        request.setListener(listener);
        SynergyKit.synergylize(request, config.isParallelMode());
    }

    @Override
    public void getPlatforms(SynergyKitUser user, PlatformsResponseListener listener, boolean parallelMode) {
        PlatformsRequestGet request = new PlatformsRequestGet();


        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(String.format(Resource.RESOURCE_USERS_PLATFORMS, user.get_id()));

        SynergyKitConfig config = new SynergyKitConfig();
        config.setType(SynergyKitPlatform[].class);
        config.setParallelMode(parallelMode);
        config.setUri(uriBuilder.build());

        request.setConfig(config);
        request.setListener(listener);
        SynergyKit.synergylize(request, config.isParallelMode());
    }

}
