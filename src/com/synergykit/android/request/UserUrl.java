package com.synergykit.android.request;

public class UserUrl {
	/* Attributes */
	final private static String SLASH = "/";
	final private static String URL_TEMPLATE = "https://%s.api.synergykit.com/users%s?application=%s";

	
	/*Get url */
	public String getUrl(String tenant, String appKey){
		return this.getUrl(tenant, appKey, null);
	} 
	
	/*Get url */
	public String getUrl(String tenant, String appKey, String userId){
		
		//set user id
		if(userId!=null && userId.length() > 0)
			userId = SLASH + userId;
		else 
			userId = new String();
		
		return String.format(URL_TEMPLATE, tenant,userId,appKey);
	} 
}
