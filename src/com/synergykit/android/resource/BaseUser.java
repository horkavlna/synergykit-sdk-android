package com.synergykit.android.resource;

import java.math.BigInteger;
import java.security.SecureRandom;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class BaseUser extends SynergyKITBaseObject {
   /* Constants */
	private static final String PLATFORM = "android";
	
	/* Attributes */
	protected String password;
	protected String email;
	private String registrationId;
	protected String platform;

	/* Constructor */
    public BaseUser(String email, String password, String registrationId) {
    	if(email == null || password == null || registrationId == null)
    		throw new IllegalArgumentException();
    	
    	this.email = email;
    	this.password = password;
    	this.platform = PLATFORM;
    	this.registrationId = registrationId;
    	
    }
    
    

	/* Email getter  */
	public String getEmail() {
		return email;
	}

	/* Password getter */
	public String getPassword() {
		return password;
	}


	/* Platform getter */
	public String getPlatform() {
		return platform;
	}

	
	/* Password setter */
	public void setPassword(String password) {
		this.password = password;
	}

	/* To String */
	@Override
	public String toString(){
		return " Email: " + email + ", Password: " + password + ", Platform: " + platform;
	}

}
