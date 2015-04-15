package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitPlatform;

public interface PlatformResponseListener {
	public void doneCallback(int statusCode, SynergykitPlatform platform);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
