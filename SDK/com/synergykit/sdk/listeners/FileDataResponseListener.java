package com.synergykit.sdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITFileData;

public interface FileDataResponseListener {
	public void doneCallback(int statusCode,SynergyKITFileData fileData);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
