package com.letsgood.synergykit.builders.uri;

import android.util.Log;

import com.letsgood.synergykit.SynergyKIT;
import com.letsgood.synergykit.SynergyKITSdk;
import com.letsgood.synergykit.builders.errors.Errors;

public class Skip {
	/* Constants */
	private static final int MIN_VALUE = 0;
	
	/* Attributes */
	private String skip;
	
	
	
	/* Top setter */
	public void setSkip(int skip){
		
		if(skip<MIN_VALUE){
			//Log
			if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_SKIP_NEGATIVE);
		}
			
		
		this.skip = new String("&$skip=" + Integer.toString(skip));
	}
	
	
	/* Top getter */
	public String getSkip(){
		return skip;
	}
}
