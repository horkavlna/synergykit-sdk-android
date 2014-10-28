package com.letsgood.synergykit.listener;

import com.letsgood.synergykit.resource.SynergyKITError;
import com.letsgood.synergykit.resource.SynergyKITObject;

public interface ResponseListener {
	public void doneCallback(int statusCode,SynergyKITObject object);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
