package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;

public interface FilesResponseListener {
	public void doneCallback(int statusCode, SynergykitFile[] files);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
