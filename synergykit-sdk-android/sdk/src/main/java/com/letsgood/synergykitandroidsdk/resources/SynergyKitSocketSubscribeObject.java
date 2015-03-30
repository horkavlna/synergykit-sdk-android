package com.letsgood.synergykitandroidsdk.resources;

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitandroidsdk.SynergyKit;


/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SynergyKitSocketSubscribeObject {


    /* Attributes */
    @Expose
    private String tenant;
    @Expose
    private String key;
    @Expose
    private String token;
    @Expose
    private String collectionName;
    @Expose
    private String eventName;
    @Expose
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
