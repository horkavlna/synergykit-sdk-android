package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;

public interface NotificationResponseListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
