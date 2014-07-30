package com.synergykit.android.requesturl;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public class RequestUrl {
	
	
	/* GET all collections URL */
	public static String getAllCollectionsUrl(String tenant, String appKey){
		
		if(RequestUrl.isParamValid(tenant) && RequestUrl.isParamValid(appKey))
			return CollectionUrl.getUrl(tenant, appKey);
		
		throw new IllegalArgumentException();
	}
	
	
	/* GET collection URL */
	public static String getCollectionUrl(String tenant, String appKey, String collectionUrl){
		if(RequestUrl.isParamValid(tenant) && RequestUrl.isParamValid(appKey) && RequestUrl.isParamValid(collectionUrl))
			return CollectionUrl.getUrl(tenant, appKey,collectionUrl);
		
		throw new IllegalArgumentException();
	}
	
	/* POST collection URL */
	public static String postCollectionUrl(String tenant, String appKey){
		return RequestUrl.getAllCollectionsUrl(tenant, appKey);
	}
	
	/* PUT collection URL */
	public static  String putCollectionUrl(String tenant, String appKey, String collectionUrl){
		return RequestUrl.getCollectionUrl(tenant, appKey, collectionUrl);
	}
	
	/* DELETE collection URL */
	public static String deleteCollectionUrl(String tenant, String appKey, String collectionUrl){
		return RequestUrl.getCollectionUrl(tenant, appKey, collectionUrl);
	}
	
	/* GET all records URL */
	public static String getAllRecordsUrl(String tenant, String appKey, String collectionUrl){
		if(RequestUrl.isParamValid(tenant) && RequestUrl.isParamValid(appKey) && RequestUrl.isParamValid(collectionUrl))
			return DataUrl.getUrl(tenant, appKey,collectionUrl);
		
		throw new IllegalArgumentException();
	}
	
	/* GET record URL */
	public static String getRecordUrl(String tenant, String appKey, String collectionUrl, String recordId){
		if(RequestUrl.isParamValid(tenant) && RequestUrl.isParamValid(appKey) && RequestUrl.isParamValid(collectionUrl)&& RequestUrl.isParamValid(recordId))
			return DataUrl.getUrl(tenant, appKey,collectionUrl,recordId);
		
		throw new IllegalArgumentException();
	}
	
	/* POST record URL */
	public static String postRecordUrl(String tenant, String appKey, String collectionUrl){
		return RequestUrl.getAllRecordsUrl(tenant, appKey, collectionUrl);
	}
	
	/* PUT record URL */
	public static String putRecordUrl(String tenant, String appKey, String collectionUrl, String recordId){
		return RequestUrl.getRecordUrl(tenant, appKey, collectionUrl,recordId);
	}	

	/* DELETE record URL */
	public static String deleteRecordUrl(String tenant, String appKey, String collectionUrl, String recordId){
		return RequestUrl.getRecordUrl(tenant, appKey, collectionUrl,recordId);
	}	
	
	
	/* GET all logs url */
	public static String getAllLogsUrl(String tenant, String appKey){
		if(RequestUrl.isParamValid(tenant) && RequestUrl.isParamValid(appKey))
			return LogUrl.getUrl(tenant, appKey);
		
		throw new IllegalArgumentException();
	}
	
	/* GET log url */
	public static String getLogUrl(String tenant, String appKey, String logId){
		if(RequestUrl.isParamValid(tenant) && RequestUrl.isParamValid(appKey) && RequestUrl.isParamValid(logId))
			return LogUrl.getUrl(tenant, appKey,logId);
		
		throw new IllegalArgumentException();
	}
	
	
	/* GET all user URL */
	public static String getAllUsersUrl(String tenant, String appKey){
		if(RequestUrl.isParamValid(tenant) && RequestUrl.isParamValid(appKey))
			return UserUrl.getUrl(tenant, appKey);
		
		throw new IllegalArgumentException();
	}
	
	/* GET user URL */
	public static String getUserUrl(String tenant, String appKey, String userId){
		if(RequestUrl.isParamValid(tenant) && RequestUrl.isParamValid(appKey) && RequestUrl.isParamValid(userId))
			return UserUrl.getUrl(tenant, appKey,userId);
		
		throw new IllegalArgumentException();
	}
	
	/* POST user URL */
	public static String postUserUrl(String tenant, String appKey){
		return RequestUrl.getAllUsersUrl(tenant, appKey);
	}
	
	/* PUT user URL */
	public static String putUserUrl(String tenant, String appKey, String userId){
		return RequestUrl.getUserUrl(tenant, appKey, userId);
	}	

	/* DELETE user URL */
	public static String deleteUserUrl(String tenant, String appKey, String userId){
		return RequestUrl.getUserUrl(tenant, appKey, userId);
	}	
	
	
	/* Is param valid */
	private static boolean isParamValid(String value){
		if(value!=null && value.length()>0)
			return true;
		
		return false;
	}



	
	
}
