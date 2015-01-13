package com.synergykit.sampleapp.model;

import com.synergykit.sdk.resources.SynergyKITObject;

/**
 * Created by Marek on 1/13/15.
 */
public class DemoObject extends SynergyKITObject {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}