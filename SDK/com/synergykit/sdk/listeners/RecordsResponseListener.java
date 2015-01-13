package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITObject;

public interface RecordsResponseListener {
	public void doneCallback(int statusCode,SynergyKITObject[] objects);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
