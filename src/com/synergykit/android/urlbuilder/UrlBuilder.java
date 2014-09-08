package com.synergykit.android.urlbuilder;

import java.util.LinkedList;
import java.util.List;

import android.util.Log;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class UrlBuilder {
	/* Constants */
	private static final String BASE_SYNERGYKIT_URL = "https://%s.api.synergykit.com";
	final public static String RESOURCE_COLLECTIONS = "collections";
	final public static String RESOURCE_DATA = "data";

	final public static String RESOURCE_FILES = "files";

	final public static String RESOURCE_LOGS = "logs";
	final public static String RESOURCE_USERS = "users";
	final public static String RESOURCE_USER_LOGIN = "users/login";
	final public static String RESOURCE_VARIANTS = "variants";
	private static final int MIN_VALUE_LENGTH = 1;
	private static final int MAX_ORDER_BY_SIZE  = 12;
	
	/* Attributes */
	private String mResource = null;
	private String mResourceUrl = null;
	private String mResourceId = null;
	private String mFilter = null;
	private String mSelect = null;
	private Integer mTop = null;
	private String mInlineCount = null;
	private Integer mSkip = null;
	private List<String> mOrderBy = new LinkedList<String>();
	
		
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
	
	/* Select setter */
	public UrlBuilder setSelect(String select){
		this.mSelect = select;
		return this;
	}
	
	/* In line count setter */
	public UrlBuilder setInlineCount(String inlineCount){
		this.mInlineCount = inlineCount;
		return this;
	}
	
	/* Top setter */
	public UrlBuilder setTop(int top){
		this.mTop = top;
		return this;
	}
	
	/* Skip setter */
	public UrlBuilder setSkip(int skip){
		this.mSkip = skip;
		return this;
	}
	
	/* Order By setter */
	public UrlBuilder setOrderBy(String orderBy){
		
		if(this.mOrderBy.size() == MAX_ORDER_BY_SIZE)
			return this;
		
		this.mOrderBy.add(orderBy);
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
		
		//set select
		if(this.checkValue(mSelect))
			url="&$select=" + mSelect;
		
		//set top
		if(mTop!=null)
			url+="&$top=" + mTop.toString();
		
		//set order by
		for (String orderBy : mOrderBy) {
			if(this.checkValue(orderBy))
				url+="&$orderby=" + orderBy;
		}
		
		
		//set skip
		if(mSkip != null)
			url+="&$skip=" + mSkip.toString();
		
		//set in line count
		if(this.checkValue(mInlineCount))
			url+="&$inlinecount=" + mInlineCount;
		
		Log.e("SynergyKIT",url);
		
		return new Url(url);	
	}
}
