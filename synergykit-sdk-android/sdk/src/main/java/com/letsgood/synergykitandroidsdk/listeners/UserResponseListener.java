package com.letsgood.synergykitandroidsdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitUser;

public interface UserResponseListener {
	public void doneCallback(int statusCode, SynergyKitUser user);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
