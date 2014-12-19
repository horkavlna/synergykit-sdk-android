package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITObject;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface RecordsResponseListener {
	public void doneCallback(int statusCode,SynergyKITObject[] objects);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
