package com.letsgood.synergykit.resources;

import java.util.LinkedList;

public class SynergyKITNotification extends SynergyKITObject{

	/* Attributes */
	private LinkedList<String> userIds = null;
	private String alert = null;

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
}