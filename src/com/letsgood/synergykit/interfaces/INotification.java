package com.letsgood.synergykit.interfaces;

import com.letsgood.synergykit.listeners.EmailResponseListener;
import com.letsgood.synergykit.resources.SynergyKITEmail;

public interface INotification {
	public void sendEmail(SynergyKITEmail email, EmailResponseListener listener, boolean parallelMode);
}
	