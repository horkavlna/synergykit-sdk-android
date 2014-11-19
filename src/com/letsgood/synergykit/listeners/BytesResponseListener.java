package com.letsgood.synergykit.listeners;

import com.letsgood.synergykit.resources.SynergyKITError;

public interface BytesResponseListener {
	public void doneCallback(int statusCode,byte[] data);
	public void errorCallback(int statusCode, SynergyKITError errorObject);
}
