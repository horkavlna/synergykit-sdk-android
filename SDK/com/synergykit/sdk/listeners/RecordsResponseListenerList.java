package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITObject;

import java.util.List;

public interface RecordsResponseListenerList {
	public void doneCallback(int statusCode, List<SynergyKITObject> object);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
