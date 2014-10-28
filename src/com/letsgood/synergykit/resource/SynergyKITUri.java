package com.letsgood.synergykit.resource;

import android.webkit.URLUtil;

public class SynergyKITUri {
	/* Constants */
	private final static String EXCEPTION_MESSAGE = "Not valid URI";
	
	/* Attributes */
	private String mUri;
	
	/* Constructor */
	public SynergyKITUri(String uri){
		this.mUri = uri;
	}	
	
	/* Uri getter */
	public String getUri(){
		String uri = null;
		
		
		//TODO Uncomment
		//Init check
	/*	if(!SynergyKIT.isInit()){
			try {
				throw new NotInitializedException();
			} catch (NotInitializedException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		uri = String.format(mUri, SynergyKIT.getTenant());		
		
		if( URLUtil.isValidUrl(uri)==false || uri.contains(" ")){
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
	 
	 */
		return uri;
	}
}
