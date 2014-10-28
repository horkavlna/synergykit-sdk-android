package com.letsgood.synergykit.builders.uri;

import java.util.LinkedList;
import java.util.List;

public class OrderBy{

	/* Constants */
	private static final String DIRECTION_DESCENDING = "desc";
	private static final String DIRECTION_ASCENDING = "asc";
	private static final int MAX_SIZE = 12;
	private static final String EXCEPTION_MESSAGE_NULL_EMPTY = "Parameter must not be null and must not be empty";
	private static final String EXCEPTION_MESSAGE_FULL = "Maximum size of orderby is 12";
	
	
	/* Attributes */
	private List<String> mOrderByList = new LinkedList<String>();
	
	/* Order by desc */
	public void setOrderByAsc(String parameter){
		this.setOrderBy(parameter, DIRECTION_ASCENDING);		
	}
	
	
	/* Order by desc */
	public void setOrderByDesc(String parameter){
		this.setOrderBy(parameter, DIRECTION_DESCENDING);

		
	}
	
	/* Order by */
	private void setOrderBy(String parameter, String direction){
		//parameter check
		if(parameter==null || parameter.length()==0){
			throw new IllegalAccessError(EXCEPTION_MESSAGE_NULL_EMPTY);
		}
		
		//size check
		if(mOrderByList.size()>=MAX_SIZE)
			throw new IllegalAccessError(EXCEPTION_MESSAGE_FULL);
		
		
		//add order by
		mOrderByList.add(parameter + "+" + direction);
	}
	
	
	/* Order by getter */
	public String getOrderBy(){
		String fullOrderBy = null;
		
		//no filter	
		if(mOrderByList.isEmpty())
			return fullOrderBy;
		

		//set order by
		for(int i=0; i<mOrderByList.size(); i++){
			
			if(i==0)
				fullOrderBy = new String( "&$orderby=" + mOrderByList.get(i));
			else
				fullOrderBy += "," + mOrderByList.get(i);
		}
	
		
		return fullOrderBy;	
	}
}
