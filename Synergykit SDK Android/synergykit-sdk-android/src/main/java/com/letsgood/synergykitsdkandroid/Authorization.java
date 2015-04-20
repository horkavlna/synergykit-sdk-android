package com.letsgood.synergykitsdkandroid;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.interfaces.IAuthorization;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.UserRequestPost;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitAnonymousAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFacebookAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitGoogleAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitTwitterAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

public class Authorization implements IAuthorization {

	/* Register user */
	//ONLY FOR BETTER VIEW - As same method as createUser
	@Override
	public void registerUser(SynergykitUser user, UserResponseListener listener) {
        Synergykit.createUser(user, listener, true);
		
	}

	/* Login user */
	@Override
	public void loginUser(SynergykitUser user,final UserResponseListener listener) {
		SynergykitConfig config = new SynergykitConfig();
		UserRequestPost request = new UserRequestPost();
        UserResponseListener innerListener = null;
		
		//User check
		if(user == null){
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
								.setResource(Resource.RESOURCE_USER_LOGIN);


        //inner listener
        innerListener = new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {

                if(user!=null){
                    Synergykit.setToken(user.getSessionToken());
                    Synergykit.setLoggedUser(user);
                }

                //error callback
                if(listener!=null)
                    listener.doneCallback(statusCode,user);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {

                //error callback
                if(listener!=null)
                    listener.errorCallback(statusCode,errorObject);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            }
        };

		//set config
		config.setUri(uriBuilder.build());
		config.setParallelMode(true);
		config.setType(user.getClass());

		
		//set request
		request.setConfig(config);
		request.setListener(innerListener);
		request.setObject(user);
		
		//execute
		Synergykit.synergylize(request, true);
		
	}

    /* Logout user */
    @Override
    public void logoutUser() {
        Synergykit.setLoggedUser(null);
        Synergykit.setToken(null);
    }


    /* Link Facebook */
    @Override
    public void linkFacebook(SynergykitUser user, SynergykitFacebookAuthData facebookAuthData, final UserResponseListener listener) {
        SynergykitConfig config = null;
        UserRequestPost request = new UserRequestPost();
        SynergykitUser.AuthData authData = null;
        UserResponseListener innerListener = null;

        //User check
        if(user == null || facebookAuthData==null){
            SynergykitLog.print(Errors.MSG_NO_OBJECT);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        authData = user.new AuthData(facebookAuthData);
        user.setAuthData(authData);

        //inner listener
        innerListener = new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {

                if(user!=null){
                    Synergykit.setToken(user.getSessionToken());
                    Synergykit.setLoggedUser(user);
                }

                //error callback
                if(listener!=null)
                    listener.doneCallback(statusCode,user);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {

                //error callback
                if(listener!=null)
                    listener.errorCallback(statusCode,errorObject);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            }
        };

        //uri
        SynergykitUri synergyKitUri = UriBuilder.newInstance()
                                                .setResource(Resource.RESOURCE_USERS)
                                                .build();

        //config
        config = SynergykitConfig.newInstance()
                                 .setType(user.getClass())
                                 .setUri(synergyKitUri)
                                 .setParallelMode(true);


        //request
        request.setConfig(config);
        request.setListener(innerListener);
        request.setObject(user);

        Synergykit.synergylize(request, config.isParallelMode());
    }


    /*Link Twitter */
    @Override
    public void linkTwitter(SynergykitUser user, SynergykitTwitterAuthData twitterAuthData,final UserResponseListener listener) {
        SynergykitConfig config = null;
        UserRequestPost request = new UserRequestPost();
        SynergykitUser.AuthData authData = null;
        UserResponseListener innerListener = null;

        //User check
        if(user == null || twitterAuthData==null){
            SynergykitLog.print(Errors.MSG_NO_OBJECT);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        authData = user.new AuthData(twitterAuthData);
        user.setAuthData(authData);
        //inner listener
        innerListener = new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {

                if(user!=null){
                    Synergykit.setToken(user.getSessionToken());
                    Synergykit.setLoggedUser(user);
                }

                //error callback
                if(listener!=null)
                    listener.doneCallback(statusCode,user);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {

                //error callback
                if(listener!=null)
                    listener.errorCallback(statusCode,errorObject);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            }
        };

        //uri
        SynergykitUri synergyKitUri = UriBuilder.newInstance()
                .setResource(Resource.RESOURCE_USERS)
                .build();

        //config
        config = SynergykitConfig.newInstance()
                .setType(user.getClass())
                .setUri(synergyKitUri)
                .setParallelMode(true);


        //request
        request.setConfig(config);
        request.setListener(innerListener);
        request.setObject(user);

        Synergykit.synergylize(request, config.isParallelMode());
    }

    @Override
    public void linkGoogle(SynergykitUser user, SynergykitGoogleAuthData googleAuthData, final UserResponseListener listener) {
        SynergykitConfig config = null;
        UserRequestPost request = new UserRequestPost();
        SynergykitUser.AuthData authData = null;
        UserResponseListener innerListener = null;

        //User check
        if(user == null || googleAuthData==null){
            SynergykitLog.print(Errors.MSG_NO_OBJECT);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        authData = user.new AuthData(googleAuthData);
        user.setAuthData(authData);
        //inner listener
        innerListener = new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {

                if(user!=null){
                    Synergykit.setToken(user.getSessionToken());
                    Synergykit.setLoggedUser(user);
                }

                //error callback
                if(listener!=null)
                    listener.doneCallback(statusCode,user);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {

                //error callback
                if(listener!=null)
                    listener.errorCallback(statusCode,errorObject);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            }
        };

        //uri
        SynergykitUri synergyKitUri = UriBuilder.newInstance()
                .setResource(Resource.RESOURCE_USERS)
                .build();

        //config
        config = SynergykitConfig.newInstance()
                .setType(user.getClass())
                .setUri(synergyKitUri)
                .setParallelMode(true);


        //request
        request.setConfig(config);
        request.setListener(innerListener);
        request.setObject(user);

        Synergykit.synergylize(request, config.isParallelMode());
    }

    @Override
    public void loginViaFacebook(SynergykitFacebookAuthData facebookAuthData, UserResponseListener listener) {
        this.linkFacebook(new SynergykitUser(),facebookAuthData,listener);
    }

    @Override
    public void loginViaTwitter(SynergykitTwitterAuthData twitterAuthData, UserResponseListener listener) {
        this.linkTwitter(new SynergykitUser(),twitterAuthData,listener);
    }

    @Override
    public void loginViaGoogle(SynergykitGoogleAuthData googleAuthData, UserResponseListener listener) {
        this.linkGoogle(new SynergykitUser(), googleAuthData, listener);
    }

    @Override
    public void loginAnonymous(SynergykitAnonymousAuthData anonymousAuthData,final UserResponseListener listener) {
        SynergykitConfig config = null;
        UserRequestPost request = new UserRequestPost();
        SynergykitUser.AuthData authData = null;
        UserResponseListener innerListener = null;
        SynergykitUser user = new SynergykitUser();

        //User check
        if(anonymousAuthData==null){
            SynergykitLog.print(Errors.MSG_NO_OBJECT);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_NO_OBJECT, new SynergykitError(Errors.SC_NO_OBJECT, Errors.MSG_NO_OBJECT));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        authData = user.new AuthData(anonymousAuthData);
        user.setAuthData(authData);
        //inner listener
        innerListener = new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {

                if(user!=null){
                    Synergykit.setToken(user.getSessionToken());
                    Synergykit.setLoggedUser(user);
                }

                //error callback
                if(listener!=null)
                    listener.doneCallback(statusCode,user);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {

                //error callback
                if(listener!=null)
                    listener.errorCallback(statusCode,errorObject);
                else if(Synergykit.isDebugModeEnabled())
                    SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            }
        };

        //uri
        SynergykitUri synergyKitUri = UriBuilder.newInstance()
                .setResource(Resource.RESOURCE_USERS)
                .build();

        //config
        config = SynergykitConfig.newInstance()
                .setType(user.getClass())
                .setUri(synergyKitUri)
                .setParallelMode(true);


        //request
        request.setConfig(config);
        request.setListener(innerListener);
        request.setObject(user);

        Synergykit.synergylize(request, config.isParallelMode());
    }


}
