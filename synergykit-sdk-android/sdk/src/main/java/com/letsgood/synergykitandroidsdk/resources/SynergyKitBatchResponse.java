package com.letsgood.synergykitandroidsdk.resources;

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitandroidsdk.addons.GsonWrapper;


import java.io.Serializable;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public class SynergyKitBatchResponse<T> extends SynergyKitObject implements Serializable {

    /*Attributes*/
    @Expose
    private int id;

    @Expose
    private String status;

    @Expose
    private int statusCode;

    @Expose
    private T body;

    /* Status Code getter */
    public int getStatusCode() {
        return statusCode;
    }

    /* Status Code setter */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /* Body getter */
    public T getBody() {
        return body;
    }

    /* Body setter */
    public void setBody(T body) {
        this.body = body;
    }

    /* Status getter */
    public String getStatus() {
        return status;
    }

    /* Status setter */
    public void setStatus(String status) {
        this.status = status;
    }

    /* Id getter */
    public int getId() {
        return id;
    }

    /* Id setter */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String tmpStatus, tmpBody;

        //status null check
        if(status==null)
            tmpStatus=new String("null");
        else
            tmpStatus=status;

        //body null check
        if(body==null)
            tmpBody=new String("null");
        else
            tmpBody = GsonWrapper.getGson().toJson(body);

        return "Id: " + Integer.toString(id) + ", StatusCode: " + Integer.toString(statusCode) +  ", Status: " + tmpStatus +  ", Body: " + tmpBody;
    }
}
