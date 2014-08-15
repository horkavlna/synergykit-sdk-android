package com.synergykit.android.requestmanager;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class Config {
	/* Attributes */
	private String mTenant;
	private String mApplicationKey;
	
	
	/* Constructor */ 
	public Config(String tenant, String appKey){
		this.mTenant =tenant;
		this.mApplicationKey= appKey;				
	}
	
	/* Tenant setter */
	public void setTenant(String tenant){
		this.mTenant=tenant;
	}
	
	/* Tenant getter */
	public String getTenant(){
		return mTenant;
	} 
	
	/* Application key setter */
	public void setApplicationKey(String appKey){
		this.mApplicationKey = appKey;
	}
	
	/* Application key getter */
	public String getApplicationKey(){
		return mApplicationKey;
	}
}
