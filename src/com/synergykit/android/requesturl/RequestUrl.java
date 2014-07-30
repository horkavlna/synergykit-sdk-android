package com.synergykit.android.requesturl;

import com.google.gson.Gson;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public class RequestUrl {
	
	/* Attributes */
	private static CollectionUrl mCollectionUrl = new CollectionUrl();
	private static DataUrl mDataUrl = new DataUrl();
	private static LogUrl mLogUrl = new LogUrl();
	private static UserUrl mUserUrl = new UserUrl();

	
	
	
	/* GET all collections URL */
	public String getAllCollectionsUrl(String tenant, String appKey){
		
		if(this.isParamValid(tenant) && this.isParamValid(appKey))
			return mCollectionUrl.getUrl(tenant, appKey);
		
		throw new IllegalArgumentException();
	}
	
	
	/* GET collection URL */
	public String getCollectionUrl(String tenant, String appKey, String collectionUrl){
		if(this.isParamValid(tenant) && this.isParamValid(appKey) && this.isParamValid(collectionUrl))
			return mCollectionUrl.getUrl(tenant, appKey,collectionUrl);
		
		throw new IllegalArgumentException();
	}
	
	/* POST collection URL */
	public String postCollectionUrl(String tenant, String appKey){
		return this.getAllCollectionsUrl(tenant, appKey);
	}
	
	/* PUT collection URL */
	public  String putCollectionUrl(String tenant, String appKey, String collectionUrl){
		return this.getCollectionUrl(tenant, appKey, collectionUrl);
	}
	
	/* DELETE collection URL */
	public String deleteCollectionUrl(String tenant, String appKey, String collectionUrl){
		return this.getCollectionUrl(tenant, appKey, collectionUrl);
	}
	
	/* GET all records URL */
	public String getAllRecordsUrl(String tenant, String appKey, String collectionUrl){
		if(this.isParamValid(tenant) && this.isParamValid(appKey) && this.isParamValid(collectionUrl))
			return mDataUrl.getUrl(tenant, appKey,collectionUrl);
		
		throw new IllegalArgumentException();
	}
	
	/* GET record URL */
	public  String getRecordUrl(String tenant, String appKey, String collectionUrl, String recordId){
		if(this.isParamValid(tenant) && this.isParamValid(appKey) && this.isParamValid(collectionUrl)&& this.isParamValid(recordId))
			return mDataUrl.getUrl(tenant, appKey,collectionUrl,recordId);
		
		throw new IllegalArgumentException();
	}
	
	/* POST record URL */
	public String postRecordUrl(String tenant, String appKey, String collectionUrl){
		return this.getAllRecordsUrl(tenant, appKey, collectionUrl);
	}
	
	/* PUT record URL */
	public String putRecordUrl(String tenant, String appKey, String collectionUrl, String recordId){
		return this.getRecordUrl(tenant, appKey, collectionUrl,recordId);
	}	

	/* DELETE record URL */
	public  String deleteRecordUrl(String tenant, String appKey, String collectionUrl, String recordId){
		return this.getRecordUrl(tenant, appKey, collectionUrl,recordId);
	}	
	
	
	/* GET all logs url */
	public String getAllLogsUrl(String tenant, String appKey){
		if(this.isParamValid(tenant) && this.isParamValid(appKey))
			return mLogUrl.getUrl(tenant, appKey);
		
		throw new IllegalArgumentException();
	}
	
	/* GET log url */
	public String getLogUrl(String tenant, String appKey, String logId){
		if(this.isParamValid(tenant) && this.isParamValid(appKey) && this.isParamValid(logId))
			return mLogUrl.getUrl(tenant, appKey,logId);
		
		throw new IllegalArgumentException();
	}
	
	
	/* GET all user URL */
	public String getAllUsersUrl(String tenant, String appKey){
		if(this.isParamValid(tenant) && this.isParamValid(appKey))
			return mUserUrl.getUrl(tenant, appKey);
		
		throw new IllegalArgumentException();
	}
	
	/* GET user URL */
	public  String getUserUrl(String tenant, String appKey, String userId){
		if(this.isParamValid(tenant) && this.isParamValid(appKey) && this.isParamValid(userId))
			return mUserUrl.getUrl(tenant, appKey,userId);
		
		throw new IllegalArgumentException();
	}
	
	/* POST user URL */
	public String postUserUrl(String tenant, String appKey){
		return this.getAllUsersUrl(tenant, appKey);
	}
	
	/* PUT user URL */
	public String putUserUrl(String tenant, String appKey, String userId){
		return this.getUserUrl(tenant, appKey, userId);
	}	

	/* DELETE user URL */
	public String deleteUserUrl(String tenant, String appKey, String userId){
		return this.getUserUrl(tenant, appKey, userId);
	}	
	
	
	/* Is param valid */
	private boolean isParamValid(String value){
		if(value!=null && value.length()>0)
			return true;
		
		return false;
	}



	
	
}
