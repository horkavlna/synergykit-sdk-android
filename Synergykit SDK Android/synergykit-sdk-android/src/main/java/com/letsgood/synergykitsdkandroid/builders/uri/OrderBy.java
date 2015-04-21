package com.letsgood.synergykitsdkandroid.builders.uri;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

import java.util.LinkedList;
import java.util.List;

public class OrderBy{

	/* Constants */
	private static final String DIRECTION_DESCENDING = "desc";
	private static final String DIRECTION_ASCENDING = "asc";
	private static final int MAX_SIZE = 12;

	
	/* Attributes */
	private List<String> orderByList = new LinkedList<String>();

    /* New instance */
    public static OrderBy newInstance(){
        return new OrderBy();
    }
	
	/* Order by desc */
	public OrderBy setOrderByAsc(String parameter){
		this.setOrderBy(parameter, DIRECTION_ASCENDING);
        return this;
	}
	
	
	/* Order by desc */
	public OrderBy setOrderByDesc(String parameter){
		this.setOrderBy(parameter, DIRECTION_DESCENDING);

		return this;
	}
	
	/* Order by */
	private OrderBy setOrderBy(String parameter, String direction){
		//parameter check
		if(parameter==null || parameter.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
		
		//size check
		if(orderByList.size()>=MAX_SIZE){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}
			
		
		
		//add order by
		orderByList.add(parameter + "+" + direction);

        return this;
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
				fullOrderBy = new String( "$orderby=" + orderByList.get(i));
			else
				fullOrderBy += "," + orderByList.get(i);
		}
	
		
		return fullOrderBy;	
	}
}
