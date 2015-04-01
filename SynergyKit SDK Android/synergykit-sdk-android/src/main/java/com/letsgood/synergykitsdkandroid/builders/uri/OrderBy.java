package com.letsgood.synergykitsdkandroid.builders.uri;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergyKitLog;

import java.util.LinkedList;
import java.util.List;

public class OrderBy{

	/* Constants */
	private static final String DIRECTION_DESCENDING = "desc";
	private static final String DIRECTION_ASCENDING = "asc";
	private static final int MAX_SIZE = 12;

	
	/* Attributes */
	private List<String> orderByList = new LinkedList<String>();
	
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
			SynergyKitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
		
		//size check
		if(orderByList.size()>=MAX_SIZE){
			SynergyKitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
			
		
		
		//add order by
		orderByList.add(parameter + "+" + direction);
	}
	
	
	/* Order by getter */
	public String getOrderBy(){
		String fullOrderBy = null;
		
		//no filter	
		if(orderByList.isEmpty())
			return fullOrderBy;
		

		//set order by
		for(int i=0; i<orderByList.size(); i++){
			
			if(i==0)
				fullOrderBy = new String( "&$orderby=" + orderByList.get(i));
			else
				fullOrderBy += "," + orderByList.get(i);
		}
	
		
		return fullOrderBy;	
	}
}
