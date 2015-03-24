package com.synergykit.sdk.builders.uri;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.log.SynergyKitLog;

public class RecordId {

	/* Attributes */
	private String recordId = null;
	
	
	/* Resource setter */
	public void setRecordId(String recordId){	
		
		//null check
		if(recordId==null || recordId.length()==0){
			SynergyKitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}	
		
		this.recordId = recordId;		
	}
	
	
	/* Resource getter */
	public String getRecordId(){		
		return recordId;
	}
}
