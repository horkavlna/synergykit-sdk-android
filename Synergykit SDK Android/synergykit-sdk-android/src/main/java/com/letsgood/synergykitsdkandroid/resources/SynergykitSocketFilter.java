package com.letsgood.synergykitsdkandroid.resources;

import com.google.gson.annotations.Expose;


/**
 * Created by Letsgood.com - Pavel Stambrecht on 24. 3. 2015.
 */
public class SynergykitSocketFilter {

    /* Attributes */
    @Expose
    private String name;
    @Expose
    private String query;

    /* Constructor  */
    public SynergykitSocketFilter(String name, String query){

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
