package com.letsgood.synergykit.builders.uri;

public class RecordId {

	/* Constructor */
	private static final String EXCEPTION_MESSAGE = "Database must not be null and must not be empty";
	
	/* Attributes */
	private String mRecordId = null;
	
	
	/* Resource setter */
	public void setRecordId(String recordId){
		
		//null check
		if(recordId==null || recordId.length()==0){
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
		
		mRecordId = recordId;		
	}
	
	
	/* Resource getter */
	public String getRecordId(){
		return mRecordId;
	}
}
