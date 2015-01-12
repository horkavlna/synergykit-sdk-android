package com.letsgood.synergykit.builders.uri;

import java.util.LinkedList;
import java.util.List;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.log.SynergyKITLog;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

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
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
		
		//size check
		if(orderByList.size()>=MAX_SIZE){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
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