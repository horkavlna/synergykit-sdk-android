package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitObject;

public interface RecordsResponseListener {
	public void doneCallback(int statusCode, SynergyKitObject[] objects);
	public void errorCallback(int statusCode, SynergyKitError errorObject);
}
