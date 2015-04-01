package com.letsgood.synergykitsdkandroid.builders.uri;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergyKitLog;

public class Top {

	/* Constants */
	private static final int MIN_VALUE = 0;
	
	/* Attributes */
	private String top;
	
	/* Top setter */
	public void setTop(int top){	
		
		if(top<MIN_VALUE){
			SynergyKitLog.print(Errors.MSG_TOP_NEGATIVE);
		}
		
		this.top = new String("&$top=" + Integer.toString(top));
	}
	
	
	/* Top getter */
	public String getTop(){		
		return top;
	}
}
