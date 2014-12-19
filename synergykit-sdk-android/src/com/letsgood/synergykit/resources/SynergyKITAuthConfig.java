package com.letsgood.synergykit.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKITAuthConfig {
	
	/* Attributes */
	private String tenant = null;
	private String applicationKey = null;
	
	/* Tenant getter */
	public String getTenant() {
		return tenant;
	}

	/* Tenant setter */
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	/* Application key getter */
	public String getApplicationKey() {
		return applicationKey;
	}

	/* Application key setter */
	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}

}
