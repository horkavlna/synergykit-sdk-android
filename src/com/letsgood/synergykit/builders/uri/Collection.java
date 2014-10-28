package com.letsgood.synergykit.builders.uri;

public class Collection {

	/* Constructor */
	private static final String EXCEPTION_MESSAGE = "Collection must be set and must not be empty";
	
	/* Attributes */
	private String collection = null;
	
	
	/* Resource setter */
	public void setCollection(String collection){
		
		//null check
		if(collection==null || collection.length()==0){
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
		
		this.collection = collection;		
	}
	
	
	/* Resource getter */
	public String getCollection(){
		return collection;
	}
}
