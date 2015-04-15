package com.letsgood.synergykitsdkandroid.listeners;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

public interface UsersResponseListener {
	public void doneCallback(int statusCode, SynergykitUser[] users);
	public void errorCallback(int statusCode, SynergykitError errorObject);
}
