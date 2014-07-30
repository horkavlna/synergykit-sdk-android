package com.synergykit.android;

import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.provider.Provider;
import com.synergykit.android.resource.ISynergykitResponseListener;

/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class Synergykit {
	private static Synergykit mInstance = null;

	
	/* Instance getter */
	/*public static Synergykit getInstance() {
		
		if (mInstance == null) {
			mInstance = new Synergykit();
		}
		
		return mInstance;
	}*/

	/* Init */
	public static void init(String tenant, String applicationKey) {
		Provider.getInstance().init(tenant, applicationKey);
	}

	
	/* Create record */
	public static boolean createRecord(String collectionUrl, Object object, ISynergykitResponseListener listener)
			throws NotInitializedException {

		return Provider.getInstance().createRecord(collectionUrl, object, listener);
	}


}
