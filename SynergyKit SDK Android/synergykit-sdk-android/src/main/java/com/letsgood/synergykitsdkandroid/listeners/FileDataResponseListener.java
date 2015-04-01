package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitFileData;

public interface FileDataResponseListener {
	public void doneCallback(int statusCode, SynergyKitFileData fileData);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
