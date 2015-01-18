package com.synergykit.sdk.builders.uri;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.log.SynergyKITLog;

public class FunctionId {

	/* Attributes */
	private String functionId = null;
	
	
	/* Resource setter */
	public void setFunctionId(String functionId){
		
		//null check
		if(functionId==null || functionId.length()==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}	
		
		this.functionId = functionId;		
	}
	
	
	/* Resource getter */
	public String getFunctionId(){
		return functionId;
	}
}
