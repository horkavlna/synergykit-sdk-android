package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKITError;

public interface NotificationResponseListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
