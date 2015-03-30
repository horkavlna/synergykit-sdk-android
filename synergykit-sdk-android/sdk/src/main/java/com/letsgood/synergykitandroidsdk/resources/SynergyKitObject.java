package com.letsgood.synergykitandroidsdk.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitandroidsdk.SynergyKit;
import com.letsgood.synergykitandroidsdk.interfaces.ISynergyKitObjectProtocol;
import com.letsgood.synergykitandroidsdk.listeners.DeleteResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.ResponseListener;


import java.io.Serializable;

public class SynergyKitObject implements Serializable, ISynergyKitObjectProtocol {
	
	/* Attributes */
    @Expose
    protected String _id;
    @Expose
    protected long __v;
    @Expose
    protected Long createdAt;
    @Expose
    protected Long updatedAt;

    protected String collection;

    /* Constructor */
    public SynergyKitObject(){
        this(null,null);
    }

    /* Constructor */
    public SynergyKitObject(String collectionUrl){
        this(collectionUrl,null);
    }

    /* Constructor */
    public SynergyKitObject(String collectionUrl, String recordId){
        setCollection(collectionUrl);
        setRecordId(recordId);
    }

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

    /* Collection getter */
    public String getCollection() {
        return collection;
    }

    /* Collection setter */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    /* Record Id getter */
    public void setRecordId(String recordId) {
        set__id(recordId);
    }

    /* Record Id setter */
    public String getRecordId() {
        return  get_id();
    }

    /* Save */
    @Override
    public void save() {
        this.save(null);
    }

    /* Fetch */
    @Override
    public void fetch() {
        this.fetch(null);
    }

    /* Delete */
    @Override
    public void delete() {
        this.delete(null);
    }

    /* Save */
    @Override
    public void save(ResponseListener listener) {

        if(getRecordId()==null)
            SynergyKit.createRecord(getCollection(), this, listener, false);
        else
            SynergyKit.updateRecord(getCollection(),this,listener,false);
    }

    /* Fetch */
    @Override
    public void fetch(ResponseListener listener) {
        SynergyKit.getRecord(getCollection(),get_id(),this.getClass(),listener,false);
    }

    /* Delete */
    @Override
    public void delete(DeleteResponseListener listener) {
        SynergyKit.deleteRecord(getCollection(),get_id(),listener,false);
    }
}
