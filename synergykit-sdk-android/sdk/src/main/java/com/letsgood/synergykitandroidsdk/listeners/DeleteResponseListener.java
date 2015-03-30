package com.letsgood.synergykitandroidsdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;

public interface DeleteResponseListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
