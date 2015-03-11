package com.synergykit.sdk.resources;

import java.io.Serializable;
import java.util.List;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKITUser extends SynergyKITObject  implements Serializable {
	
	/* Attributes */
	protected String password;	
	protected String email;
	protected String activationHash;

	protected List<SynergyKITPlatform> platforms;

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

	/* Activation hash getter */
	public String getActivationHash() {
		return activationHash;
	}

	/* Activation hash setter */
	public void setActivationHash(String activationHash) {
		this.activationHash = activationHash;
	}

}
