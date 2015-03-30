package com.letsgood.synergykitandroidsdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitObject;

public interface RecordsResponseListener {
	public void doneCallback(int statusCode, SynergyKitObject[] objects);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
