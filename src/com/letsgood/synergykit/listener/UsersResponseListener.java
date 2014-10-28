package com.letsgood.synergykit.listener;

import com.letsgood.synergykit.resource.SynergyKITError;
import com.letsgood.synergykit.resource.SynergyKITUser;

public interface UsersResponseListener {
	public void doneCallback(int statusCode, SynergyKITUser[] users);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
