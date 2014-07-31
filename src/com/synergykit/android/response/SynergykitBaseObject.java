package com.synergykit.android.response;

/**
 * Created by tomas_000 on 24.2.14.
 */
public abstract class SynergykitBaseObject {
    protected String id;
    protected String version;
    protected long createdAt;
    protected long updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public void setCreatedAt(long createdAt){
    	this.createdAt=createdAt;
    }
    
    public long getCreatedAt(){
    	return this.createdAt;
    }
    
    public void setUpdatedAt(long updatedAt){
    	this.updatedAt = updatedAt;
    }
    
    public long getUpdatedAt(){
    	return this.updatedAt;
    }
}

