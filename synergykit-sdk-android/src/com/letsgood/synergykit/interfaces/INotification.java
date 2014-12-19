package com.letsgood.synergykit.interfaces;

import com.letsgood.synergykit.listeners.EmailResponseListener;
import com.letsgood.synergykit.listeners.NotificationResponseListener;
import com.letsgood.synergykit.resources.SynergyKITEmail;
import com.letsgood.synergykit.resources.SynergyKITNotification;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface INotification {
	public void sendEmail(SynergyKITEmail email, EmailResponseListener listener, boolean parallelMode);
	public void sendNotification(SynergyKITNotification notification, NotificationResponseListener listener, boolean parralelMode);
}
	