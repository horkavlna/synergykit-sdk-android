package com.letsgood.synergykitsdkandroid.interfaces;




/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.PlatformResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.PlatformsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UsersResponseListener;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitPlatform;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

import java.lang.reflect.Type;

public interface IUsers{
	public void getUser(SynergykitConfig config, UserResponseListener listener);
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode);
	public void getUsers(SynergykitConfig config, UsersResponseListener listener);
	public void getUsers(Type type, UsersResponseListener listener, boolean parallelMode);
	public void createUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode);
	public void updateUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode);
    public void patchUser(SynergykitUser user, UserResponseListener listener, boolean parallelMode);

    public void deleteUser(SynergykitUser user, DeleteResponseListener listener, boolean parallelMode);

    public void addPlatformToUser(SynergykitUser user, SynergykitPlatform platform, PlatformResponseListener listener, boolean parallelMode);
    public void updatePlatformInUser(SynergykitUser user, SynergykitPlatform platform, PlatformResponseListener listener, boolean parallelMode);
    public void patchPlatformInUser(SynergykitUser user, SynergykitPlatform platform, PlatformResponseListener listener, boolean parallelMode);
    public void deletePlatform(SynergykitUser user, SynergykitPlatform platform, DeleteResponseListener listener, boolean parallelMode);
    public void getPlatform(SynergykitUser user, String platformId, PlatformResponseListener listener, boolean parallelMode);
    public void getPlatforms(SynergykitUser user, PlatformsResponseListener listener, boolean parallelMode);

    public void addRole(SynergykitUser user, String role, UserResponseListener listener, boolean parallelMode);
    public void removeRole(SynergykitUser user,String role, UserResponseListener listener, boolean parallelMode);

}
