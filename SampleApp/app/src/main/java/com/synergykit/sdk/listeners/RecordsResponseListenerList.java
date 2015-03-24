package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitObject;

import java.util.List;

public interface RecordsResponseListenerList {
	public void doneCallback(int statusCode, List<SynergyKitObject> object);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
