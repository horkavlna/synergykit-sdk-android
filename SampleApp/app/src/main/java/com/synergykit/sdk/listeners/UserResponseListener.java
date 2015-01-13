package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITUser;

public interface UserResponseListener {
	public void doneCallback(int statusCode, SynergyKITUser user);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
