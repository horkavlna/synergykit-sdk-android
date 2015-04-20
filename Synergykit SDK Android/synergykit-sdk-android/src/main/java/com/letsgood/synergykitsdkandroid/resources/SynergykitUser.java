package com.letsgood.synergykitsdkandroid.resources;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.Synergykit;

import java.io.Serializable;
import java.util.LinkedList;
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
    @Expose
    protected boolean isActivated = false;
    @Expose
    protected LinkedList<String> roles = new LinkedList<>();

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

    /* Is activated */
    public boolean isActivated() {
        return isActivated;
    }

    /* Set activated */
    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    /* Roles getter */
    public LinkedList<String> getRoles() {
        return roles;
    }

    /* -------------------------------------------------------------- */
    public class AuthData {

        /* Attributes */
        @Expose
        private SynergykitFacebookAuthData facebook;

        @Expose
        private SynergykitTwitterAuthData twitter;

        @Expose
        private SynergykitAnonymousAuthData anonymous;

        @Expose
        private SynergykitGoogleAuthData google;


        public AuthData() {
            this.twitter = null;
            this.facebook = null;
            this.anonymous = null;
            this.google = null;
        }

        /* Constructor */
        public AuthData(SynergykitFacebookAuthData facebookAuthData) {
            this.facebook = facebookAuthData;
            this.twitter = null;
            this.anonymous = null;
            this.google = null;
        }

        /* Constructor */
        public AuthData(SynergykitTwitterAuthData twitterAuthData) {
            facebook = null;
            twitter = twitterAuthData;
            this.anonymous = null;
            this.google = null;
        }

        /* Constructor */
        public AuthData(SynergykitGoogleAuthData googleAuthData) {
            facebook = null;
            twitter = null;
            this.anonymous = null;
            this.google = googleAuthData;
        }

        /* Constructor */
        public AuthData(SynergykitAnonymousAuthData anonymousAuthData) {
            facebook = null;
            twitter = null;
            this.anonymous = anonymousAuthData;
            this.google = null;
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

        /* Anonymous setter*/
        public SynergykitAnonymousAuthData getAnonymous() {
            return anonymous;
        }

        /* Anonymous setter */
        public void setAnonymous(SynergykitAnonymousAuthData anonymous) {
            this.anonymous = anonymous;
        }
    }
}
