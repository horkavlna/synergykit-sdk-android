package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;

public interface FileResponseListener {
	public void doneCallback(int statusCode, SynergykitFile file);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
