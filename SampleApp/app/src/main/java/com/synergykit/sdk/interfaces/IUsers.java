package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.listeners.DeleteResponseListener;
import com.synergykit.sdk.listeners.PlatformResponseListener;
import com.synergykit.sdk.listeners.PlatformsResponseListener;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.listeners.UsersResponseListener;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITPlatform;
import com.synergykit.sdk.resources.SynergyKITUser;

import java.lang.reflect.Type;

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
	public void deleteUser(SynergyKITUser user,DeleteResponseListener listener, boolean parallelMode);

    public void addPlatformToUser(SynergyKITUser user, SynergyKITPlatform platform, PlatformResponseListener listener, boolean parallelMode);
    public void updatePlatformInUser(SynergyKITUser user, SynergyKITPlatform platform, PlatformResponseListener listener, boolean parallelMode);
    public void deletePlatform(SynergyKITUser user, SynergyKITPlatform platform, DeleteResponseListener listener,	boolean parallelMode);
    public void getPlatform(SynergyKITUser user,String platformId, PlatformResponseListener listener, boolean parallelMode );
    public void getPlatforms(SynergyKITUser user, PlatformsResponseListener listener, boolean parallelMode);
}
