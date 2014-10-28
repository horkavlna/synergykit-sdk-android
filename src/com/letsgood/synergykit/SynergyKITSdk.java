package com.letsgood.synergykit;

import com.letsgood.synergykit.interfaces.IRecords;
import com.letsgood.synergykit.interfaces.ISynergyKITSdk;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.resources.SynergyKITAuthConfig;
import com.letsgood.synergykit.resources.SynergyKITConfig;

public class SynergyKITSdk implements ISynergyKITSdk, IRecords{

	/* Attributes */
	private static SynergyKITSdk instance = null;
	private SynergyKITAuthConfig authConfig = new SynergyKITAuthConfig();
	private SynergyKITConfig config = new SynergyKITConfig();
	
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
	
	//---------------------------------------------------------------------------------------
	@Override
	public void getRecord(SynergyKITConfig config, ResponseListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRecord(String collectionUrl, String recordId,
			ResponseListener listener, boolean parallelMode) {
		// TODO Auto-generated method stub
		
	}

}
