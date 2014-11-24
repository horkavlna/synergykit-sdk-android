package com.letsgood.synergykit;

import java.lang.reflect.Type;

import com.letsgood.synergykit.builders.UriBuilder;
import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.builders.uri.Resource;
import com.letsgood.synergykit.interfaces.IUsers;
import com.letsgood.synergykit.listeners.DeleteResponseListener;
import com.letsgood.synergykit.listeners.UserResponseListener;
import com.letsgood.synergykit.listeners.UsersResponseListener;
import com.letsgood.synergykit.log.SynergyKITLog;
import com.letsgood.synergykit.request.RequestDelete;
import com.letsgood.synergykit.request.UserRequestGet;
import com.letsgood.synergykit.request.UserRequestPost;
import com.letsgood.synergykit.request.UserRequestPut;
import com.letsgood.synergykit.request.UsersRequestGet;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITUser;

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
	public void deleteUser(String userId, DeleteResponseListener listener,	boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		RequestDelete request = new RequestDelete();
		
		//Object check
		if(userId == null){
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
								.setRecordId(userId);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);		
		
		//set request
		request.setConfig(config);
		request.setListener(listener);
		
		//execute
		SynergyKIT.synergylize(request, parallelMode);		
	}
	
}
