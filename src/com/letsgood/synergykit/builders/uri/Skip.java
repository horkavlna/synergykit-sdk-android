package com.letsgood.synergykit.builders.uri;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.log.SynergyKITLog;

public class Skip {
	/* Constants */
	private static final int MIN_VALUE = 0;
	
	/* Attributes */
	private String skip;
	
	
	/* Top setter */
	public void setSkip(int skip){
		
		if(skip<MIN_VALUE){
			SynergyKITLog.print(Errors.MSG_SKIP_NEGATIVE);
		}
			
		
		this.skip = new String("&$skip=" + Integer.toString(skip));
	}
	
	
	/* Top getter */
	public String getSkip(){
		return skip;
	}
}
