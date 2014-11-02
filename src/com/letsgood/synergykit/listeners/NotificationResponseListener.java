package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;

public interface NotificationResponseListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
