package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITUser;

public interface UserResponseListener {
	public void doneCallback(int statusCode, SynergyKITUser user);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
