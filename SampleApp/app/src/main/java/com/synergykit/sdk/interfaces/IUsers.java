package com.synergykit.sdk.interfaces;

import java.lang.reflect.Type;

import com.synergykit.sdk.listeners.DeleteResponseListener;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.listeners.UsersResponseListener;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITUser;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IUsers{
	public void getUser(SynergyKITConfig config, UserResponseListener listener);
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode );
	public void getUsers(SynergyKITConfig config, UsersResponseListener listener);
	public void getUsers(Type type, UsersResponseListener listener, boolean parallelMode);
	public void createUser(SynergyKITUser user, UserResponseListener listener, boolean parallelMode);
	public void updateUser(SynergyKITUser user, UserResponseListener listener, boolean parallelMode);
	public void deleteUser(String userId,DeleteResponseListener listener, boolean parallelMode);

}
