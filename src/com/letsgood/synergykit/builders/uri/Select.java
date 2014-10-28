package com.letsgood.synergykit.builders.uri;

import java.util.LinkedList;
import java.util.List;

public class Select {

	/* Constants */
	private static final String EXCEPTION_MESSAGE = "Select attribute must not be null and must not be empty";

	
	/* Attributes */
	private List<String> mSelectList = new LinkedList<String>();
	
	
	/* Select setter */
	public void setSelect(String attribute){
		if(attribute == null || attribute.length()==0 )
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);

		mSelectList.add(attribute);
	}
	
	/* Select getter */
	public String getSelect(){
		String fullSelect = null;
		
		if(mSelectList.isEmpty())
			return fullSelect;
		
		
		//set order by
		for(int i=0; i<mSelectList.size(); i++){
			
			if(i==0)
				fullSelect = new String( "&$select=" + mSelectList.get(i));
			else
				fullSelect += "," + mSelectList.get(i);
		}
		
		return fullSelect;
	}
}
