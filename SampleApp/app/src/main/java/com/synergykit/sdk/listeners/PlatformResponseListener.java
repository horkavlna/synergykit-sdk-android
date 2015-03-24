package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitPlatform;

public interface PlatformResponseListener {
	public void doneCallback(int statusCode, SynergyKitPlatform platform);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
