package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitUser;

public interface UserResponseListener {
	public void doneCallback(int statusCode, SynergyKitUser user);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
