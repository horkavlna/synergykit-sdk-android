package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface BytesResponseListener {
	public void doneCallback(int statusCode,byte[] data);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
