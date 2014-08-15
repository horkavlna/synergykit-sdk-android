package com.synergykit.android.urlbuilder;

import com.synergykit.android.SynergyKIT;
import com.synergykit.android.exception.NotInitializedException;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class Url {
	
	/* Attributes */
	private String mUrl;
	
	/* Constructor */
	public Url(String url){
		this.mUrl = url;
	}
	
	
	
	/* Url getter */
	public String getUrl(){
		String url;
		
		//Init check
		if(!SynergyKIT.isInit()){
			try {
				throw new NotInitializedException();
			} catch (NotInitializedException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		url = String.format(mUrl, SynergyKIT.getTenant(),"?application=" + SynergyKIT.getApplicationKey());		
		
		return url;
	}
}
