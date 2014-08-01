package com.synergykit.android.requesturl;

/**
 * 
 * @author Pavel Stambrecht
 *
 */


public class CollectionUrl {
	
	/* Constants */
	final private static String SLASH = "/";
	final private static String URL_TEMPLATE = "https://%s.api.synergykit.com/collections%s?application=%s";
	
	
	/* Get url */
	public static String getUrl(String tenant, String appKey ){
		 return CollectionUrl.getUrl(tenant, appKey, null);
	}
	
	/* Get url */
	public static String getUrl(String tenant, String appKey, String collectionUrl ){
		
		//set collection url
		if(collectionUrl!=null && collectionUrl.length()>0)
			collectionUrl = SLASH+collectionUrl;
		else
			collectionUrl = new String();
		
		return String.format(URL_TEMPLATE, tenant,collectionUrl,appKey);		
	}
	

}
