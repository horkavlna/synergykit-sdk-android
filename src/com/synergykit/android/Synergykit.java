package com.synergykit.android;

import java.lang.reflect.Type;

import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.provider.Provider;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetAllResponseListener;

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

	/* Reset */
	public static void reset(){
		Provider.getInstance().reset();
	}
	
	/* Get record */
	public static void getRecord(String collectionUrl, String recordId, BaseResponseListener listener, Type type){
		try {
			Provider.getInstance().getRecord(collectionUrl, recordId, listener, type);
		} catch (NotInitializedException e) {
			e.printStackTrace();
		}
	}
	
	/* Get all records */
	public static void getAllRecords(String collectionUrl,GetAllResponseListener listener, Type type){
		try {
			Provider.getInstance().getAllRecords(collectionUrl, listener,type);
		} catch (NotInitializedException e) {
			e.printStackTrace();
		}
	}
	
	/* Create record */
	public static void createRecord(String collectionUrl, Object object, BaseResponseListener listener,Type type){
		try {
			Provider.getInstance().createRecord(collectionUrl, object, listener, type);
		} catch (NotInitializedException e) {
			e.printStackTrace();
		}
	}
	
	/* Update record */
	public static void updateRecord(String collectionUrl, String recordId, Object object, BaseResponseListener listener, Type type){
		try {
			Provider.getInstance().updateRecord(collectionUrl, recordId, object, listener, type);
		} catch (NotInitializedException e) {
			e.printStackTrace();
		}
	}

	/* Delete record */
	public static void deleteRecord(String collectionUrl, String recordId, DeleteResponseListener listener){
		try {
			Provider.getInstance().deleteRecord(collectionUrl, recordId, listener);
		} catch (NotInitializedException e) {
			e.printStackTrace();
		}
	}

}
