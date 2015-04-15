package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;

public interface RecordsResponseListener {
	public void doneCallback(int statusCode, SynergykitObject[] objects);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
