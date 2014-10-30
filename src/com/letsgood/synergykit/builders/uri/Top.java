package com.letsgood.synergykit.builders.uri;

import android.util.Log;

import com.letsgood.synergykit.SynergyKIT;
import com.letsgood.synergykit.SynergyKITSdk;
import com.letsgood.synergykit.builders.errors.Errors;

public class Top {

	/* Constants */
	private static final int MIN_VALUE = 0;
	
	/* Attributes */
	private String top;
	
	
	
	/* Top setter */
	public void setTop(int top){	
		
		if(top<MIN_VALUE){
			//Log
			if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_TOP_NEGATIVE);
		}
		
		this.top = new String("&$top=" + Integer.toString(top));
	}
	
	
	/* Top getter */
	public String getTop(){		
		return top;
	}
}
