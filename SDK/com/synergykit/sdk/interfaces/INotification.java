package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.listeners.EmailResponseListener;
import com.synergykit.sdk.listeners.NotificationResponseListener;
import com.synergykit.sdk.resources.SynergyKITEmail;
import com.synergykit.sdk.resources.SynergyKITNotification;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface INotification {
	public void sendEmail(String mailId, SynergyKITEmail email, EmailResponseListener listener, boolean parallelMode);
	public void sendNotification(SynergyKITNotification notification, NotificationResponseListener listener, boolean parralelMode);
}
	