package com.synergykit.sdk.resources;

import android.webkit.URLUtil;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.log.SynergyKITLog;

import java.io.Serializable;

public class SynergyKITUri  implements Serializable {
	
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
