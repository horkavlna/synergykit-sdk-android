package com.letsgood.synergykitsdkandroid.resources;

import com.google.gson.annotations.Expose;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public class SynergykitBatchItem {

    /* Attributes */
    @Expose
    private String method;
    @Expose
    private String endpoint;
    @Expose
    private int id;

    @Expose
    private Object body;


    /* Constructor */
    public SynergykitBatchItem(String method, SynergykitEndpoint endpoint){
        this(0,method,endpoint,null);
    }

    public SynergykitBatchItem(String method, SynergykitEndpoint endpoint, Object body){
        this(0,method,endpoint,body);
    }

    public SynergykitBatchItem(int id, String method, SynergykitEndpoint endpoint){
        this(id,method,endpoint,null);
    }

    public SynergykitBatchItem(int id, String method, SynergykitEndpoint endpoint, Object body){
        this.method = method;

        if(endpoint==null){
            throw new NullPointerException();
        }

        this.endpoint = endpoint.toString();
        this.id = id;
        this.body = body;
    }

    /* Method getter */
    public String getMethod() {
        return method;
    }

    /* Method setter */
    public void setMethod(String method) {
        this.method = method;
    }

    /* Path getter */
    public String getEndpoint() {
        return this.endpoint;
    }

    /* Path setter */
    public void setEndpoint(SynergykitEndpoint endpoint) {
        if(endpoint==null){
            throw new NullPointerException();
        }

        this.endpoint = endpoint.toString();
    }

    /* Id getter */
    public int getId() {
        return id;
    }

    /* Id setter */
    public void setId(int id) {
        this.id = id;
    }

    /* Body getter */
    public Object getBody() {
        return body;
    }

    /* Body setter */
    public void setBody(Object body) {
        this.body = body;
    }

}
