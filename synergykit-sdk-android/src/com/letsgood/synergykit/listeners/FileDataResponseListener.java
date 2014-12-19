package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITFileData;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface FileDataResponseListener {
	public void doneCallback(int statusCode,SynergyKITFileData fileData);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
