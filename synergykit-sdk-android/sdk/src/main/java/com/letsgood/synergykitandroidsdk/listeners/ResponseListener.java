package com.letsgood.synergykitandroidsdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitObject;

public interface ResponseListener {
	public void doneCallback(int statusCode, SynergyKitObject object);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
