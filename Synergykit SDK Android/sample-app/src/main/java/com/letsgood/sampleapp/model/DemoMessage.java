package com.letsgood.sampleapp.model;


import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 12. 3. 2015.
 */
public class DemoMessage extends SynergykitObject {
    public static final int TYPE_INCOMMING_MSG = 0;
    public static final int TYPE_MY_MSG = 1;
    public static final int TYPE_STATE = 2;


    @Expose
    private String name = null;
    @Expose
    private String text = null;

    @Expose
    private String userId = "";

    private int type = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
