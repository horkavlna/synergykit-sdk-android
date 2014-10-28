package com.letsgood.synergykit.listener;

import com.letsgood.synergykit.resource.SynergyKITError;
import com.letsgood.synergykit.resource.SynergyKITUser;

public interface UserResponseListener {
	public void doneCallback(int statusCode, SynergyKITUser user);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
