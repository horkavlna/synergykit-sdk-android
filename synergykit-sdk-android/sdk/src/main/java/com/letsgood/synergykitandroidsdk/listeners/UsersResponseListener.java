package com.letsgood.synergykitandroidsdk.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitUser;

public interface UsersResponseListener {
	public void doneCallback(int statusCode, SynergyKitUser[] users);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
