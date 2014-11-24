package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITUser;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface UsersResponseListener {
	public void doneCallback(int statusCode, SynergyKITUser[] users);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
