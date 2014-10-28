package com.letsgood.synergykit.builders.uri;


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
	private static final String EXCEPTION_MESSAGE = "Attribute, operator and parameter must not be null and must not be empty";
	
	/* Attributes */
	private String mFilter = null; 
	
	/* Filter setter */
	public void setFilter(String attribute, String operator, String parametr){
		
		if(attribute == null || attribute.length()==0 || operator==null || operator.length()==0|| parametr==null || parametr.length() ==0)
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		
	
		
		mFilter=new String(attribute + "+" + operator + "+"  + parametr);
	}
	
	/* Filter setter */
	public void setFilter(String attribute, String operator, int parameter){
		
		if(attribute == null || attribute.length()==0 || operator==null || operator.length()==0)
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		
		
		mFilter=new String(attribute + "+" + operator + "+" + Integer.toString(parameter));
	}
	
	
	/* Filter setter */
	public void setFilter(String filter){
		if(filter == null || filter.length()==0)
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		
		
		mFilter=new String(filter);
	}
	
	/* Filter getter */
	public String getFilter(){
		
		String fullFilter = null;
		
		//not filter
		if(mFilter==null)
			return fullFilter;
		
		
		fullFilter = new String("&$filter=" + mFilter);
		
		
		
		return fullFilter;
	}
	
	/* Attribute  builder*/
	public static String buildAttribute(String attribute){
		if(attribute == null || attribute.length()==0)
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		
		
		return "'" + attribute + "'"; 
	}
	
	/* Parameter builder*/
	public static String buildParametr(String parameter){
		if(parameter == null || parameter.length()==0)
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		
		
		return "'" + parameter	 + "'";
	}
	
	/* Array parameter builder */
	public static String buildArrayParameter(String[] arrayParameter){
		String parameter = new String();
		
		if(arrayParameter == null || arrayParameter.length==0)
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		
		
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
}
