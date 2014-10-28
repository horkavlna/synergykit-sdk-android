package com.letsgood.synergykit;

import java.lang.reflect.Type;

import com.letsgood.synergykit.listeners.DeleteListener;
import com.letsgood.synergykit.listeners.RecordsResponseListener;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.request.SynergyKITRequest;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITObject;

public class SynergyKIT {

	/* Synergylize */
	public static void synergylize(SynergyKITRequest request, boolean parallelMode){
		SynergyKITSdk.getInstance().synergylize(request, parallelMode);
	}
	
	//------------------------------------------------------------------------------
	/* Init */
	public static void init(String tenant, String applicationKey) {
		SynergyKITSdk.getInstance().init(tenant, applicationKey);
	}

	/* Reset */
	public static void reset() {
		SynergyKITSdk.getInstance().reset();	
	}

	
	/* Tenant setter */
	public static void setTenant(String tenant) {
		SynergyKITSdk.getInstance().setTenant(tenant);
	}

	/* Tenant getter */
	public static String getTenant() {
		return SynergyKITSdk.getInstance().getTenant();
	}

	/* Application key setter */
	public static void setApplicationKey(String applicationKey) {
		SynergyKITSdk.getInstance().setApplicationKey(applicationKey);		
	}

	/* Application key getter */
	public static String getApplicationKey() {
		return SynergyKITSdk.getInstance().getApplicationKey();
	}

	/* Is init */
	public static boolean isInit() {
		return SynergyKITSdk.getInstance().isInit();
	}
	
	/* Config setter */
	public static void setConfig(SynergyKITConfig config) {
		SynergyKITSdk.getInstance().setConfig(config);
	}

	/* Config getter */
	public static SynergyKITConfig getConfig() {
		return SynergyKITSdk.getInstance().getConfig();
	}
	//------------------------------------------------------------------------------
	/* Get record */
	public static void getRecord(SynergyKITConfig config, ResponseListener listener) {
		SynergyKITSdk.getInstance().getRecord(config, listener);
	}

	/* Get record */
	public static void getRecord(String collectionUrl, String recordId, Type type,	ResponseListener listener, boolean parallelMode) {
		SynergyKITSdk.getInstance().getRecord(collectionUrl, recordId, type, listener, parallelMode);
		
	}
	
	/* Get records */
	public static void getRecords(SynergyKITConfig config, RecordsResponseListener listener) {
		SynergyKITSdk.getInstance().getRecords(config, listener);
		
	}

	/* Get records */
	public static void getRecords(String collectionUrl, Type type, RecordsResponseListener listener, boolean parallelMode) {
		SynergyKITSdk.getInstance().getRecords(collectionUrl, type, listener, parallelMode);
		
	}

	/* Create record */
	public static void createRecord(String collectionUrl, SynergyKITObject object, ResponseListener listener, boolean parallelMode) {
		SynergyKITSdk.getInstance().createRecord(collectionUrl, object, listener, parallelMode);
		
	}

	/* Update record */
	public static void updateRecord(String collectionUrl, String recordId,	SynergyKITObject object, ResponseListener listener,	boolean parallelMode) {
		SynergyKITSdk.getInstance().updateRecord(collectionUrl, recordId, object, listener, parallelMode);
		
	}

	/* Delete record */
	public static void deleteRecord(String collectionUrl, String recordId,	DeleteListener listener, boolean parallelMode) {
		SynergyKITSdk.getInstance().deleteRecord(collectionUrl, recordId, listener, parallelMode);		
	}
}
