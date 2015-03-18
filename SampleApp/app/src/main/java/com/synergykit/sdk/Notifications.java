package com.synergykit.sdk;

import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.interfaces.INotification;
import com.synergykit.sdk.listeners.EmailResponseListener;
import com.synergykit.sdk.listeners.NotificationResponseListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.request.EmailRequestPost;
import com.synergykit.sdk.request.NotificationRequestPost;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITEmail;
import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITNotification;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Notifications implements INotification{

	/* Send email */
	@Override
	public void sendEmail(String mailId, SynergyKITEmail email, EmailResponseListener listener,	boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		EmailRequestPost request = new EmailRequestPost();

		//Email check
		if(email == null){
			SynergyKITLog.print(Errors.MSG_NO_OBJECT);
			
			
			//Error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKITError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKIT.isDebugModeEnabled())
				SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}


		
		//set config
		config.setUri(new UriBuilder()
                .setResource(Resource.RESOURCE_EMAIL).setMailId(mailId).build());

		config.setParallelMode(parallelMode);
		config.setType(email.getClass());
				
				
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(email);
		
		//execute
		SynergyKIT.synergylize(request, parallelMode);

			
	}

	/* Send notification */
	@Override
	public void sendNotification(SynergyKITNotification notification, NotificationResponseListener listener, boolean parallelMode) {
		SynergyKITConfig config = new SynergyKITConfig();
		NotificationRequestPost request = new NotificationRequestPost();

		//Email check
		if(notification == null){
			SynergyKITLog.print(Errors.MSG_NO_OBJECT);		
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKITError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKIT.isDebugModeEnabled())
				SynergyKITLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_NOTIFICATION);

		
		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(parallelMode);
		config.setType(notification.getClass());
				
				
		//set request
		request.setConfig(config);
		request.setListener(listener);
		request.setObject(notification);
		
		//execute
		SynergyKIT.synergylize(request, parallelMode);
		
	}

}
