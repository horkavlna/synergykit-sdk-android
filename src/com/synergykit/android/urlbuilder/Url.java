package com.synergykit.android.urlbuilder;

public class Url {
	
	/* Attributes */
	private String mUrl;
	
	/* Constructor */
	public Url(String url){
		this.mUrl = url;
	}
	
	/* Url getter */
	public String getUrl(String tenant, String applicationKey){
		String url;
		
		//null pointer check
		if(tenant==null || applicationKey==null)
			return null;
		
		url = String.format(mUrl, tenant);
		url += "?application=" + applicationKey;
		
		return url;
	}
}
