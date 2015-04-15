package com.letsgood.synergykitsdkandroid.builders.uri;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

public class RecordId {

	/* Attributes */
	private String recordId = null;
	
	
	/* Resource setter */
	public void setRecordId(String recordId){	
		
		//null check
		if(recordId==null || recordId.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}	
		
		this.recordId = recordId;		
	}
	
	
	/* Resource getter */
	public String getRecordId(){		
		return recordId;
	}
}
