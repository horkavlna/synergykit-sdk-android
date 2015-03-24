package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitUser;

public interface UserResponseListener {
	public void doneCallback(int statusCode, SynergyKitUser user);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
