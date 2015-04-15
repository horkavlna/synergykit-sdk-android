package com.letsgood.synergykitsdkandroid.resources;

import com.google.gson.annotations.Expose;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 13. 4. 2015.
 */
public class SynergykitTwitterAuthData {

    /* Attribute */
    @Expose
    private String id;
    @Expose
    private String screenName;
    @Expose
    private String consumerKey;
    @Expose
    private String consumerSecret;
    @Expose
    private String authToken;
    @Expose
    private String authTokenSecret;

    /* Constructor */
    private SynergykitTwitterAuthData(String id, String screenName, String consumerKey, String consumerSecret, String authToken, String authTokenSecret) {
        this.id = id;
        this.screenName = screenName;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.authToken = authToken;
        this.authTokenSecret = authTokenSecret;
    }

    /* Id getter */
    public String getId() {
        return id;
    }

    /* Id setter */
    public void setId(String id) {
        this.id = id;
    }

    /* Screen name getter */
    public String getScreenName() {
        return screenName;
    }

    /* Screen name setter */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /* Consumer key getter */
    public String getConsumerKey() {
        return consumerKey;
    }

    /* Consumer key setter */
    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    /* Consumer secret getter */
    public String getConsumerSecret() {
        return consumerSecret;
    }

    /* Consumer secret setter */
    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    /* Auth token getter */
    public String getAuthToken() {
        return authToken;
    }

    /* Auth token setter */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /* Auth token secret getter */
    public String getAuthTokenSecret() {
        return authTokenSecret;
    }

    /* Auth token secret setter */
    public void setAuthTokenSecret(String authTokenSecret) {
        this.authTokenSecret = authTokenSecret;
    }
}

