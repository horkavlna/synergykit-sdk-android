package com.synergykit.sampleapp.beans;

import com.synergykit.sdk.resources.SynergyKitObject;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 12. 3. 2015.
 */
public class Message extends SynergyKitObject {

    private String name = null;
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
