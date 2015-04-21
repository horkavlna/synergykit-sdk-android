package com.letsgood.synergykitsdkandroid.builders.uri;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

public class Skip {
	/* Constants */
	private static final int MIN_VALUE = 0;
	
	/* Attributes */
	private String skip;

    /* New instance */
    public static Skip newInstace(){
        return new Skip();
    }
	
	/* Top setter */
	public Skip setSkip(int skip){
		
		if(skip<MIN_VALUE){
			SynergykitLog.print(Errors.MSG_SKIP_NEGATIVE);
		}
			
		
		this.skip = new String("$skip=" + Integer.toString(skip));
        return this;
	}
	
	
	/* Top getter */
	public String getSkip(){
		return skip;
	}
}
