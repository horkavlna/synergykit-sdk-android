package com.letsgood.synergykit;

import java.lang.reflect.Type;

import android.os.AsyncTask;

import com.letsgood.synergykit.interfaces.IRecords;
import com.letsgood.synergykit.interfaces.ISynergyKITSdk;
import com.letsgood.synergykit.listeners.DeleteListener;
import com.letsgood.synergykit.listeners.RecordsResponseListener;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.request.SynergyKITRequest;
import com.letsgood.synergykit.resources.SynergyKITAuthConfig;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITObject;

public class SynergyKITSdk implements ISynergyKITSdk, IRecords{

	/* Attributes */
	private static SynergyKITSdk instance = null;
	private SynergyKITAuthConfig authConfig = new SynergyKITAuthConfig();
	private SynergyKITConfig config = new SynergyKITConfig();
	private Records records = new Records();
	
	//---------------------------------------------------------------------------------------
	/* Instance static getter */
	public static SynergyKITSdk getInstance(){
		
		if(instance == null )
			instance = new SynergyKITSdk();
		
		return instance;
	}
	
	//---------------------------------------------------------------------------------------
	/* Init */
	@Override
	public void init(String tenant, String applicationKey) {
		this.setTenant(tenant);
		this.setApplicationKey(applicationKey);
	}

	/* Reset */
	@Override
	public void reset() {
		authConfig = new SynergyKITAuthConfig();	
	}

	
	/* Tenant setter */
	@Override
	public void setTenant(String tenant) {
		// TODO Exception
		
		authConfig.setTenant(tenant);
	}

	/* Tenant getter */
	@Override
	public String getTenant() {
		return authConfig.getTenant();
	}

	/* Application key setter */
	@Override
	public void setApplicationKey(String applicationKey) {
		// TODO Exception
		
		authConfig.setApplicationKey(applicationKey);
		
	}

	/* Application key getter */
	@Override
	public String getApplicationKey() {
		return authConfig.getApplicationKey();
	}

	/* Is init */
	@Override
	public boolean isInit() {
		
		if(authConfig.getApplicationKey() == null 
		   || authConfig.getApplicationKey().isEmpty() == true
		   || authConfig.getTenant()==null
		   || authConfig.getTenant().isEmpty() ==true)
		   
		   return false;
		   
		   
		return true;
	}
	
	@Override
	public void setConfig(SynergyKITConfig config) {
		// TODO Exception
		
		this.config = config;
	}

	@Override
	public SynergyKITConfig getConfig() {
		return config;
	}
	
	/* Synergylize */
	@Override
	public void synergylize(SynergyKITRequest request, boolean parallelMode) {
		
		if(request==null){
			//TODO Exception
			return;
		}
		
		//execute
		if(parallelMode==false){
			request.execute();
		}else{
			request.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
		
	}
	
	//---------------------------------------------------------------------------------------
	/* Get record */
	@Override
	public void getRecord(SynergyKITConfig config, ResponseListener listener) {
		records.getRecord(config, listener);
		
	}

	/* Get record */
	@Override
	public void getRecord(String collectionUrl, String recordId, Type type,	ResponseListener listener, boolean parallelMode) {
		// TODO Auto-generated method stub
		
	}
	
	/* Get records */
	@Override
	public void getRecords(SynergyKITConfig config,
			RecordsResponseListener listener) {
		// TODO Auto-generated method stub
		
	}

	/* Get records */
	@Override
	public void getRecords(String collectionUrl, Type type,
			RecordsResponseListener listener, boolean parallelMode) {
		// TODO Auto-generated method stub
		
	}

	/* Create record */
	@Override
	public void createRecord(String collectionUrl, SynergyKITObject object,	ResponseListener listener, boolean parallelMode) {
		records.createRecord(collectionUrl, object, listener, parallelMode);
		
	}

	/* Update record */
	@Override
	public void updateRecord(String collectionUrl, String recordId, SynergyKITObject object, ResponseListener listener,	boolean parallelMode) {
		records.updateRecord(collectionUrl, recordId, object, listener, parallelMode);
		
	}

	/* Delete record */
	@Override
	public void deleteRecord(String collectionUrl, String recordId,
			DeleteListener listener, boolean parallelMode) {
		// TODO Auto-generated method stub
		
	}

}
