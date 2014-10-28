package com.letsgood.synergykit.builders.uri;

public class Resource {
	
	/* Constructor */
	public static final String RESOURCE_COLLECTIONS = "collections";
	public static final String RESOURCE_DATA = "data";
	public static final String RESOURCE_FILES = "files";
	public static final String RESOURCE_LOGS = "logs";
	public static final String RESOURCE_USERS = "users";
	public static final String RESOURCE_USER_LOGIN = "users/login";
	public static final String RESOURCE_EMAIL = "mail";
	
	private static final String EXCEPTION_MESSAGE = "Resource must be set and must not be empty";
	
	
	/* Attributes */
	private String mResource = null;
	
	/* Resource setter */
	public void setResource(String resource){
		
		//null check
		if(resource==null || resource.length()==0){
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
		
		mResource = resource;		
	}
	
	
	/* Resource getter */
	public String getResource(){
		
		if(mResource==null){
			throw new NullPointerException(EXCEPTION_MESSAGE);
		}
		
		return mResource;
	}
}

