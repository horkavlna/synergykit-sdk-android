package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergykitError;

public interface DeleteResponseListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
