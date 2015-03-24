package com.synergykit.sdk.resources;

import com.synergykit.sdk.SynergyKit;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SynergyKitSocketAuth {

    private String tenant;
    private String key;
    private String token;
    private String collection;
    private String message;
    private Query query;


    public SynergyKitSocketAuth(){
        tenant= SynergyKit.getTenant();
        key= SynergyKit.getApplicationKey();
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

    public void setQuery(String name, String oDataFilter){
       this.query = new Query(name,oDataFilter);

    }

    /* Query */
    private class Query{

        /* Attributes */
        private String name;
        private String filter;

        public Query(String name, String filter){
            this.name = name;
            this.filter = filter;
        }
    }

}
