package com.letsgood.synergykit.listener;

import com.letsgood.synergykit.resource.SynergyKITError;
import com.letsgood.synergykit.resource.SynergyKITObject;

public interface RecordsResponseListener {
	public void doneCallback(int statusCode,SynergyKITObject[] objects);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
