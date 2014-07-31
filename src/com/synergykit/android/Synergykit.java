package com.synergykit.android;

import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.provider.Provider;
import com.synergykit.android.resource.ISynergykitResponseListener;

/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class Synergykit {

	/* Init */
	public static void init(String tenant, String applicationKey) {
		Provider.getInstance().init(tenant, applicationKey);
	}

	/* Get record */
	public static void getRecord(String collectionUrl, String recordId, ISynergykitResponseListener listener, Class<?> classOfT){
		try {
			Provider.getInstance().getRecord(collectionUrl, recordId, listener, classOfT);
		} catch (NotInitializedException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Create record */
	public static void createRecord(String collectionUrl, Object object, ISynergykitResponseListener listener,Class<?> classOfT){
		try {
			Provider.getInstance().createRecord(collectionUrl, object, listener, classOfT);
		} catch (NotInitializedException e) {
			e.printStackTrace();
		}
	}
	
	


}
