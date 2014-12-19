package com.letsgood.synergykit.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKITPlatform {

	/* Attributes */
	private String name;
	private String applicationUrl;
	private String registrationId;
	private boolean development;

	/* Name getter */
	public String getName() {
		return name;
	}

	/* Name setter */
	public void setName(String name) {
		this.name = name;
	}

	/* Application URL getter */
	public String getApplicationUrl() {
		return applicationUrl;
	}

	/* Application URL setter */
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
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
