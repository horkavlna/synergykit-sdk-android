package com.letsgood.synergykit.resources;

import com.letsgood.synergykit.SynergyKIT;

import android.webkit.URLUtil;

public class SynergyKITUri {
	/* Constants */
	private final static String EXCEPTION_MESSAGE = "Not valid URI";
	
	/* Attributes */
	private String uri;
	
	/* Constructor */
	public SynergyKITUri(String uri){
		this.uri = uri;
	}	
	
	/* Uri getter */
	public String getUri(){
		String uri = null;
		
		
		
		//Init check
		if(!SynergyKIT.isInit()){
			return uri;
		}
		
		uri = String.format(this.uri, SynergyKIT.getTenant());		
		
		if( URLUtil.isValidUrl(uri)==false || uri.contains(" ")){
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
	 
	
		return uri;
	}
}
