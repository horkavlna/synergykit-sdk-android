package com.letsgood.synergykitsdkandroid.builders.uri;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

public class Top {

	/* Constants */
	private static final int MIN_VALUE = 0;

	/* Attributes */
	private String top;

    /* New instance */
    public static Top newInstance(){
        return new Top();
    }
	
	/* Top setter */
	public Top setTop(int top){
		
		if(top<MIN_VALUE){
			SynergykitLog.print(Errors.MSG_TOP_NEGATIVE);
		}
		
		this.top = new String("$top=" + Integer.toString(top));
        return this;
	}
	
	
	/* Top getter */
	public String getTop(){		
		return top;
	}
}
