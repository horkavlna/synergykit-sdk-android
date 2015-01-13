package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.resources.SynergyKITUser;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IAuthorization {
	public void registerUser(SynergyKITUser user, UserResponseListener listener);
	public void loginUser(SynergyKITUser user,UserResponseListener listener);
}
