package com.letsgood.synergykitsdkandroid.resources;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class SynergykitUser extends SynergykitObject implements Serializable {
	
	/* Attributes */
    @Expose
	protected String password;
    @Expose
	protected String email;
    @Expose
	protected String activationHash;
    @Expose
    protected String sessionToken;
    @Expose
	protected List<SynergykitPlatform> platforms;
    @Expose
    protected AuthData authData;

	/* Email getter  */
	public String getEmail() {
		return email;
	}
	
	/* Email setter */
	public void setEmail(String email){
		this.email = email;
	}

	/* Platform getter */
	public List<SynergykitPlatform> getPlatforms() {
		return platforms;
	}
	
	/* Password setter */
	public void setPassword(String password) {
		this.password = password;
	}

	/* Activation hash getter */
	public String getActivationHash() {
		return activationHash;
	}

	/* Activation hash setter */
	public void setActivationHash(String activationHash) {
		this.activationHash = activationHash;
	}

    /* Session token getter */
    public String getSessionToken(){
        return sessionToken;
    }

    /* Auth data getter */
    public AuthData getAuthData() {
        return authData;
    }

    /* Auth data setter */
    public void setAuthData(AuthData authData) {
        this.authData = authData;
    }

    /* -------------------------------------------------------------- */
    public class AuthData {

        /* Attributes */
        @Expose
        private SynergykitFacebookAuthData facebook;

        @Expose
        private SynergykitTwitterAuthData twitter;


        public AuthData() {
            twitter = null;
            facebook = null;
        }

        /* Constructor */
        public AuthData(SynergykitFacebookAuthData facebookAuthData) {
            facebook = facebookAuthData;
            twitter = null;
        }

        /* Constructor */
        public AuthData(SynergykitTwitterAuthData twitterAuthData) {
            facebook = null;
            twitter = twitterAuthData;
        }

        /* Facebook getter */
        public SynergykitFacebookAuthData getFacebook() {
            return facebook;
        }

        /* Facebook setter */
        public void setFacebook(SynergykitFacebookAuthData facebook) {
            this.facebook = facebook;
        }

        /* Twitter getter */
        public SynergykitTwitterAuthData getTwitter() {
            return twitter;
        }

        /* Twitter setter */
        public void setTwitter(SynergykitTwitterAuthData twitter) {
            this.twitter = twitter;
        }

    }
}
