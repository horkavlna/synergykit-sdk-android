package com.synergykit.sdk.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKITEmail extends SynergyKITObject {
	
	/* Attributes */
	protected String url;
	protected String email;
	protected String subject;

	/* Template getter */
	public String getTemplateUrl() {
		return url;
	}

	/* Template setter */
	public void setTemplateUrl(String templateUrl) {
		this.url = templateUrl;
	}

	/* Email getter */
	public String getEmail() {
		return email;
	}

	/* Email setter */
	public void setEmail(String email) {
		this.email = email;
	}

	/* Subject getter */
	public String getSubject() {
		return subject;
	}

	/* Subject setter */
	public void setSubject(String subject) {
		this.subject = subject;
	}
}