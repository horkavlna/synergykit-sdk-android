package com.synergykit.android.requesturl;

/**
 * 
 * @author Pavel Stambrecht
 *
 */

public class LogUrl {
	
	/* Constants */
	final private static String SLASH = "/";
	final private static String URL_TEMPLATE = "https://%s.api.synergykit.com/logs%s?application=%s";

	
	/* Get url */
	public static String getUrl(String tenant, String appKey){
		return LogUrl.getUrl(tenant, appKey, null);
	}
	
	
	/* Get url */
	public static String getUrl(String tenant, String appKey, String logId){
		
		//set log id
		if(logId!=null && logId.length()>0)
			logId = SLASH + logId;
		else 
			logId = new String();
		
		
		return String.format(URL_TEMPLATE, tenant,logId,appKey);
	}
}
