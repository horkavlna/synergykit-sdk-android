package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.listeners.EmailResponseListener;
import com.synergykit.sdk.listeners.NotificationResponseListener;
import com.synergykit.sdk.resources.SynergyKitEmail;
import com.synergykit.sdk.resources.SynergyKitNotification;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface INotifications {
	public void sendEmail(String mailId, SynergyKitEmail email, EmailResponseListener listener, boolean parallelMode);
	public void sendNotification(SynergyKitNotification notification, NotificationResponseListener listener, boolean parralelMode);
}
	