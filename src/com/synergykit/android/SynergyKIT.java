package com.synergykit.android;

import java.lang.reflect.Type;

import org.apache.http.HttpEntity;

import com.synergykit.android.requestmanager.RequestManager;
import com.synergykit.android.resource.BaseUser;
import com.synergykit.android.resource.FileData;
import com.synergykit.android.resource.SynergylizeRequestAsyncTask;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.BaseUserResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetRecordsResponseListener;
import com.synergykit.android.response.GetUsersResponseListener;
import com.synergykit.android.usermanager.UserManager;

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
	public static boolean isInit() {
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

	// ----------------------------------------------------------------------------------------------------------------------

	/* Serialize */
	public static void synergylize(	SynergylizeRequestAsyncTask synergylizeRequestAsyncTask) {
		RequestManager.getInstance().synergylize(synergylizeRequestAsyncTask);
	}

	// ----------------------------------------------------------------------------------------------------------------------

	/* Get records */
	public static void getRecords(String collectionUrl,	GetRecordsResponseListener listener, Type type) {
		RequestManager.getInstance().getRecords(collectionUrl, listener, type);
	}

	/* Get record */
	public static void getRecord(String collectionUrl, String recordId, BaseResponseListener listener, Type type) {
		RequestManager.getInstance().getRecord(collectionUrl, recordId,
				listener, type);
	}

	/* Create record */ 
	public static void createRecord(String collectionUrl, Object object, BaseResponseListener listener, Type type) {
		RequestManager.getInstance().createRecord(collectionUrl, object,
				listener, type);
	}

	/* Create file */
	public static void createFile(BaseResponseListener listener,
			HttpEntity multiPartEntity) {
		RequestManager.getInstance().createFile(listener, FileData.class,
				multiPartEntity);
	}

	/* Update record */
	public static void updateRecord(String collectionUrl, String recordId, 	Object object, BaseResponseListener listener, Type type) {
		RequestManager.getInstance().updateRecord(collectionUrl, recordId, 	object, listener, type);
	}

	/* Delete record */
	public static void deleteRecord(String collectionUrl, String recordId, 	DeleteResponseListener listener) {
		RequestManager.getInstance().deleteRecord(collectionUrl, recordId,
				listener);
	}

	// ----------------------------------------------------------------------------------------------------------------------
	/* Get users */
	public static void getUsers(GetUsersResponseListener listener, Type type) {
		UserManager.getInstance().getUsers(listener, type);
	}

	/* Get user */
	public static void getUser(String userId,
			BaseUserResponseListener listener, Type type) {
		UserManager.getInstance().getUser(userId, listener, type);
	}

	/* Create user */
	public static void createUser(BaseUser baseUser,
			BaseUserResponseListener listener, Type type) {
		UserManager.getInstance().createUser(baseUser, listener, type);
	}

	/* Update user */
	public static void updateUser(String userId, BaseUser baseUser,
			BaseUserResponseListener listener, Type type) {
		UserManager.getInstance().updateUser(userId, baseUser, listener, type);
	}

	/* Delete user */
	public static void deleteUser(String userId, DeleteResponseListener listener) {
		UserManager.getInstance().deleteUser(userId, listener);
	}

	/* Login user */
	public static void loginUser(BaseUser baseUser,
			BaseUserResponseListener listener, Type type) {
		UserManager.getInstance().loginUser(baseUser, listener, type);
	}

	/* Register user */
	public static void registerUser(BaseUser baseUser,
			BaseUserResponseListener listener, Type type) {
		UserManager.getInstance().createUser(baseUser, listener, type);
	}
}

