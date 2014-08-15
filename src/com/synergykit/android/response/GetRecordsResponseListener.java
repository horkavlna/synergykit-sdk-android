package com.synergykit.android.response;

import com.synergykit.android.resource.SynergyKITBaseObject;
import com.synergykit.android.resource.SynergyKITErrorObject;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface GetRecordsResponseListener {
	public void doneCallback(int statusCode,SynergyKITBaseObject[] baseObjects);
	public void errorCallback(int statusCode, SynergyKITErrorObject errorObject);

}
