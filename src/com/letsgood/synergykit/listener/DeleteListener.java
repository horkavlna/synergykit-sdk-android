package com.letsgood.synergykit.listener;

import com.letsgood.synergykit.resource.SynergyKITError;

public interface DeleteListener {
	public void doneCallback(int statusCode);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
