package com.letsgood.synergykitsdkandroid.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitUser;

public interface IAuthorization {
	public void registerUser(SynergyKitUser user, UserResponseListener listener);
	public void loginUser(SynergyKitUser user, UserResponseListener listener);
}
