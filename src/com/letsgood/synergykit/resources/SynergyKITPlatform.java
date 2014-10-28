package com.letsgood.synergykit.resources;

public class SynergyKITPlatform {

	private String name;
	private String applicationUrl;
	private String registrationId;
	private boolean development;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public boolean getDevelopment() {
		return development;
	}

	public void setDevelopment(boolean development) {
		this.development = development;
	}
}
