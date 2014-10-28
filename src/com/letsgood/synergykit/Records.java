package com.letsgood.synergykit;

import java.lang.reflect.Type;

import com.letsgood.synergykit.builders.UriBuilder;
import com.letsgood.synergykit.builders.uri.Collection;
import com.letsgood.synergykit.builders.uri.Resource;
import com.letsgood.synergykit.interfaces.IRecords;
import com.letsgood.synergykit.listeners.DeleteListener;
import com.letsgood.synergykit.listeners.RecordsResponseListener;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.request.RecordRequestGet;
import com.letsgood.synergykit.request.RecordsRequestGet;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITObject;

public class Records implements IRecords {

	/* Get record*/
	@Override
	public void getRecord(SynergyKITConfig config, ResponseListener listener) {
		RecordRequestGet request = new RecordRequestGet();		
		request.setConfig(config);
		request.setListener(listener);
		SynergyKIT.synergylize(request, config.isParallelMode());
	}
	
	/* Get record */
	@Override
	public void getRecord(String collectionUrl, String recordId, Type type, ResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = SynergyKIT.getConfig();

		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setDatabase(collectionUrl)
								.setRecordId(recordId);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);

		//redirect
		this.getRecord(config, listener);
		
		
	}

	/* Records getter */
	@Override
	public void getRecords(SynergyKITConfig config,	RecordsResponseListener listener) {
		RecordsRequestGet request = new RecordsRequestGet();		
		request.setConfig(config);
		request.setListener(listener);
		SynergyKIT.synergylize(request, config.isParallelMode());
		
	}

	/* Records getter */
	@Override
	public void getRecords(String collectionUrl, Type type,	RecordsResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();

		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setDatabase(collectionUrl);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(type);

		//redirect
		this.getRecords(config, listener);
		
	}

	/* Record creater */
	@Override
	public void createRecord(String collectionUrl, SynergyKITObject object,	ResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_DATA)
								.setDatabase(collectionUrl);
		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(object.getClass());
		
	}

	/* Record updater */
	@Override
	public void updateRecord(String collectionUrl, String recordId,
			SynergyKITObject object, ResponseListener listener,
			boolean parallelMode) {
		// TODO Auto-generated method stub
		
	}

	/* Record deleter */
	@Override
	public void deleteRecord(String collectionUrl, String recordId,
			DeleteListener listener, boolean parallelMode) {
		// TODO Auto-generated method stub
		
	}

}
