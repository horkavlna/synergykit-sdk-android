package com.letsgood.synergykit.interfaces;

import com.letsgood.synergykit.listeners.UserResponseListener;
import com.letsgood.synergykit.resources.SynergyKITUser;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IAuthorization {
	public void registerUser(SynergyKITUser user, UserResponseListener listener);
	public void loginUser(SynergyKITUser user,UserResponseListener listener);
}
