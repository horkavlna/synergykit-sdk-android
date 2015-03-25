package com.synergykit.sdk.resources;

import android.webkit.URLUtil;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
import com.google.gson.annotations.Expose;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.log.SynergyKitLog;

import java.io.Serializable;

public class SynergyKitUri implements Serializable {
	
	/* Constants */
	private static final String URI = "URI: ";
	
	/* Attributes */
	private String uri;
	
	/* Constructor */
	public SynergyKitUri(String uri){
		this.uri = uri;
	}	
	
	/* Uri getter */
	public String toString(){
		String uri = this.uri;
		SynergyKitLog.print(URI + uri);
		
		if( URLUtil.isValidUrl(uri)==false || uri.contains(" ")){			
			
			SynergyKitLog.print(Errors.MSG_URI_NOT_VALID);
			
			uri=null;
		}
	 
	
		return uri;
	}
}
