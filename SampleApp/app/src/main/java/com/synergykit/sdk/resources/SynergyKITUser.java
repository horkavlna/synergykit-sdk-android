package com.synergykit.sdk.resources;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKitUser extends SynergyKitObject implements Serializable {
	
	/* Attributes */
    @Expose
	protected String password;
    @Expose
	protected String email;
    @Expose
	protected String activationHash;
    @Expose
    protected String sessionToken;

	protected List<SynergyKitPlatform> platforms;

	/* Email getter  */
	public String getEmail() {
		return email;
	}
	
	/* Email setter */
	public void setEmail(String email){
		this.email = email;
	}

	/* Platform getter */
	public List<SynergyKitPlatform> getPlatforms() {
		return platforms;
	}
	
	/* Password setter */
	public void setPassword(String password) {
		this.password = password;
	}

	/* Activation hash getter */
	public String getActivationHash() {
		return activationHash;
	}

	/* Activation hash setter */
	public void setActivationHash(String activationHash) {
		this.activationHash = activationHash;
	}

    /* Session token getter */
    public String getSessionToken(){
        return sessionToken;
    }

}
