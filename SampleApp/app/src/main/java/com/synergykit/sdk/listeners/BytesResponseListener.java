package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;

public interface BytesResponseListener {
	public void doneCallback(int statusCode,byte[] data);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
