package com.synergykit.android;

import java.lang.reflect.Type;

import com.synergykit.android.request.Request;
import com.synergykit.android.resource.BaseRequestAsyncTask;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetRecordsResponseListener;

/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class Synergykit {

	/* Init */
	public static void init(String tenant, String applicationKey) {
		Request.getInstance().init(tenant, applicationKey);
	}

	/* Reset */
	public static void reset(){
		Request.getInstance().reset();
	}
	
	/* Tenant setter */
	public static void setTenant(String tenant) {
		Request.getInstance().setTenant(tenant);
	}

	/* Tenant getter */
	public static String getTenant() {
		return Request.getInstance().getTenant();
	}

	/* Application key setter */
	public static void setApplicationKey(String applicationKey) {
		Request.getInstance().setApplicationKey(applicationKey);
		
	}

	/* Application key getter */
	public static String getApplicationKey() {
		return Request.getInstance().getApplicationKey();
	}
	
	/* Serialize */
	public static void synergylize(BaseRequestAsyncTask baseRequestAsyncTask){
		Request.getInstance().synergylize(baseRequestAsyncTask);
	}
	
	/* Get records */
	public static void getRecords(String collectionUrl, GetRecordsResponseListener listener, Type type){
		Request.getInstance().getRecords(collectionUrl, listener, type);
	}
	
	/* Get record */
	public static void getRecord(String collectionUrl, String recordId, BaseResponseListener listener, Type type){
		Request.getInstance().getRecord(collectionUrl, recordId, listener, type);
	}
	
	/* Create record */
	public static void createRecord(String collectionUrl, Object object, BaseResponseListener listener, Type type){
		Request.getInstance().createRecord(collectionUrl, object, listener,type);
	}
	
	/* Update record */
	public static void updateRecord(String collectionUrl, String recordId, Object object, BaseResponseListener listener, Type type){
		Request.getInstance().updateRecord(collectionUrl, recordId, object, listener,type);
	}
	
	/* Delete record */
	public static void deleteRecord(String collectionUrl, String recordId, DeleteResponseListener listener){
		Request.getInstance().deleteRecord(collectionUrl, recordId, listener);
	}
}
