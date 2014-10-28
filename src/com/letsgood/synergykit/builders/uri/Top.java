package com.letsgood.synergykit.builders.uri;

public class Top {

	/* Constants */
	private static final String EXCEPTION_MESSAGE = "Top must not be a negative number";
	private static final int MIN_VALUE = 0;
	
	/* Attributes */
	private String mTop;
	
	
	
	/* Top setter */
	public void setTop(int top){
		
		if(top<MIN_VALUE)
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		
		mTop = new String("&$top=" + Integer.toString(top));
	}
	
	
	/* Top getter */
	public String getTop(){
		return mTop;
	}
}
