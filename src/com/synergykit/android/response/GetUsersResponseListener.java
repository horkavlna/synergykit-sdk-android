package com.synergykit.android.response;

import com.synergykit.android.resource.BaseUser;
import com.synergykit.android.resource.SynergyKITErrorObject;

public interface GetUsersResponseListener {
	public void doneCallback(int statusCode, BaseUser[] baseUsers);
	public void errorCallback(int statusCode, SynergyKITErrorObject errorObject);
}
