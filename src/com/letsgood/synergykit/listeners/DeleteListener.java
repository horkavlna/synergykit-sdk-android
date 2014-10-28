package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;

public interface DeleteListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
