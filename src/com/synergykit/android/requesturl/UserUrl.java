package com.synergykit.android.requesturl;

/**
 * 
 * @author Pavel Stambrecht
 *
 */


public class UserUrl {
	
	/* Constants */
	final private static String SLASH = "/";
	final private static String URL_TEMPLATE = "https://%s.api.synergykit.com/users%s?application=%s";

	
	/*Get url */
	public static String getUrl(String tenant, String appKey){
		return UserUrl.getUrl(tenant, appKey, null);
	} 
	
	/*Get url */
	public static String getUrl(String tenant, String appKey, String userId){
		
		//set user id
		if(userId!=null && userId.length() > 0)
			userId = SLASH + userId;
		else 
			userId = new String();
		
		return String.format(URL_TEMPLATE, tenant,userId,appKey);
	} 
}
