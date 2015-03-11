package com.synergykit.sdk.resources;

import com.google.gson.annotations.Expose;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public class SynergyKITBatchResponse<T> extends SynergyKITObject implements Serializable {

    /*Attributes*/
    private int id;
    private String status;
    private int statusCode;
    private T body;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id: " + Integer.toString(id) + ", StatusCode: " + Integer.toString(statusCode) +  ", Status: " + status +  ", Body: " + body;
    }
}
