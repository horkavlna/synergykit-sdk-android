package com.synergykit.sdk.resources;

import java.io.Serializable;
import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKitConfig implements Serializable {
	/* Attributes */
	private boolean parallelMode = false;
	private SynergyKitUri synergyKITUri = null;
	private Type type = null;
	
	/* Parallel mode getter */
	public boolean isParallelMode() {
		return parallelMode;
	}
	
	/* Parallel mode setter */
	public void setParallelMode(boolean parallelMode) {
		this.parallelMode = parallelMode;
	}
	
	/* Uri getter */
	public SynergyKitUri getUri() {
		return synergyKITUri;
	}
	
	/* Uri setter */
	public void setUri(SynergyKitUri synergyKITUri) {
		this.synergyKITUri = synergyKITUri;
	}
	
	/* Valid getter */
	public boolean isValid(){
		
		//Uri
		if(synergyKITUri==null || synergyKITUri.toString().length()==0)
			return false;
		
		return true;
	}

	/* Type getter */
	public Type getType() {
		return type;
	}

	/* Type setter */
	public void setType(Type type) {
		this.type = type;
	}
}
