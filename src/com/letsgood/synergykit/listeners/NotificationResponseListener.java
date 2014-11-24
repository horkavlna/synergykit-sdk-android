package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface NotificationResponseListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
