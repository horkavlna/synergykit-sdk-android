package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITUser;

public interface UsersResponseListener {
	public void doneCallback(int statusCode, SynergyKITUser[] users);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
