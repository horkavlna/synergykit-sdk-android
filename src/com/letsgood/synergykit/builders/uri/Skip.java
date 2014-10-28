package com.letsgood.synergykit.builders.uri;

public class Skip {
	/* Constants */
	private static final String EXCEPTION_MESSAGE = "Skip must not be a negative number";
	private static final int MIN_VALUE = 0;
	
	/* Attributes */
	private String mSkip;
	
	
	
	/* Top setter */
	public void setSkip(int skip){
		
		if(skip<MIN_VALUE)
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		
		mSkip = new String("&$skip=" + Integer.toString(skip));
	}
	
	
	/* Top getter */
	public String getSkip(){
		return mSkip;
	}
}
