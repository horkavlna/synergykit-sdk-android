package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergykitError;

public interface BytesResponseListener {
	public void doneCallback(int statusCode, byte[] data);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
