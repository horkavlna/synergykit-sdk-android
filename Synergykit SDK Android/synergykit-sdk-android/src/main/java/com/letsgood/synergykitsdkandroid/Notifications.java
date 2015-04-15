package com.letsgood.synergykitsdkandroid;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.interfaces.INotifications;
import com.letsgood.synergykitsdkandroid.listeners.EmailResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.NotificationResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.EmailRequestPost;
import com.letsgood.synergykitsdkandroid.request.NotificationRequestPost;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEmail;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitNotification;

public class Notifications implements INotifications {

	/* Send email */
	@Override
	public void sendEmail(String mailId, SynergykitEmail email, EmailResponseListener listener,	boolean parallelMode) {
		SynergykitConfig config = new SynergykitConfig();
		EmailRequestPost request = new EmailRequestPost();

		//Email check
		if(email == null){
			SynergykitLog.print(Errors.MSG_NO_OBJECT);
			
			
			//Error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(Synergykit.isDebugModeEnabled())
				SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
		Synergykit.synergylize(request, parallelMode);

			
	}

	/* Send notification */
	@Override
	public void sendNotification(SynergykitNotification notification, NotificationResponseListener listener, boolean parallelMode) {
		SynergykitConfig config = new SynergykitConfig();
		NotificationRequestPost request = new NotificationRequestPost();

		//Email check
		if(notification == null){
			SynergykitLog.print(Errors.MSG_NO_OBJECT);
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(Synergykit.isDebugModeEnabled())
				SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
			
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
		Synergykit.synergylize(request, parallelMode);
		
	}

}
