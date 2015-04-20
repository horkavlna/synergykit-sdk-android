package com.letsgood.synergykitsdkandroid.interfaces;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitAnonymousAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFacebookAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitGoogleAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitTwitterAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

public interface IAuthorization {
	public void registerUser(SynergykitUser user, UserResponseListener listener);
	public void loginUser(SynergykitUser user, UserResponseListener listener);
    public void logoutUser();
    public void linkFacebook(SynergykitUser user, SynergykitFacebookAuthData facebookAuthData, UserResponseListener listener);
    public void linkTwitter(SynergykitUser user,SynergykitTwitterAuthData twitterAuthData, UserResponseListener listener);
    public void linkGoogle(SynergykitUser user, SynergykitGoogleAuthData googleAuthData, UserResponseListener listener);
    public void loginViaFacebook(SynergykitFacebookAuthData facebookAuthData, UserResponseListener listener);
    public void loginViaTwitter(SynergykitTwitterAuthData twitterAuthData, UserResponseListener listener);
    public void loginViaGoogle(SynergykitGoogleAuthData googleAuthData, UserResponseListener listener);
    public void loginAnonymous(SynergykitAnonymousAuthData anonymousAuthData, UserResponseListener listener);
}
