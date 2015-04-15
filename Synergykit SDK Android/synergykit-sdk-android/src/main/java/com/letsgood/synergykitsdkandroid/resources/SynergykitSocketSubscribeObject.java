package com.letsgood.synergykitsdkandroid.resources;


import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.Synergykit;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SynergykitSocketSubscribeObject {


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
    private SynergykitSocketFilter filter;


    public SynergykitSocketSubscribeObject(){
        this.tenant= Synergykit.getTenant();
        this.key= Synergykit.getApplicationKey();
        this.token = Synergykit.getToken();

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
    public void setFilter(SynergykitSocketFilter filter){
       this.filter = filter;
    }

    /* Filter getter */
    public SynergykitSocketFilter getFilter(){
        return filter;
    }
}
