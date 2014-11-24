package com.letsgood.synergykit.builders.uri;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.log.SynergyKITLog;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class RecordId {

	/* Attributes */
	private String recordId = null;
	
	
	/* Resource setter */
	public void setRecordId(String recordId){	
		
		//null check
		if(recordId==null || recordId.length()==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}	
		
		this.recordId = recordId;		
	}
	
	
	/* Resource getter */
	public String getRecordId(){		
		return recordId;
	}
}
