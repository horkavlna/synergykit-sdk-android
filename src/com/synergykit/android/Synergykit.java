package com.synergykit.android;

import com.synergykit.android.provider.IProvider;
import com.synergykit.android.provider.Provider;


/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class Synergykit {
	/* Attributes */
	private static Synergykit mInstance=null;
	private IProvider mProvider;
	
	/* Constructor */
	public Synergykit(String tenant, String applicationKey){
		mProvider = new Provider(tenant, applicationKey);
	}
	
	
	public boolean createRecord(String collectionUrl, Object object){
		return mProvider.createRecord(collectionUrl, object);
	}
}
