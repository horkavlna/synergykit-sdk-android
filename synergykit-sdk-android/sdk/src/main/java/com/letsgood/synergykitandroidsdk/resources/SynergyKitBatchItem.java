package com.letsgood.synergykitandroidsdk.resources;

import com.google.gson.annotations.Expose;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public class SynergyKitBatchItem {

    /* Attributes */
    @Expose
    private String method;
    @Expose
    private String url;
    @Expose
    private int id;
    @Expose
    private Object body;


    /* Constructor */
    public SynergyKitBatchItem(String method, String url){
        this(0,method,url,null);
    }

    public SynergyKitBatchItem(String method, String url, Object body){
        this(0,method,url,body);
    }

    public SynergyKitBatchItem(int id, String method, String url){
        this(id,method,url,null);
    }

    public SynergyKitBatchItem(int id, String method, String url, Object body){
        this.method = method;
        this.url = url;
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
    public String getUrl() {
        return url;
    }

    /* Path setter */
    public void setUrl(String path) {
        this.url = path;
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
