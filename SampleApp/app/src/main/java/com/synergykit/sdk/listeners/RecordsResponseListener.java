package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitObject;

public interface RecordsResponseListener {
	public void doneCallback(int statusCode,SynergyKitObject[] objects);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
