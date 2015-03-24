package com.synergykit.sdk.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.SynergyKit;
import com.synergykit.sdk.interfaces.ISynergyKitObjectProtocol;
import com.synergykit.sdk.listeners.DeleteResponseListener;
import com.synergykit.sdk.listeners.ResponseListener;

import java.io.Serializable;

public class SynergyKitObject implements Serializable, ISynergyKitObjectProtocol {
	
	/* Attributes */
	protected String _id; 
    protected long __v;
    protected Long createdAt; 
    protected Long updatedAt;

    
    /* Id getter */
    public String get_id() {
        return _id;
    }

    /* Id setter */
    public void set__id(String _id) {
        this._id = _id;
    }
    /* Version getter */
    public long get__v() {
        return __v;
    }

    /* Version setter */
    public void set__v(long __v) {
        this.__v = __v;
    }
    
    /* CreatedAt setter */
    public void setCreatedAt(long createdAt){
    	this.createdAt=createdAt;
    }
    
    /* CreatedAt getter */
    public long getCreatedAt(){
    	return this.createdAt;
    }
    
    /* UpdateAt setter */
    public void setUpdatedAt(long updatedAt){
    	this.updatedAt = updatedAt;
    }
    
    /* UpdateAt getter */
    public long getUpdatedAt(){
    	return this.updatedAt;
    }


    @Override
    public void save() {

    }

    @Override
    public void fetch() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void save(ResponseListener listener) {

    }

    @Override
    public void fetch(ResponseListener listener) {

    }

    @Override
    public void delete(DeleteResponseListener listener) {

    }
}
