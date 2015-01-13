package com.synergykit.sdk.builders.uri;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.log.SynergyKITLog;

public class Filter {

	/* Constants */
	public static final String OPERATOR_EQUAL  = "eq";
	public static final String OPERATOR_NOT_EQUAL  = "ne";
	public static final String OPERATOR_GREATER_THAN  = "gt";
	public static final String OPERATOR_GREATER_THAN_OR_EQUAL  = "ge";
	public static final String OPERATOR_LESS_THAN  = "lt";
	public static final String OPERATOR_LESS_THAN_OR_EQUAL  = "le";
	public static final String OPERATOR_IN = "in";
	public static final String OPERATOR_NOT_IN = "nin"; 
	public static final String OPERATOR_AND  = "and";
	public static final String OPERATOR_OR  = "or";
	public static final String OPERATOR_NOT  = "and+not";
	
	/* Attributes */
	private String filter = null; 
	
	/* Filter setter */
	public void setFilter(String attribute, String operator, String parametr){
		
		if(attribute == null || attribute.length()==0 || operator==null || operator.length()==0|| parametr==null || parametr.length() ==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return;
		}
	
		
		filter=new String(attribute + "+" + operator + "+"  + parametr);
	}
	
	/* Filter setter */
	public void setFilter(String attribute, String operator, int parameter){
		
		if(attribute == null || attribute.length()==0 || operator==null || operator.length()==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return;
		}
			
		
		
		filter=new String(attribute + "+" + operator + "+" + Integer.toString(parameter));
	}
	
	
	/* Filter setter */
	public void setFilter(String filter){
		if(filter == null || filter.length()==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return;
		}
			
		
		
		this.filter=new String(filter);
	}
	

	
	
	/* Filter getter */
	public String getFilter(){
		
		String fullFilter = null;
		
		//not filter
		if(filter==null)
			return fullFilter;
		
		
		fullFilter = new String("&$filter=" + filter);
		
		
		
		return fullFilter;
	}
	
	/* Attribute  builder*/
	public static String buildAttribute(String attribute){
		if(attribute == null || attribute.length()==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}		
		
		return "'" + attribute + "'"; 
	}
	
	/* Parameter builder*/
	public static String buildParametr(String parameter){
		if(parameter == null || parameter.length()==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return "''";
		}
		
		return "'" + parameter	 + "'";
	}
	
	/* Array parameter builder */
	public static String buildArrayParameter(String[] arrayParameter){
		String parameter = new String();
		
		if(arrayParameter == null || arrayParameter.length==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return "''";
		}
		
		
		for(int i=0; i<arrayParameter.length; i++){
			
			//null check
			if(arrayParameter[i]==null)
				continue;
			
			//set comma
			if(i!=0)
				parameter+=",";
			
			parameter+=arrayParameter[i].toString();
		}
		
		if(!parameter.isEmpty())
			parameter = "'" + parameter + "'";
			
		return parameter;
	}
	
	
	/* SubString Of Filter */
	public static String buildSubStringOfFilter(String attribute, String parameter){
		String filter = null;
		
		if(attribute == null || attribute.length()==0 || parameter==null || parameter.length()==0){
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return "''";
		}
		
		filter =  new String("substringof("+attribute+ ",'" + parameter +"')");
		
		return filter.replaceAll(" +", "+"); //replace all spaces by 1 + char
	}
}
