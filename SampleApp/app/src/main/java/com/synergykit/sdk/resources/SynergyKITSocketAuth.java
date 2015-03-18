package com.synergykit.sdk.resources;

import com.synergykit.sdk.SynergyKIT;
import com.synergykit.sdk.builders.uri.Filter;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SynergyKITSocketAuth {

    private String tenant;
    private String key;
    private String token;
    private String collection;
    private String message;
    private Query query;


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
