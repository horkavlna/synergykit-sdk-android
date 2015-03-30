package com.letsgood.synergykitandroidsdk.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitandroidsdk.listeners.EmailResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.NotificationResponseListener;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitEmail;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitNotification;

public interface INotifications {
	public void sendEmail(String mailId, SynergyKitEmail email, EmailResponseListener listener, boolean parallelMode);
	public void sendNotification(SynergyKitNotification notification, NotificationResponseListener listener, boolean parralelMode);
}
	