package com.synergykit.sdk.resources;

import com.synergykit.sdk.SynergyKit;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.log.SynergyKitLog;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SynergyKitSocketSubscribeObject {

    private String tenant;
    private String key;
    private String token;
    private String collectionName;
    private String eventName;
    private SynergyKitSocketFilter filter;


    public SynergyKitSocketSubscribeObject(){
        this.tenant= SynergyKit.getTenant();
        this.key= SynergyKit.getApplicationKey();
        this.token = SynergyKit.getToken();

    }


    public String getTenant() {
        return tenant;
    }

    public String getKey() {
        return key;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collection) {
        this.collectionName = collection;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /* Filter setter*/
    public void setFilter(SynergyKitSocketFilter filter){
       this.filter = filter;
    }

    /* Filter getter */
    public SynergyKitSocketFilter getFilter(){
        return filter;
    }
}
