package com.synergykit.sdk.resources;

import com.synergykit.sdk.builders.uri.Filter;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 24. 3. 2015.
 */
public class SynergyKitSocketFilter {

    /* Attributes */
    private String name;
    private String query;

    /* Constructor  */
    public SynergyKitSocketFilter(String name, String query){

        if(name==null || query == null || name.isEmpty() || query.isEmpty())
            throw new IllegalArgumentException();

        this.name = name;
        this.query = query;
    }

    /* Name getter */
    public String getName() {
        return name;
    }

    /* Name setter */
    public void setName(String name) {

        if(name==null || name.isEmpty())
            throw new IllegalArgumentException();

        this.name = name;
    }

    /* Query getter */
    public String getQuery() {
        return query;
    }

    /* Query setter */
    public void setQuery(String query) {

        if(query == null ||  query.isEmpty())
            throw new IllegalArgumentException();

        this.query = query;
    }


}
