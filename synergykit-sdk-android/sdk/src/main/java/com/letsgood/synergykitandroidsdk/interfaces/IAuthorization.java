package com.letsgood.synergykitandroidsdk.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitandroidsdk.listeners.UserResponseListener;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitUser;

public interface IAuthorization {
	public void registerUser(SynergyKitUser user, UserResponseListener listener);
	public void loginUser(SynergyKitUser user, UserResponseListener listener);
}
