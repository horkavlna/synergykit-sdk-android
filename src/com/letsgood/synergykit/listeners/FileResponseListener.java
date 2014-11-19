package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITFileData;

public interface FileResponseListener {
	public void doneCallback(int statusCode,SynergyKITFileData object);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
