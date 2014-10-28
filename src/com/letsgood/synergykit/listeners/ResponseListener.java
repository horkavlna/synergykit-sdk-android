package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITObject;

public interface ResponseListener {
	public void doneCallback(int statusCode,SynergyKITObject object);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
