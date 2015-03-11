package com.synergykit.sdk.resources;

import com.synergykit.sdk.SynergyKIT;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SynergyKITSocketAuth {

    private String tenant;
    private String key;
    private String collection;
    private String message;


    public SynergyKITSocketAuth(){
        tenant=SynergyKIT.getTenant();
        key=SynergyKIT.getApplicationKey();
    }


    public String getTenant() {
        return tenant;
    }

    public String getKey() {
        return key;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
