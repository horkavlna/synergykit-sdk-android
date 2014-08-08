package com.synergykit.android.response;

import com.synergykit.android.resource.BaseUser;
import com.synergykit.android.resource.SynergyKITErrorObject;

public interface BaseUserResponseListener{
	public void doneCallback(int statusCode, BaseUser baseUser);
	public void errorCallback(int statusCode, SynergyKITErrorObject errorObject);

}
