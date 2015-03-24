package com.synergykit.sdk.interfaces;

import android.content.Context;

import com.synergykit.sdk.listeners.DeleteResponseListener;
import com.synergykit.sdk.listeners.PlatformResponseListener;
import com.synergykit.sdk.listeners.PlatformsResponseListener;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.listeners.UsersResponseListener;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitPlatform;
import com.synergykit.sdk.resources.SynergyKitUser;

import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IUsers{
	public void getUser(SynergyKitConfig config, UserResponseListener listener);
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode );
	public void getUsers(SynergyKitConfig config, UsersResponseListener listener);
	public void getUsers(Type type, UsersResponseListener listener, boolean parallelMode);
	public void createUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode);
	public void updateUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode);
	public void deleteUser(SynergyKitUser user,DeleteResponseListener listener, boolean parallelMode);

    public void addPlatformToUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode);
    public void updatePlatformInUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode);
    public void deletePlatform(SynergyKitUser user, SynergyKitPlatform platform, DeleteResponseListener listener,	boolean parallelMode);
    public void getPlatform(SynergyKitUser user,String platformId, PlatformResponseListener listener, boolean parallelMode );
    public void getPlatforms(SynergyKitUser user, PlatformsResponseListener listener, boolean parallelMode);

}
