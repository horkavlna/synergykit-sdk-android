package com.letsgood.synergykit.resource;

public class SynergyKITObject {
	
	/* Attributes */
	protected String _id; 
    protected String __v; 
    protected long createdAt; 
    protected long updatedAt;

    /* Id getter */
    public String get_id() {
        return _id;
    }

    /* Id setter */
    public void set__id(String _id) {
        this._id = _id;
    }
    /* Version getter */
    public String get__v() {
        return __v;
    }

    /* Version setter */
    public void set__v(String __v) {
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
}
