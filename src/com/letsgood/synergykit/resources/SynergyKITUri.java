package com.letsgood.synergykit.resources;

import android.webkit.URLUtil;

import com.letsgood.synergykit.log.SynergyKITLog;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
import com.letsgood.synergykit.builders.errors.Errors;

public class SynergyKITUri {
	
	/* Constants */
	private static final String URI = "URI: ";
	
	/* Attributes */
	private String uri;
	
	/* Constructor */
	public SynergyKITUri(String uri){
		this.uri = uri;
	}	
	
	/* Uri getter */
	public String toString(){
		SynergyKITLog.print(URI + uri);
		
		if( URLUtil.isValidUrl(uri)==false || uri.contains(" ")){			
			
			SynergyKITLog.print(Errors.MSG_URI_NOT_VALID);
			
			uri=null;
		}
	 
	
		return uri;
	}
}
