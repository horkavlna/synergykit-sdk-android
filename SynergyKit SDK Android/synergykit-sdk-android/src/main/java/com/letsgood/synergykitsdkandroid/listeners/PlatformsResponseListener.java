package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitPlatform;

public interface PlatformsResponseListener {
	public void doneCallback(int statusCode, SynergyKitPlatform[] platforms);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
