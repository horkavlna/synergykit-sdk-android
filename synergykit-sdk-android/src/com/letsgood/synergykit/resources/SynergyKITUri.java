package com.letsgood.synergykit.resources;

import android.webkit.URLUtil;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.log.SynergyKITLog;

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
		String uri = this.uri;
		SynergyKITLog.print(URI + uri);
		
		if( URLUtil.isValidUrl(uri)==false || uri.contains(" ")){			
			
			SynergyKITLog.print(Errors.MSG_URI_NOT_VALID);
			
			uri=null;
		}
	 
	
		return uri;
	}
}
