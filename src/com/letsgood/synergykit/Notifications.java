package com.letsgood.synergykit;

import android.util.Log;

import com.letsgood.synergykit.builders.UriBuilder;
import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.builders.uri.Resource;
import com.letsgood.synergykit.interfaces.INotification;
import com.letsgood.synergykit.listeners.EmailResponseListener;
import com.letsgood.synergykit.listeners.NotificationResponseListener;
import com.letsgood.synergykit.request.EmailRequestPost;
import com.letsgood.synergykit.request.NotificationRequestPost;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITEmail;
import com.letsgood.synergykit.resources.SynergyKITError;
import com.letsgood.synergykit.resources.SynergyKITNotification;

public class Notifications implements INotification{

	/* Send email */
	@Override
	public void sendEmail(SynergyKITEmail email, EmailResponseListener listener,	boolean parallelMode) {		
		SynergyKITConfig config = new SynergyKITConfig();
		EmailRequestPost request = new EmailRequestPost();

		//Email check
		if(email == null){
			//Log
			if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NO_OBJECT);		
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKITError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NO_CALLBACK_LISTENER);
			
			return;
		}
		
		//Uri builder
		UriBuilder uriBuilder = new UriBuilder()
								.setResource(Resource.RESOURCE_EMAIL);

		
		//set config
		config.setUri(uriBuilder.build());
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
			//Log
			if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NO_OBJECT);		
			
			//error callback
			if(listener!=null)
				listener.errorCallback(Errors.SC_NO_OBJECT, new SynergyKITError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
			else if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NO_CALLBACK_LISTENER);
			
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
