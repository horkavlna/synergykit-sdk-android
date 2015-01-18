package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITPlatform;

public interface PlatformsResponseListener {
	public void doneCallback(int statusCode, SynergyKITPlatform[] platforms);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
