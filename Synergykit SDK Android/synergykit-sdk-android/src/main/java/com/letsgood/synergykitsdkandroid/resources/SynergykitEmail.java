package com.letsgood.synergykitsdkandroid.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SynergykitEmail extends SynergykitObject implements Serializable {
	
	/* Attributes */
    @Expose
	protected String email;
    @Expose
	protected String subject;
    @Expose
    protected String from;

    /* New Instance */
    public static SynergykitEmail newInstace(){
        return new SynergykitEmail();
    }

	/* Email getter */
	public String getEmail() {
		return email;
	}

	/* Email setter */
	public SynergykitEmail setEmail(String email) {
		this.email = email;
        return this;
	}

	/* Subject getter */
	public String getSubject() {
		return subject;
	}

	/* Subject setter */
	public SynergykitEmail setSubject(String subject) {
		this.subject = subject;
        return this;
	}

    public String getFrom() {
        return from;
    }

    public SynergykitEmail setFrom(String from) {
        this.from = from;
        return this;
    }
}