package com.letsgood.synergykitsdkandroid.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import java.io.Serializable;

public class SynergykitAuthConfig implements Serializable {
	
	/* Attributes */
	private String tenant = null;
	private String applicationKey = null;
    private String token = null;
    private SynergykitUser user = null;
	
	/* Tenant getter */
	public String getTenant() {
		return tenant;
	}

	/* Tenant setter */
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	/* Application key getter */
	public String getApplicationKey() {
		return applicationKey;
	}

	/* Application key setter */
	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}

    /* Token getter */
    public String getToken() {
        return token;
    }

    /* Token setter */
    public void setToken(String token) {
        this.token = token;
    }

    /* User getter */
    public SynergykitUser getUser() {
        return user;
    }

    /* User setter */
    public void setUser(SynergykitUser user) {
        this.user = user;
    }
}
