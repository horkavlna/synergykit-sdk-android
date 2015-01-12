package com.letsgood.synergykit.builders.uri;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.log.SynergyKITLog;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class Resource {
	
	/* Constructor */
	public static final String RESOURCE_COLLECTIONS = "collections";
	public static final String RESOURCE_DATA = "data";
	public static final String RESOURCE_FILES = "files";
	public static final String RESOURCE_LOGS = "logs";
	public static final String RESOURCE_USERS = "users";
	public static final String RESOURCE_USER_LOGIN = "users/login";
	public static final String RESOURCE_EMAIL = "mail";
	public static final String RESOURCE_NOTIFICATION = "users/notification";
	

	/* Attributes */
	private String resource = null;
	
	/* Resource setter */
	public void setResource(String resource){	
		this.resource = resource;		
	}
	
	
	/* Resource getter */
	public String getResource(){
		
		//null check
		if(resource==null || resource.length()==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
		
		return resource;
	}
}
