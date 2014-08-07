package com.synergykit.android.response;

import com.synergykit.android.resource.SynergyKITErrorObject;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface DeleteResponseListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergyKITErrorObject errorObject);
}
