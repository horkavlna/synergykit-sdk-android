package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitPlatform;

public interface PlatformsResponseListener {
	public void doneCallback(int statusCode, SynergykitPlatform[] platforms);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
