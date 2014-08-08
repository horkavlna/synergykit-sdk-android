package com.synergykit.android.urlbuilder;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class UrlBuilder {
	/* Constants */
	private static final String BASE_SYNERGYKIT_URL = "https://%s.api.synergykit.com";
	final public static String RESOURCE_COLLECTIONS = "collections";
	final public static String RESOURCE_DATA = "data";
	final public static String RESOURCE_LOGS = "logs";
	final public static String RESOURCE_USERS = "users";
	final public static String RESOURCE_VARIANTS = "variants";
	private static final int MIN_VALUE_LENGTH = 1;
	
	/* Attributes */
	private String mResource = null;
	private String mResourceUrl = null;
	private String mResourceId = null;
	private String mFilter = null;
	
		
	/* Resource setter */
	public UrlBuilder setResource(String resource){
		this.mResource = resource;
		return this;
	}
	
	/* Resource URL setter */
	public UrlBuilder setResourceUrl(String resourceUrl){
		this.mResourceUrl = resourceUrl;
		return this;
	}
	
	/* Resource Id setter */
	public UrlBuilder setResourceId(String resourceId){
		this.mResourceId = resourceId;
		return this;
	}
	
	/* Filter setter */
	public UrlBuilder setFilter(String filter){
		this.mFilter=filter;
		return this;
	}
	
	/* Values checker */
	private boolean checkValue(String value){
		
		//null pointer check
		if( value==null){
			return false;
		}
			
		
		//value length check
		if(value!=null && value.length()<MIN_VALUE_LENGTH){
			return false;
		}
		
		return true;
	}
	
	/* Build */
	public Url build(){
		String url = new String(BASE_SYNERGYKIT_URL);
		
		//set resource
		if(this.checkValue(mResource))
			url += "/" + mResource;
		
		//set resource url
		if(this.checkValue(mResourceUrl))
			url += "/" + mResourceUrl;
		
		//set resource id 
		if(this.checkValue(mResourceId))
			url += "/" + mResourceId;
		
		//add application key tag
		url+="%s";
		
		//set filter
		if(this.checkValue(mFilter))
			url+="&$filter=" + mFilter;
		
		return new Url(url);	
	}
}
