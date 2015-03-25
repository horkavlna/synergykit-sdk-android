package com.synergykit.sdk.builders;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.SynergyKit;
import com.synergykit.sdk.builders.uri.Collection;
import com.synergykit.sdk.builders.uri.RecordId;
import com.synergykit.sdk.builders.uri.Resource;

import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitUri;

public class UriBuilder {

	/* Attributes */
	private Resource resource = new Resource();
	private Collection collection = new Collection();
	private RecordId recordId = new RecordId();
    private ODataBuilder oDataBuilder = ODataBuilder.newInstance();

    /* New instance */
    public static UriBuilder newInstance(){
        return new UriBuilder();

    }

	/* Resource setter */
	public UriBuilder setResource(String resource){
		this.resource.setResource(resource);
		return this;
	}

    /* Resource collection setter */
	public UriBuilder setCollection(String collection){
		this.collection.setCollection(collection);
		return this;
	}

	/* Resource Id setter */
	public UriBuilder setRecordId(String recordId){
		this.recordId.setRecordId(recordId);
		return this;
	}

    /* Mail Id setter */
    public UriBuilder setMailId(String mailId){
        return this.setRecordId(mailId);
    }

    /* Funcion Id setter */
    public UriBuilder setFunctionId(String functionId){
        return this.setRecordId(functionId);
    }
	
	/* Filter setter */
	public UriBuilder setFilter(String attribute, String operator, String parametr){
		oDataBuilder.setFilter(attribute, operator, parametr);;
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String attribute, String operator, int parametr){
        oDataBuilder.setFilter(attribute, operator, parametr);
		return this;
	}
	
	/* Filter setter */
	public UriBuilder setFilter(String filter){
        oDataBuilder.setFilter(filter);
		return this;
	}
	
	/* Select setter */
	public UriBuilder setSelect(String attribute){
        oDataBuilder.setSelect(attribute);
		return this;
	}
	
	/* Order by desc setter */
	public UriBuilder setOrderByDesc(String parameter){
        oDataBuilder.setOrderByDesc(parameter);
		return this;
	}

	/* Order by asc setter */
	public UriBuilder setOrderByAsc(String parameter){
        oDataBuilder.setOrderByAsc(parameter);
		return this;
	}
	
	/* Top setter */
	public UriBuilder setTop(int top){
        oDataBuilder.setTop(top);
		return this;
	}
	
	/* Top setter */
	public UriBuilder setSkip(int skip){
        oDataBuilder.setSkip(skip);
		return this;
	}

    /* Inline count setter */
	public UriBuilder setInLineCountEnabled(boolean enabled){
		oDataBuilder.setInLineCountEnabled(enabled);
		return this;
	}

	/* Build */
	public SynergyKitUri build(){
		String uri = new String(SynergyKitConfig.API_SYNERGYKIT_URL);
		boolean hasFilters = false;
		
		
		uri += "/" + resource.getResource();  //set resource	

		if(collection.getCollection()!=null)
			uri += "/" + collection.getCollection();  //set collection
		
		
		if(recordId.getRecordId()!=null)
			uri += "/" + recordId.getRecordId();	//set resource id 
		

        if(oDataBuilder.build()!=null)
            uri += oDataBuilder.build();            // set OData


	    //set tenant
		uri = String.format(uri, SynergyKit.getTenant());
		
		
		return new SynergyKitUri(uri);
	}

    /* Socket address getter*/
    public SynergyKitUri getSocketUrl(){
        String uri = new String(SynergyKitConfig.SOCKET_SYNERGYKIT_URL);

        //set tenant
        uri = String.format(uri, SynergyKit.getTenant());

        return new SynergyKitUri(uri);
    }
}
