package com.letsgood.synergykitsdkandroid.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.listeners.EmailResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.NotificationResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitEmail;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitNotification;

public interface INotifications {
	public void sendEmail(String mailId, SynergyKitEmail email, EmailResponseListener listener, boolean parallelMode);
	public void sendNotification(SynergyKitNotification notification, NotificationResponseListener listener, boolean parralelMode);
}
	