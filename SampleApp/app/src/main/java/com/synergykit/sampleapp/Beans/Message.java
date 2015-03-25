package com.synergykit.sampleapp.beans;

import com.google.gson.annotations.Expose;
import com.synergykit.sdk.resources.SynergyKitObject;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 12. 3. 2015.
 */
public class Message extends SynergyKitObject {

    @Expose
    private String name = null;
    @Expose
    private String text = null;


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
}
