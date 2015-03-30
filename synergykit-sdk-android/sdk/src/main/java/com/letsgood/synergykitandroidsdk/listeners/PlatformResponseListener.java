package com.letsgood.synergykitandroidsdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitPlatform;

public interface PlatformResponseListener {
	public void doneCallback(int statusCode, SynergyKitPlatform platform);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
