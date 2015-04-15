package com.letsgood.synergykitsdkandroid.config;

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import java.io.Serializable;
import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergykitConfig implements Serializable {
    /* Constants */
    public static final String BASE_SYNERGYKIT_URL = "https://%s.api.synergykit.com";
    public static final String API_SYNERGYKIT_URL = BASE_SYNERGYKIT_URL + "/v2.1";
    public static final String SOCKET_SYNERGYKIT_URL = BASE_SYNERGYKIT_URL;


    /* Attributes */
    @Expose
	private boolean parallelMode = false;
    @Expose
	private SynergykitUri synergyKITUri = null;
    @Expose
	private Type type = null;

    /* New instance*/
    public static SynergykitConfig newInstance(){
        return new SynergykitConfig();
    }

	/* Parallel mode getter */
	public boolean isParallelMode() {
		return parallelMode;
	}
	
	/* Parallel mode setter */
	public SynergykitConfig setParallelMode(boolean parallelMode) {
		this.parallelMode = parallelMode;
        return this;
	}
	
	/* Uri getter */
	public SynergykitUri getUri() {
		return synergyKITUri;
	}
	
	/* Uri setter */
	public SynergykitConfig setUri(SynergykitUri synergyKITUri) {

        this.synergyKITUri = synergyKITUri;
        return this;
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
	public SynergykitConfig setType(Type type) {
		this.type = type;
        return this;
	}
}
