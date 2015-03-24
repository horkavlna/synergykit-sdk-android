package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.resources.SynergyKitUser;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IAuthorization {
	public void registerUser(SynergyKitUser user, UserResponseListener listener);
	public void loginUser(SynergyKitUser user,UserResponseListener listener);
}
