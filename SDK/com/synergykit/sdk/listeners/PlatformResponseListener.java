package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITPlatform;

public interface PlatformResponseListener {
	public void doneCallback(int statusCode, SynergyKITPlatform platform);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
