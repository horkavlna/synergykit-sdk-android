package com.letsgood.synergykitsdkandroid.builders.uri;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

public class Filter {

	/* Constants */
	public static final String OPERATOR_EQUAL  = "+eq+";
	public static final String OPERATOR_NOT_EQUAL  = "+ne+";
	public static final String OPERATOR_GREATER_THAN  = "+gt+";
	public static final String OPERATOR_GREATER_THAN_OR_EQUAL  = "+ge+";
	public static final String OPERATOR_LESS_THAN  = "+lt+";
	public static final String OPERATOR_LESS_THAN_OR_EQUAL  = "+le+";
	public static final String OPERATOR_IN = "+in+";
	public static final String OPERATOR_NOT_IN = "+nin+";
	public static final String OPERATOR_AND  = "+and+";
	public static final String OPERATOR_OR  = "+or+";
	public static final String OPERATOR_NOT  = "+and+not+";
	
	/* Attributes */
	private String filter = null; 

    /* New instance*/
    public static Filter newInstance(){
        return new Filter();
    }

	/* Filter setter */
	public Filter setFilter(String attribute, String operator, String parameter){
		
		if(attribute == null || attribute.length()==0 || operator==null || operator.length()==0|| parameter==null || parameter.length() ==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return this;
		}
	
		
		filter=Filter.buildAttribute(attribute)+operator+Filter.buildParametr(parameter);
        return this;

	}
	
	/* Filter setter */
	public Filter setFilter(String attribute, String operator, int parameter){
		
		if(attribute == null || attribute.length()==0 || operator==null || operator.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
            return this;
		}



        filter=Filter.buildAttribute(attribute)+operator+Filter.buildParametr(parameter);
        return this;
	}
	
	
	/* Filter setter */
	public Filter setFilter(String filter){
		if(filter == null || filter.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
            return this;
		}
			
		
		
		this.filter=new String(filter);
        return this;
	}
	

	
	
	/* Filter getter */
	public String getFilter(){
		
		String fullFilter = null;
		
		//not filter
		if(filter==null)
			return fullFilter;
		
		
		fullFilter = new String("$filter=" + filter);
		
		
		
		return fullFilter;
	}

    /* Filter body getter */
    public String getFilterBodyForSocket(){
      return filter.replaceAll("\\+"," ");
    }
	
	/* Attribute  builder*/
	public static String buildAttribute(String attribute){
		if(attribute == null || attribute.length()==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}		
		
		return "'" + attribute + "'"; 
	}
	
	/* Parameter builder*/
	public static String buildParametr(int parameter){
		return "" + parameter + "";
	}

    /* Parameter builder*/
    public static String buildParametr(boolean parameter){
        return (parameter ?  "true" : "false");
    }

    /* Parameter builder*/
    public static String buildParametr(double parameter){
        return "" + parameter + "d";
    }

    /* Parameter builder*/
    public static String buildParametr(float parameter){
        return "" + parameter + "d";
    }

    /* Parameter builder*/
    public static String buildParametr(long parameter){
        return "" + parameter + "";
    }

    /* Parameter builder*/
    public static String buildParametr(short parameter){
        return "" + parameter + "";
    }

    /* Parameter builder*/
    public static String buildParametr(String parameter){
        return "'" + parameter + "'";
    }
	
	/* Array parameter builder */
	public static String buildArrayParameter(String[] arrayParameter){
		String parameter = new String();
		
		if(arrayParameter == null || arrayParameter.length==0){
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
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
			SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
			return "''";
		}
		
		filter =  new String("substringof("+attribute+ ",'" + parameter +"')");
		
		//return filter.replaceAll(" +", "+"); //replace all spaces by 1 + char
        return filter;
	}

    /* Start with Filter */
    public static String buildStartsWithFilter(String attribute, String parameter){
        String filter = null;

        if(attribute == null || attribute.length()==0 || parameter==null || parameter.length()==0){
            SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
            return "''";
        }

        filter =  new String("startswith("+attribute+ ",'" + parameter +"')");

        return filter;
    }

    /* Start with Filter */
    public static String buildEndsWithFilter(String attribute, String parameter){
        String filter = null;

        if(attribute == null || attribute.length()==0 || parameter==null || parameter.length()==0){
            SynergykitLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
            return "''";
        }

        filter =  new String("endswith("+attribute+ ",'" + parameter +"')");

        return filter;
    }


}
