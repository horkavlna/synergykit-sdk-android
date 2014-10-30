package com.letsgood.synergykit.resources;

import com.letsgood.synergykit.SynergyKIT;
import com.letsgood.synergykit.SynergyKITSdk;
import com.letsgood.synergykit.builders.errors.Errors;

import android.util.Log;
import android.webkit.URLUtil;

public class SynergyKITUri {
	
	/* Attributes */
	private String uri;
	
	/* Constructor */
	public SynergyKITUri(String uri){
		this.uri = uri;
	}	
	
	/* Uri getter */
	public String toString(){
		String uri = null;
		
		uri = String.format(this.uri, SynergyKIT.getTenant());		
		
		if( URLUtil.isValidUrl(uri)==false || uri.contains(" ")){			
			
			if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_URI_NOT_VALID + " " + uri.toString());
			
			uri=null;
		}
	 
	
		return uri;
	}
}
