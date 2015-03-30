package com.letsgood.synergykitandroidsdk.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SynergyKitEmail extends SynergyKitObject implements Serializable {
	
	/* Attributes */
    @Expose
	protected String email;
    @Expose
	protected String subject;
    @Expose
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