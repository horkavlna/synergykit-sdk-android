package com.synergykit.sdk.builders.uri;


/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.log.SynergyKITLog;

public class MailId {

	/* Attributes */
	private String mailId = null;
	
	
	/* Resource setter */
	public void setMailId(String mailId){
		
		//null check
		if(mailId==null || mailId.length()==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}	
		
		this.mailId = mailId;		
	}
	
	
	/* Resource getter */
	public String getMailId(){
		return mailId;
	}
}
