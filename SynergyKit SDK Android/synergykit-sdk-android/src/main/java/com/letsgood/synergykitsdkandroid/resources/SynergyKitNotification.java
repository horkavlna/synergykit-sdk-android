package com.letsgood.synergykitsdkandroid.resources;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.LinkedList;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKitNotification extends SynergyKitObject implements Serializable {


	/* Attributes */
    @Expose
	private LinkedList<String> userIds = new LinkedList<String>();
    @Expose
	private String alert = null;
    @Expose
    private String payload = null;

	/* Constructor */
	public SynergyKitNotification(String alert) {
		this.setAlert(alert);
	}

	/* Alert getter */
	public String getAlert() {
		return alert;
	}

	/* Alert setter */
	public void setAlert(String alert) {
		this.alert = alert;
	}

	/* User ids getter */
	public LinkedList<String> getUserIds() {
		return userIds;
	}

	/* User ids setter */
	public void setUserIds(LinkedList<String> userIds) {
		this.userIds = userIds;

		// if null
		if (this.userIds == null) {
			this.userIds = new LinkedList<String>();
		}
	}

	/* Add user id */
	public void addUserId(String userId){
		if(userId!=null && !this.userIds.contains(userId))
			this.userIds.add(userId);
	}

	/* Remove user id */
	public void removeUserId(String userId){
		if(userId!=null)
			this.userIds.remove(userId);
	}

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
