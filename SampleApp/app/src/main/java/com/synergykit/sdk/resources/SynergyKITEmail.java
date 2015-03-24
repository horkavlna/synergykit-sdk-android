package com.synergykit.sdk.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import java.io.Serializable;

public class SynergyKitEmail extends SynergyKitObject implements Serializable {
	
	/* Attributes */
	protected String email;
	protected String subject;
    protected String from;


	/* Email getter */
	public String getEmail() {
		return email;
	}

	/* Email setter */
	public void setEmail(String email) {
		this.email = email;
	}

	/* Subject getter */
	public String getSubject() {
		return subject;
	}

	/* Subject setter */
	public void setSubject(String subject) {
		this.subject = subject;
	}

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}