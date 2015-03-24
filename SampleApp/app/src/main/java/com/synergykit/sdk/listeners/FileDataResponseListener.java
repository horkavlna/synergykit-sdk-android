package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitFileData;

public interface FileDataResponseListener {
	public void doneCallback(int statusCode,SynergyKitFileData fileData);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
