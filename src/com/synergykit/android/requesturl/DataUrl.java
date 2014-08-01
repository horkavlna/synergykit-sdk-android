package com.synergykit.android.requesturl;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public class DataUrl {
	
	/* Constants */
	final private static String SLASH = "/";
	final private static String URL_TEMPLATE = "https://%s.api.synergykit.com/data/%s%s?application=%s";
	
	/*Get url */
	public static String getUrl(String tenant, String appKey, String collectionUrl){
		return DataUrl.getUrl(tenant, appKey, collectionUrl,null);
	}
	
	/*Get url */
	public static String getUrl(String tenant, String appKey, String collectionUrl, String recordId){
		
		//set record id
		if(recordId!=null && recordId.length() > 0)
			recordId = SLASH + recordId;
		else
			recordId= new String();
		
		
		return String.format(URL_TEMPLATE,tenant,collectionUrl, recordId , appKey);
	}
}
