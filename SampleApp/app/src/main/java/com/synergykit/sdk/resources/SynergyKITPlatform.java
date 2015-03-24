package com.synergykit.sdk.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import java.io.Serializable;

public class SynergyKitPlatform extends SynergyKitObject implements Serializable {

    /* Constants */
    protected static final String PLATFORM = "android";


    /* Attributes */
	private String platformName = PLATFORM;
	private String registrationId;
	private boolean development;

	/* Name getter */
	public String getPlatformName() {
		return platformName;
	}

	/* Registration ID getter */
	public String getRegistrationId() {
		return registrationId;
	}

	/* Registration ID setter */
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	/* Development getter */
	public boolean getDevelopment() {
		return development;
	}

	/* Development setter */
	public void setDevelopment(boolean development) {
		this.development = development;
	}
}
