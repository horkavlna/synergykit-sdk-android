package com.synergykit.sampleapp.beans;

import com.google.gson.annotations.Expose;
import com.synergykit.sdk.resources.SynergyKitObject;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 12. 3. 2015.
 */
public class SocketMessage extends SynergyKitObject {

    @Expose
    private String name = null;
    @Expose
    private String text = null;
    @Expose
    private int userId = 0;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
