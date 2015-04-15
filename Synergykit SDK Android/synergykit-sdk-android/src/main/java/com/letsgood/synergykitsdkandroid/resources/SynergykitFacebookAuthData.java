package com.letsgood.synergykitsdkandroid.resources;

import com.google.gson.annotations.Expose;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 13. 4. 2015.
 */
public class SynergykitFacebookAuthData {

    /* Attribute */
    @Expose
    private String id;
    @Expose
    private String accessToken;

    /* Constructor */
    public SynergykitFacebookAuthData(String id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }

    /* Id getter */
    public String getId() {
        return id;
    }

    /* Id setter */
    public void setId(String id) {
        this.id = id;
    }

    /* Access token getter */
    public String getAccessToken() {
        return accessToken;
    }

    /* Access token setter */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
