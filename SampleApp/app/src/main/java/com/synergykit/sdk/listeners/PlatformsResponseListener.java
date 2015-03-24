package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitPlatform;

public interface PlatformsResponseListener {
	public void doneCallback(int statusCode, SynergyKitPlatform[] platforms);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
