package com.synergykit.android.provider;

import java.util.List;

public interface IProvider {
	public boolean getAllRecords(String collectionUrl, List<?> list,Class<?> classOf); //get all records of collection on storage
	public boolean getRecord(String collectionUrl, String recordId,Class<?> classOf); //get record of collection on storage
	public boolean createRecord(String collectionUrl, Object object);	//add object to storage 
	public boolean updateRecord(String collectionUrl, String recordId, Object object); //update existing record
	
	
	
	
	
}
