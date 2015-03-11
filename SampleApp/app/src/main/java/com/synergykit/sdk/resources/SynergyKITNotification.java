package com.synergykit.sdk.resources;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKITNotification extends SynergyKITObject  implements Serializable {

    /* Constants */
    public static final String PAYLOAD_TYPE_PLAYER_PROFILE_ID = "player_profile_id";
    public static final String PAYLOAD_TYPE_TEAM_ID = "team_id";


	/* Attributes */
	private LinkedList<String> userIds = new LinkedList<String>();
	private String alert = null;
    private String payload = null;

	/* Constructor */
	public SynergyKITNotification(String alert) {
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
