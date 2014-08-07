package com.synergykit.android.resource;

import java.util.ArrayList;
import java.util.List;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class BaseUser extends SynergyKITBaseObject {
    /* Attributes */
	private String password;
	private String email;
	private String registrationId;
	private String platform;

	public String getRegistrationId() {
		return registrationId;
	}


	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}


	public String getPlatform() {
		return platform;
	}


	public void setPlatform(String platform) {
		this.platform = platform;
	}


	/* Constructor */
    public BaseUser(String registrationId) {
    	this.registrationId = registrationId;
    }
    

	/* Email getter  */
	public String getEmail() {
		return email;
	}

	/* Email setter */
	public void setEmail(String email) {
		this.email = email;
	}

	/* Password getter */
	public String getPassword() {
		return password;
	}

	/* Password setter */
	public void setPassword(String password) {
		this.password = password;
	}



}
