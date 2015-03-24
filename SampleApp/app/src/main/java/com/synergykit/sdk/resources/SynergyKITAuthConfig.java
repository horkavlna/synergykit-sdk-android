package com.synergykit.sdk.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import java.io.Serializable;

public class SynergyKitAuthConfig implements Serializable {
	
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
