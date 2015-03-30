package com.letsgood.synergykitandroidsdk.interfaces;



import com.letsgood.synergykitandroidsdk.listeners.DeleteResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.PlatformResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.PlatformsResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.UserResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.UsersResponseListener;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitPlatform;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitUser;

import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IUsers{
	public void getUser(SynergyKitConfig config, UserResponseListener listener);
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode);
	public void getUsers(SynergyKitConfig config, UsersResponseListener listener);
	public void getUsers(Type type, UsersResponseListener listener, boolean parallelMode);
	public void createUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode);
	public void updateUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode);
    public void patchUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode);

    public void deleteUser(SynergyKitUser user, DeleteResponseListener listener, boolean parallelMode);

    public void addPlatformToUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode);
    public void updatePlatformInUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode);
    public void patchPlatformInUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode);
      public void deletePlatform(SynergyKitUser user, SynergyKitPlatform platform, DeleteResponseListener listener, boolean parallelMode);
    public void getPlatform(SynergyKitUser user, String platformId, PlatformResponseListener listener, boolean parallelMode);
    public void getPlatforms(SynergyKitUser user, PlatformsResponseListener listener, boolean parallelMode);

}
