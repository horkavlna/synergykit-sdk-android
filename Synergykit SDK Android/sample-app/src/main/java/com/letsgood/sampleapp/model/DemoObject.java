package com.letsgood.sampleapp.model;

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;


/**
 * Created by Marek on 1/13/15.
 */
public class DemoObject extends SynergykitObject {

    /* Attributes */
    @Expose
    private String text;

    /* Text getter */
    public String getText() {
        return text;
    }

    /* Text setter */
    public void setText(String text) {
        this.text = text;
    }

}