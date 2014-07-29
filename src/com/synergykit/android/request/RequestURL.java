package com.synergykit.android.request;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public class RequestURL {
	
	/* Constants */
	final private static String SLASH = "/";
	final private static String[] TYPES = {"collections","data"};

	
	final private static String URL_TEMPLATE = 	"https://%s.api.synergykit.com%s%s%s?application=%s";
	
	
	/* GET all collections URL */
	public static String getAllCollectionsUrl(String tenant, String appKey){
		String url= new String();

		//valid params
		if(!validateParams(tenant, appKey, null, null)){
			throw new IllegalArgumentException();
		}
		
		
		//init url
		url = new String(URL_TEMPLATE);
		
		//replace tags
		url = replaceTags(url, tenant, appKey, TYPES[0], null, null);			
		
		return url;
	}
	
	
	/* GET collection URL */
	public static String getCollectionUrl(String tenant, String appKey, String collectionUrl){
		String url = new String();
		
		//valid params
		if(!validateParams(tenant, appKey, collectionUrl, null))
			throw new IllegalArgumentException();			
				
		//init url
		url = new String(URL_TEMPLATE);
		
		//replace tags
		url = replaceTags(url, tenant, appKey, TYPES[0], collectionUrl, null);			
		
		return url;
	}
	
	/* POST collection URL */
	public static String postCollectionUrl(String tenant, String appKey){
		return RequestURL.getAllCollectionsUrl(tenant, appKey);
	}
	
	/* PUT collection URL */
	public static String putCollectionUrl(String tenant, String appKey, String collectionUrl){
		return RequestURL.getCollectionUrl(tenant, appKey,collectionUrl);
	}
	
	/* DELETE collection URL */
	public static String deleteCollectionUrl(String tenant, String appKey, String collectionUrl){
		return RequestURL.getCollectionUrl(tenant, appKey,collectionUrl);
	}
	
	/* GET all records URL */
	public static String getAllRecordsUrl(String tenant, String appKey, String collectionUrl){
		String url= new String();

		//valid params
		if(!validateParams(tenant, appKey, collectionUrl, null))
			throw new IllegalArgumentException();
		
		
		//init url
		url = new String(URL_TEMPLATE);
		
		//replace tags
		url = replaceTags(url, tenant, appKey, TYPES[1], collectionUrl, null);			
		
		return url;
	}
	
	/* GET record URL */
	public static String getRecordUrl(String tenant, String appKey, String collectionUrl, String recordId){
		String url= new String();

		//valid params
		if(!validateParams(tenant, appKey, collectionUrl, recordId))
			throw new IllegalArgumentException();
		
		
		//init url
		url = new String(URL_TEMPLATE);
		
		//replace tags
		url = replaceTags(url, tenant, appKey, TYPES[1], collectionUrl, recordId);			
		
		return url;
	}
	
	/* POST record URL */
	public static String postRecordUrl(String tenant, String appKey, String collectionUrl){
		return RequestURL.getAllRecordsUrl(tenant, appKey, collectionUrl);
	}
	
	/* PUT record URL */
	public static String putRecordUrl(String tenant, String appKey, String collectionUrl, String recordId){
		return RequestURL.getRecordUrl(tenant, appKey, collectionUrl,recordId);
	}	

	/* DELETE record URL */
	public static String deleteRecordUrl(String tenant, String appKey, String collectionUrl, String recordId){
		return RequestURL.getRecordUrl(tenant, appKey, collectionUrl,recordId);
	}	
	
	/* Validate params */
	private static boolean validateParams(String tenant, String appKey, String collectionUrl, String recordId){
		
		//tenant check
		if(tenant==null || tenant.length()<1)
			return false;
		
		//application key check
		if(appKey==null || appKey.length()<1)
			return false;
		
		
		//collection url check
		if(collectionUrl!=null && collectionUrl.length()<1)
			return false;
		
		//record id check
		if(recordId!=null && recordId.length()<1)
			return false;
		
		
		return true;
	}

	/* Replace tags */
	private static String replaceTags(String url, String tenant, String appKey, String type, String collectionUrl, String recordId){
		String[] values = new String[5];
		
		//set tenant
		values[0] = tenant;
		
		//set type
		values[1] = SLASH + type;
		
		//set collection url
		if(collectionUrl!=null && collectionUrl.length()>0)
			values[2] = SLASH + collectionUrl;
		else
			values[2] = "";
		
		//set record id
		if(recordId!=null && recordId.length()>0)
			values[3] = SLASH + recordId;
		else
			values[3] = "";
		
		//set application key
		values[4] = appKey;
	
		//replace
		url = String.format(url, values[0],values[1],values[2],values[3],values[4]);
		
		
		return url;
	}
	
	
}
