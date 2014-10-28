package com.letsgood.synergykit;

import com.letsgood.synergykit.interfaces.IRecords;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.request.RecordRequestGet;
import com.letsgood.synergykit.resources.SynergyKITConfig;

public class Records implements IRecords {

	/* Get record*/
	@Override
	public void getRecord(SynergyKITConfig config, ResponseListener listener) {
		RecordRequestGet requestGet = new RecordRequestGet();		
		requestGet.setConfig(config);
		SynergyKIT.synergylize(requestGet, config.isParallelMode());
	}
	
	/* Get record */
	@Override
	public void getRecord(String collectionUrl, String recordId, ResponseListener listener, boolean parallelMode) {
		
	}

}
