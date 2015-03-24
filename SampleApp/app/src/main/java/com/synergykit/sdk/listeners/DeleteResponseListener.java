package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;

public interface DeleteResponseListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
