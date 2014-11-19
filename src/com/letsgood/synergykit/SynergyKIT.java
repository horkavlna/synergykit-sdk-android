package com.letsgood.synergykit;

import java.lang.reflect.Type;

import android.content.Context;
import android.graphics.Bitmap;

import com.letsgood.synergykit.listeners.BitmapResponseListener;
import com.letsgood.synergykit.listeners.BytesResponseListener;
import com.letsgood.synergykit.listeners.DeleteResponseListener;
import com.letsgood.synergykit.listeners.EmailResponseListener;
import com.letsgood.synergykit.listeners.FileDataResponseListener;
import com.letsgood.synergykit.listeners.NotificationResponseListener;
import com.letsgood.synergykit.listeners.RecordsResponseListener;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.listeners.UserResponseListener;
import com.letsgood.synergykit.listeners.UsersResponseListener;
import com.letsgood.synergykit.request.SynergyKITRequest;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITEmail;
import com.letsgood.synergykit.resources.SynergyKITNotification;
import com.letsgood.synergykit.resources.SynergyKITObject;
import com.letsgood.synergykit.resources.SynergyKITUser;

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
	
	/* Debug mode enabled getter */
	public static boolean isDebugModeEnabled(){
		return SynergyKITSdk.getInstance().isDebugModeEnabled();
	}
	
	/* Debug mode enabled setter */ 
	public static void setDebugModeEnabled(boolean debugModeEnabled){
		SynergyKITSdk.getInstance().setDebugModeEnabled(debugModeEnabled);
	}
	
	//-------------------------------------------------------------------------------------------------------------------	
	/* Install cache */
	public static void installCache(Context context) {
		SynergyKITSdk.getInstance().installCache(context);		
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
	public static void updateRecord(String collectionUrl,SynergyKITObject object, ResponseListener listener,	boolean parallelMode) {
		SynergyKITSdk.getInstance().updateRecord(collectionUrl, object, listener, parallelMode);
		
	}

	/* Delete record */
	public static void deleteRecord(String collectionUrl, String recordId,	DeleteResponseListener listener, boolean parallelMode) {
		SynergyKITSdk.getInstance().deleteRecord(collectionUrl, recordId, listener, parallelMode);		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	/* Get user */
	public static void getUser(SynergyKITConfig config, UserResponseListener listener) {
		SynergyKITSdk.getInstance().getUser(config, listener);		
	}
	
	/* Get user */
	public static void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode) {
		SynergyKITSdk.getInstance().getUser(userId, type, listener, parallelMode);		
	}

	/* Get users */
	public static void getUsers(SynergyKITConfig config, UsersResponseListener listener) {
		SynergyKITSdk.getInstance().getUsers(config, listener);		
	}

	/* Get users */
	public static void getUsers(Type type, UsersResponseListener listener,	boolean parallelMode) {
		SynergyKITSdk.getInstance().getUsers(type, listener, parallelMode);		
	}

	/* Create user */
	public static void createUser(SynergyKITUser user, UserResponseListener listener, boolean parallelMode) {
		SynergyKITSdk.getInstance().createUser(user, listener, parallelMode);		
	}
	
	/* Update user */
	public static void updateUser(SynergyKITUser user, UserResponseListener listener, boolean parallelMode) {
		SynergyKITSdk.getInstance().updateUser(user, listener, parallelMode);	
	}

	/* Delete user */
	public static void deleteUser(String userId, DeleteResponseListener listener,	boolean parallelMode) {
		SynergyKITSdk.getInstance().deleteUser(userId, listener, parallelMode);		
	}
	
	//-------------------------------------------------------------------------------------------------------------------	
	/* Send email */
	public static void sendEmail(SynergyKITEmail email, EmailResponseListener listener, boolean parallelMode){
		SynergyKITSdk.getInstance().sendEmail(email, listener, parallelMode);
	}
	
	/* Send notification */
	public static void sendNotification(SynergyKITNotification notification, NotificationResponseListener listener, boolean parralelMode){
		SynergyKITSdk.getInstance().sendNotification(notification, listener, parralelMode);
	}
	
	//-------------------------------------------------------------------------------------------------------------------	
	/* Register user */
	public static void registerUser(SynergyKITUser user, UserResponseListener listener){
		SynergyKITSdk.getInstance().registerUser(user, listener);
	}
	
	/* Login user */
	public static void loginUser(SynergyKITUser user, UserResponseListener listener){
		SynergyKITSdk.getInstance().loginUser(user, listener);
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	/* Upload file */
	public static void uploadFile(byte[] data, FileDataResponseListener listener) {
		SynergyKITSdk.getInstance().uploadFile(data, listener);
		
	}
	
	/* Upload bitmap */
	public static void uploadBitmap(Bitmap bitmap, FileDataResponseListener listener) {
		SynergyKITSdk.getInstance().uploadBitmap(bitmap, listener);
		
	}

	/* Download bitmap */
	public static void downloadBitmap(String uri, BitmapResponseListener listener) {
		SynergyKITSdk.getInstance().downloadBitmap(uri, listener);
		
	}

	/* Download file */
	public static void downloadFile(String uri, BytesResponseListener listener) {
		SynergyKITSdk.getInstance().downloadFile(uri, listener);
		
	}
}
