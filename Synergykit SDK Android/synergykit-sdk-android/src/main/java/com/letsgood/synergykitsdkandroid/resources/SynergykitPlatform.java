package com.letsgood.synergykitsdkandroid.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SynergykitPlatform extends SynergykitObject implements Serializable {

    /* Constants */
    protected static final String PLATFORM = "android";


    /* Attributes */
    @Expose
	private String platformName = PLATFORM;
    @Expose
	private String registrationId;
    @Expose
	private boolean development = false;


    public SynergykitPlatform(String registrationId){

        if(registrationId==null){
            throw new NullPointerException();
        }

        this.registrationId = registrationId;
    }

	/* Name getter */
	public String getPlatformName() {
		return platformName;
	}

	/* Registration ID getter */
	public String getRegistrationId() {
		return registrationId;
	}

	/* Registration ID setter */
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	/* Development getter */
	public boolean getDevelopment() {
		return development;
	}

	/* Development setter */
	public void setDevelopment(boolean development) {
		this.development = development;
	}
}
