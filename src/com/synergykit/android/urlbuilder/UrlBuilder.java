package com.synergykit.android.urlbuilder;

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
	
	/* Values checker */
	private void checkValue(String value, boolean canBeEmpty){
		
		//null pointer check
		if(!canBeEmpty && value==null){
			throw new NullPointerException();
		}
			
		
		//value length check
		if(value!=null && value.length()<MIN_VALUE_LENGTH){
			throw new IllegalArgumentException();
		}
	}
	
	/* Build */
	public Url build(){
		String url = new String(BASE_SYNERGYKIT_URL);
		
		//set resource
		this.checkValue(mResource, false);
		url += "/" + mResource;
		
		//set resource url
		this.checkValue(mResourceUrl, false);
		url += "/" + mResourceUrl;
		
		//set resource id 
		this.checkValue(mResourceId, true);
		if(mResourceId!=null)
			url += "/" + mResourceId;
		
		return new Url(url);	
	}
}
