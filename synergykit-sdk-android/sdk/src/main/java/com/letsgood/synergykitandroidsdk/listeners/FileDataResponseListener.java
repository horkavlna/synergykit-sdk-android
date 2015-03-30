package com.letsgood.synergykitandroidsdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitFileData;

public interface FileDataResponseListener {
	public void doneCallback(int statusCode, SynergyKitFileData fileData);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
