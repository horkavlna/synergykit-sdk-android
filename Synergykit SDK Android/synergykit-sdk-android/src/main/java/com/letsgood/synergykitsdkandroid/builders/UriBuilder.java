package com.letsgood.synergykitsdkandroid.builders;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.uri.Collection;
import com.letsgood.synergykitsdkandroid.builders.uri.Filter;
import com.letsgood.synergykitsdkandroid.builders.uri.RecordId;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEndpoint;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

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

    /* Filter setter */
    public UriBuilder setFilter(Filter filter){
        oDataBuilder.setFilter(filter);
        return this;
    }

	/* Select setter */
	public UriBuilder addSelect(String attribute){
        oDataBuilder.addSelect(attribute);
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
	public SynergykitUri build(){
        SynergykitUri synergyKitUri = null;
		String uri = new String(SynergykitConfig.API_SYNERGYKIT_URL);

	    //set tenant
		uri = String.format(uri, Synergykit.getTenant());

        synergyKitUri = new SynergykitUri(uri);
        synergyKitUri.setEndpoint(buildEndpoint());
		
		return synergyKitUri;
	}

    /* Socket address getter*/
    public SynergykitUri getSocketUrl(){
        String uri = new String(SynergykitConfig.SOCKET_SYNERGYKIT_URL);

        //set tenant
        uri = String.format(uri, Synergykit.getTenant());

        return new SynergykitUri(uri);
    }


    public SynergykitEndpoint buildEndpoint(){
        String endpoint = new String();
       boolean hasFilters = false;

        endpoint += "/" + resource.getResource();  //set resource

        if(collection.getCollection()!=null)
            endpoint += "/" + collection.getCollection();  //set collection


        if(recordId.getRecordId()!=null)
            endpoint += "/" + recordId.getRecordId();	//set resource id


        if(oDataBuilder.build()!=null)
            endpoint += oDataBuilder.build();            // set OData


        return new SynergykitEndpoint(endpoint);
    }
}
