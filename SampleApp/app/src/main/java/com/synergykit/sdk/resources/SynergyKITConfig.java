package com.synergykit.sdk.resources;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKitConfig implements Serializable {
    /* Constants */
    public static final String BASE_SYNERGYKIT_URL = "https://%s.api.synergykit.com";
    public static final String API_SYNERGYKIT_URL = BASE_SYNERGYKIT_URL + "/v2.1";
    public static final String SOCKET_SYNERGYKIT_URL = BASE_SYNERGYKIT_URL;


    /* Attributes */
    @Expose
	private boolean parallelMode = false;
    @Expose
	private SynergyKitUri synergyKITUri = null;
    @Expose
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
