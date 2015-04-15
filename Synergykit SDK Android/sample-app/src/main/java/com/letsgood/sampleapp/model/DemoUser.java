package com.letsgood.sampleapp.model;

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 2. 4. 2015.
 */
public class DemoUser extends SynergykitUser {

    /* Attributes */
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
