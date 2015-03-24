package com.synergykit.sdk;

import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.interfaces.INotifications;
import com.synergykit.sdk.listeners.EmailResponseListener;
import com.synergykit.sdk.listeners.NotificationResponseListener;
import com.synergykit.sdk.log.SynergyKitLog;
import com.synergykit.sdk.request.EmailRequestPost;
import com.synergykit.sdk.request.NotificationRequestPost;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitEmail;
import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitNotification;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Notifications implements INotifications {

	/* Send email */
	@Override
	public void sendEmail(String mailId, SynergyKitEmail email, EmailResponseListener listener,	boolean parallelMode) {
		SynergyKitConfig config = new SynergyKitConfig();
		EmailRequestPost request = new EmailRequestPost();

		//Email check
		if(email == null){
			SynergyKitLog.print(Errors.MSG_NO_OBJECT);
			
			
			//Error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKit.isDebugModeEnabled())
				SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
		SynergyKit.synergylize(request, parallelMode);

			
	}

	/* Send notification */
	@Override
	public void sendNotification(SynergyKitNotification notification, NotificationResponseListener listener, boolean parallelMode) {
		SynergyKitConfig config = new SynergyKitConfig();
		NotificationRequestPost request = new NotificationRequestPost();

		//Email check
		if(notification == null){
			SynergyKitLog.print(Errors.MSG_NO_OBJECT);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKit.isDebugModeEnabled())
				SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
		SynergyKit.synergylize(request, parallelMode);
		
	}

}
