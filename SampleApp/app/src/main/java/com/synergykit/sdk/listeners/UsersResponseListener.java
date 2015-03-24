package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitUser;

public interface UsersResponseListener {
	public void doneCallback(int statusCode, SynergyKitUser[] users);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
