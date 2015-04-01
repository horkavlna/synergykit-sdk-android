package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitPlatform;

public interface PlatformResponseListener {
	public void doneCallback(int statusCode, SynergyKitPlatform platform);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
