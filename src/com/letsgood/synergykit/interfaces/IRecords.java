package com.letsgood.synergykit.interfaces;

import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.resources.SynergyKITConfig;

public interface IRecords {
	public void getRecord(SynergyKITConfig config, ResponseListener listener);
	public void getRecord(String collectionUrl, String recordId, ResponseListener listener, boolean parallelMode );
}
