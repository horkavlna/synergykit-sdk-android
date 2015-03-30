package com.synergykit.sampleapp.model;

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitObject;


/**
 * Created by Marek on 1/13/15.
 */
public class DemoObject extends SynergyKitObject {

    @Expose
    private String text;


    public DemoObject(){
        super();
    }


    public DemoObject(String collectionUrl){
        super(collectionUrl);
    }

    public DemoObject(String collectionUrl, String recordId){
        super(collectionUrl, recordId);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}