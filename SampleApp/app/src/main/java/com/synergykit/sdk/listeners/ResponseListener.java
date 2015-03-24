package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitObject;

public interface ResponseListener {
	public void doneCallback(int statusCode,SynergyKitObject object);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
