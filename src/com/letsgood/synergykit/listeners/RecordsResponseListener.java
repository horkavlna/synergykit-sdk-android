package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITObject;

public interface RecordsResponseListener {
	public void doneCallback(int statusCode,SynergyKITObject[] objects);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
