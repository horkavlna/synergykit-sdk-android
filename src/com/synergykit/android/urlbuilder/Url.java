package com.synergykit.android.urlbuilder;

import com.synergykit.android.Synergykit;
import com.synergykit.android.exception.NotInitializedException;

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
		if(!Synergykit.isInit()){
			try {
				throw new NotInitializedException();
			} catch (NotInitializedException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		url = String.format(mUrl, Synergykit.getTenant());		
		url += "?application=" + Synergykit.getApplicationKey();
		
		return url;
	}
}
