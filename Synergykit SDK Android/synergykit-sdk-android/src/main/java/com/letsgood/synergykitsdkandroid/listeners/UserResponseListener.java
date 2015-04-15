package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

public interface UserResponseListener {
	public void doneCallback(int statusCode, SynergykitUser user);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
