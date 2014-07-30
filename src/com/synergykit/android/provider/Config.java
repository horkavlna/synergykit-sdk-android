package com.synergykit.android.provider;

public class Config {
	/* Attributes */
	private String mTenant;
	private String mAppKey;
	
	
	/* Constructor */ 
	public Config(String tenant, String appKey){
		this.mTenant =tenant;
		this.mAppKey= appKey;				
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
		this.mAppKey = appKey;
	}
	
	/* Application key getter */
	public String getApplicationKey(){
		return mAppKey;
	}
}
