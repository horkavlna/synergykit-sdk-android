package com.letsgood.synergykit.resources;

import java.util.LinkedList;
import java.util.List;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKITUser extends SynergyKITObject {
	/* Constants */
	protected static final String PLATFORM = "android";

	
	/* Attributes */
	protected String password;	
	protected String email;
	private String registrationId;

	protected String platform = PLATFORM;

	protected List<SynergyKITPlatform> platforms;

	/* Constructor */
    public SynergyKITUser(String registrationId) {
    	
    	if(registrationId == null){
    		//TODO Exception
    		throw new IllegalArgumentException();
    	}
       	
    	//list of platforms
    	platforms = new LinkedList<SynergyKITPlatform>();

    	
    	//add platforms to list
    	SynergyKITPlatform pl = new SynergyKITPlatform();
    	pl.setName(PLATFORM);
    	pl.setRegistrationId(registrationId);
    	platforms.add(pl);
    	
    	// SPECIAL DATA FOR SYNERGYKIT
    	this.platform = PLATFORM;
    	this.registrationId = registrationId;    	
    }
    
    

	/* Email getter  */
	public String getEmail() {
		return email;
	}
	
	/* Email setter */
	public void setEmail(String email){
		this.email = email;
	}

	/* Platform getter */
	public List<SynergyKITPlatform> getPlatforms() {
		return platforms;
	}

	
	/* Password setter */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/* Registration id getter */
	public String getRegistrationId(){
		return registrationId;
	}

	/* To String */
	@Override
	public String toString(){
		return " Email: " + email + ", Password: " + password + ", Platform: " + platform;
	}
}
