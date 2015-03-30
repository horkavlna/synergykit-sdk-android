package com.letsgood.synergykitandroidsdk;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitandroidsdk.builders.UriBuilder;
import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.builders.uri.Resource;
import com.letsgood.synergykitandroidsdk.interfaces.INotifications;
import com.letsgood.synergykitandroidsdk.listeners.EmailResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.NotificationResponseListener;
import com.letsgood.synergykitandroidsdk.log.SynergyKitLog;
import com.letsgood.synergykitandroidsdk.request.EmailRequestPost;
import com.letsgood.synergykitandroidsdk.request.NotificationRequestPost;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitEmail;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitNotification;

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
