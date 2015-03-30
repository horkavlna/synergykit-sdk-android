package com.letsgood.synergykitandroidsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.letsgood.synergykitandroidsdk.listeners.BatchResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.BitmapResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.BytesResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.DeleteResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.EmailResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.FileDataResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.NotificationResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.PlatformResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.PlatformsResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.RecordsResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.ResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.SocketEventListener;
import com.letsgood.synergykitandroidsdk.listeners.SocketStateListener;
import com.letsgood.synergykitandroidsdk.listeners.UserResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.UsersResponseListener;
import com.letsgood.synergykitandroidsdk.request.SynergyKitRequest;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitBatchItem;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitEmail;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitNotification;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitObject;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitPlatform;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitSocketFilter;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitUser;

import java.lang.reflect.Type;
import java.util.LinkedList;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKit {

	/* Synergylize */
	public static void synergylize(SynergyKitRequest request, boolean parallelMode){
		SynergyKitSdk.getInstance().synergylize(request, parallelMode);
	}
	
	//------------------------------------------------------------------------------
	/* Init */
	public static void init(String tenant, String applicationKey) {
		SynergyKitSdk.getInstance().init(tenant, applicationKey);
	}

	/* Reset */
	public static void reset() {
		SynergyKitSdk.getInstance().reset();
	}

	
	/* Tenant setter */
	public static void setTenant(String tenant) {
		SynergyKitSdk.getInstance().setTenant(tenant);
	}

	/* Tenant getter */
	public static String getTenant() {
		return SynergyKitSdk.getInstance().getTenant();
	}

	/* Application key setter */
	public static void setApplicationKey(String applicationKey) {
		SynergyKitSdk.getInstance().setApplicationKey(applicationKey);
	}

    /* Token getter */
    public static String getToken() {
        return SynergyKitSdk.getInstance().getToken();
    }

    /* Token setter */
    public static void setToken(String token) {
         SynergyKitSdk.getInstance().setToken(token);
    }

	/* Application key getter */
	public static String getApplicationKey() {
		return SynergyKitSdk.getInstance().getApplicationKey();
	}

	/* Is init */
	public static boolean isInit() {
		return SynergyKitSdk.getInstance().isInit();
	}
	
	/* Config setter */
	public static void setConfig(SynergyKitConfig config) {
		SynergyKitSdk.getInstance().setConfig(config);
	}

	/* Config getter */
	public static SynergyKitConfig getConfig() {
		return SynergyKitSdk.getInstance().getConfig();
	}
	
	/* Debug mode enabled getter */
	public static boolean isDebugModeEnabled(){
		return SynergyKitSdk.getInstance().isDebugModeEnabled();
	}
	
	/* Debug mode enabled setter */ 
	public static void setDebugModeEnabled(boolean debugModeEnabled){
		SynergyKitSdk.getInstance().setDebugModeEnabled(debugModeEnabled);
	}
	
	//-------------------------------------------------------------------------------------------------------------------	
	/* Install cache */
	public static void installCache(Context context) {
		SynergyKitSdk.getInstance().installCache(context);
	}
	
	//------------------------------------------------------------------------------
	/* Get record */
	public static void getRecord(SynergyKitConfig config, ResponseListener listener) {
		SynergyKitSdk.getInstance().getRecord(config, listener);
	}

    /* Invoke cloud code*/
    public static void invokeCloudCode(SynergyKitConfig config,SynergyKitObject object,  ResponseListener listener) {
        SynergyKitSdk.getInstance().invokeCloudCode(config, object,  listener);
    }


	/* Get record */
	public static void getRecord(String collectionUrl, String recordId, Type type,	ResponseListener listener, boolean parallelMode) {
		SynergyKitSdk.getInstance().getRecord(collectionUrl, recordId, type, listener, parallelMode);
		
	}
	
	/* Get records */
	public static void getRecords(SynergyKitConfig config, RecordsResponseListener listener) {
		SynergyKitSdk.getInstance().getRecords(config, listener);
	}

	/* Get records */
	public static void getRecords(String collectionUrl, Type type, RecordsResponseListener listener, boolean parallelMode) {
		SynergyKitSdk.getInstance().getRecords(collectionUrl, type, listener, parallelMode);
	}

    /* Create record */
	public static void createRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener, boolean parallelMode) {
		SynergyKitSdk.getInstance().createRecord(collectionUrl, object, listener, parallelMode);
		
	}

	/* Update record */
	public static void updateRecord(String collectionUrl,SynergyKitObject object, ResponseListener listener,	boolean parallelMode) {
		SynergyKitSdk.getInstance().updateRecord(collectionUrl, object, listener, parallelMode);
		
	}

    /* Patch record */
    public static void patchRecord(String collectionUrl,SynergyKitObject object, ResponseListener listener,	boolean parallelMode) {
        SynergyKitSdk.getInstance().patchRecord(collectionUrl, object, listener, parallelMode);

    }

	/* Delete record */
	public static void deleteRecord(String collectionUrl, String recordId,	DeleteResponseListener listener, boolean parallelMode) {
		SynergyKitSdk.getInstance().deleteRecord(collectionUrl, recordId, listener, parallelMode);
	}

	//-------------------------------------------------------------------------------------------------------------------
	/* Get user */
	public static void getUser(SynergyKitConfig config, UserResponseListener listener) {
		SynergyKitSdk.getInstance().getUser(config, listener);
	}
	
	/* Get user */
	public static void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode) {
		SynergyKitSdk.getInstance().getUser(userId, type, listener, parallelMode);
	}

	/* Get users */
	public static void getUsers(SynergyKitConfig config, UsersResponseListener listener) {
		SynergyKitSdk.getInstance().getUsers(config, listener);
	}

	/* Get users */
	public static void getUsers(Type type, UsersResponseListener listener,	boolean parallelMode) {
		SynergyKitSdk.getInstance().getUsers(type, listener, parallelMode);
	}

	/* Create user */
	public static void createUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode) {
		SynergyKitSdk.getInstance().createUser(user, listener, parallelMode);
	}
	
	/* Update user */
	public static void updateUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode) {
		SynergyKitSdk.getInstance().updateUser(user, listener, parallelMode);
	}

    /* Update user */
    public static void patchUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode) {
        SynergyKitSdk.getInstance().patchUser(user, listener, parallelMode);
    }

	/* Delete user */
	public static void deleteUser(SynergyKitUser user, DeleteResponseListener listener,	boolean parallelMode) {
		SynergyKitSdk.getInstance().deleteUser(user, listener, parallelMode);
	}

    /* Add platform (GCM) */
    public static void addPlatformToUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKitSdk.getInstance().addPlatformToUser(user, platform, listener, parallelMode);
    }

    /* Update platform by _id */
    public static void updatePlatformInUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKitSdk.getInstance().updatePlatformInUser(user, platform, listener, parallelMode);
    }

    /* Patch platform by _id */
    public static void patchPlatformInUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKitSdk.getInstance().patchPlatformInUser(user, platform, listener, parallelMode);
    }

    /* Delete platform by _id */
    public static void deletePlatform(SynergyKitUser user, SynergyKitPlatform platform, DeleteResponseListener listener,	boolean parallelMode) {
        SynergyKitSdk.getInstance().deletePlatform(user, platform, listener, parallelMode);
    }

    /* Get platform by user and platformId */
    public static void getPlatform(SynergyKitUser user, String platformId, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKitSdk.getInstance().getPlatform(user, platformId, listener, parallelMode);
    }

    /* Get platforms[] by user */
    public static void getPlatforms(SynergyKitUser user, PlatformsResponseListener listener, boolean parallelMode) {
        SynergyKitSdk.getInstance().getPlatforms(user, listener, parallelMode);
    }

	//-------------------------------------------------------------------------------------------------------------------
	/* Send email */
	public static void sendEmail(String mailId, SynergyKitEmail email, EmailResponseListener listener, boolean parallelMode){
		SynergyKitSdk.getInstance().sendEmail(mailId, email, listener, parallelMode);
	}
	
	/* Send notification */
	public static void sendNotification(SynergyKitNotification notification, NotificationResponseListener listener, boolean parralelMode){
		SynergyKitSdk.getInstance().sendNotification(notification, listener, parralelMode);
	}
	
	//-------------------------------------------------------------------------------------------------------------------	
	/* Register user */
	public static void registerUser(SynergyKitUser user, UserResponseListener listener){
		SynergyKitSdk.getInstance().registerUser(user, listener);
	}
	
	/* Login user */
	public static void loginUser(SynergyKitUser user, UserResponseListener listener){
		SynergyKitSdk.getInstance().loginUser(user, listener);
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	/* Upload file */
	public static void uploadFile(byte[] data, FileDataResponseListener listener) {
		SynergyKitSdk.getInstance().uploadFile(data, listener);
		
	}
	
	/* Upload bitmap */
	public static void uploadBitmap(Bitmap bitmap, FileDataResponseListener listener) {
		SynergyKitSdk.getInstance().uploadBitmap(bitmap, listener);
		
	}

	/* Download bitmap */
	public static void downloadBitmap(String uri, BitmapResponseListener listener) {
		SynergyKitSdk.getInstance().downloadBitmap(uri, listener);
		
	}

	/* Download file */
	public static void downloadFile(String uri, BytesResponseListener listener) {
		SynergyKitSdk.getInstance().downloadFile(uri, listener);
		
	}
    //-------------------------------------------------------------------------------------------------------------------
    /* Init batch */
    public static void initBatch(String batchId) {
        SynergyKitSdk.getInstance().initBatch(batchId);
    }

    /* Remove batch */
    public static void removeBatch(String batchId) {
        SynergyKitSdk.getInstance().removeBatch(batchId);
    }

    /* Remove all batches */

    public static void removeAllBatches() {
        SynergyKitSdk.getInstance().removeAllBatches();
    }

    /* Send batch */
    public static void sendBatch(String batchId, BatchResponseListener listener, boolean parallelMode) {
        SynergyKitSdk.getInstance().sendBatch(batchId, listener, parallelMode);
    }

    /*Batch getter*/
    public static LinkedList<SynergyKitBatchItem> getBatch(String batchId) {
        return SynergyKitSdk.getInstance().getBatch(batchId);
    }

    //-------------------------------------------------------------------------------------------------------------------
    public static boolean isSocketInitialized() {
        return SynergyKitSdk.getInstance().isSocketInitialized();
    }

    public static boolean isSocketConnected() {
        return SynergyKitSdk.getInstance().isSocketConnected();
    }

    public static void connectSocket() {
        SynergyKitSdk.getInstance().connectSocket();
    }

    public static void connectSocket(@Nullable SocketStateListener listener) {
        SynergyKitSdk.getInstance().connectSocket(listener);
    }

    public static void disconnectSocket() {
        SynergyKitSdk.getInstance().disconnectSocket();
    }

    public static void onSocket(String eventName, SocketEventListener listener) {
        SynergyKitSdk.getInstance().onSocket(eventName, listener);
    }

    public static void onSocket(String eventName, @Nullable String collectionName, SocketEventListener listener) {
        SynergyKitSdk.getInstance().onSocket(eventName, collectionName, listener);
    }

    public static void onSocket(String eventName, @Nullable String collectionName, @Nullable SynergyKitSocketFilter filter, SocketEventListener listener) {
        SynergyKitSdk.getInstance().onSocket(eventName, collectionName, filter, listener);
    }

    public static void offSocket(String eventName, SocketEventListener listener) {
        SynergyKitSdk.getInstance().offSocket(eventName, listener);
    }

    public static void offSocket(String eventName, @Nullable String collectionName, SocketEventListener listener) {
        SynergyKitSdk.getInstance().offSocket(eventName, collectionName, listener);
    }

    public static void offSocket(String eventName, @Nullable String collectionName, @Nullable SynergyKitSocketFilter filter, SocketEventListener listener) {
        SynergyKitSdk.getInstance().offSocket(eventName, collectionName, filter, listener);
    }

    public static void emitViaSocket(String eventName, Object object) {
        SynergyKitSdk.getInstance().emitViaSocket(eventName, object);
    }
}
