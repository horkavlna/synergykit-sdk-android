package com.synergykit.android;


import java.lang.reflect.Type;

import com.synergykit.android.requestmanager.RequestManager;
import com.synergykit.android.resource.SynergylizeRequestAsyncTask;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetRecordsResponseListener;
import com.synergykit.android.response.GetUsersResponseListener;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKIT {

	/* Init */
	public static void init(String tenant, String applicationKey) {
		RequestManager.getInstance().reset();
		RequestManager.getInstance().init(tenant, applicationKey);
	}

	/* Is init */
	public static boolean isInit(){
		return RequestManager.getInstance().isInit();
	}
	
	
	/* Tenant setter */
	public static void setTenant(String tenant) {
		RequestManager.getInstance().setTenant(tenant);
	}

	/* Tenant getter */
	public static String getTenant() {
		return RequestManager.getInstance().getTenant();
	}

	/* Application key setter */
	public static void setApplicationKey(String applicationKey) {
		RequestManager.getInstance().setApplicationKey(applicationKey);
		
	}

	/* Application key getter */
	public static String getApplicationKey() {
		return RequestManager.getInstance().getApplicationKey();
	}
	//----------------------------------------------------------------------------------------------------------------------
	
	/* Serialize */
	public static void synergylize(SynergylizeRequestAsyncTask synergylizeRequestAsyncTask){
		RequestManager.getInstance().synergylize(synergylizeRequestAsyncTask);
	}
	//----------------------------------------------------------------------------------------------------------------------
	
	/* Get records */
	public static void getRecords(String collectionUrl, GetRecordsResponseListener listener, Type type){
		RequestManager.getInstance().getRecords(collectionUrl, listener, type);
	}
	
	/* Get record */
	public static void getRecord(String collectionUrl, String recordId, BaseResponseListener listener, Type type){
		RequestManager.getInstance().getRecord(collectionUrl, recordId, listener, type);
	}
	
	/* Create record */
	public static void createRecord(String collectionUrl, Object object, BaseResponseListener listener, Type type){
		RequestManager.getInstance().createRecord(collectionUrl, object, listener,type);
	}
	
	/* Update record */
	public static void updateRecord(String collectionUrl, String recordId, Object object, BaseResponseListener listener, Type type){
		RequestManager.getInstance().updateRecord(collectionUrl, recordId, object, listener,type);
	}
	
	/* Delete record */
	public static void deleteRecord(String collectionUrl, String recordId, DeleteResponseListener listener){
		RequestManager.getInstance().deleteRecord(collectionUrl, recordId, listener);
	}
	//----------------------------------------------------------------------------------------------------------------------
	public static void getUsers(GetUsersResponseListener listener,Type type){
		RequestManager.getInstance().getUsers(listener, type);
	}
}
	
