package com.letsgood.synergykitsdkandroid.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.listeners.EmailResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.NotificationResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEmail;
import com.letsgood.synergykitsdkandroid.resources.SynergykitNotification;

public interface INotifications {
	public void sendEmail(String mailId, SynergykitEmail email, EmailResponseListener listener, boolean parallelMode);
	public void sendNotification(SynergykitNotification notification, NotificationResponseListener listener, boolean parralelMode);
}
	