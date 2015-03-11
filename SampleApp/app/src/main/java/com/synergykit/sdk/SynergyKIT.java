package com.synergykit.sdk;

import android.content.Context;
import android.graphics.Bitmap;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.synergykit.sdk.listeners.BatchResponseListener;
import com.synergykit.sdk.listeners.BitmapResponseListener;
import com.synergykit.sdk.listeners.BytesResponseListener;
import com.synergykit.sdk.listeners.DeleteResponseListener;
import com.synergykit.sdk.listeners.EmailResponseListener;
import com.synergykit.sdk.listeners.FileDataResponseListener;
import com.synergykit.sdk.listeners.NotificationResponseListener;
import com.synergykit.sdk.listeners.PlatformResponseListener;
import com.synergykit.sdk.listeners.PlatformsResponseListener;
import com.synergykit.sdk.listeners.RecordsResponseListener;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.listeners.UsersResponseListener;
import com.synergykit.sdk.request.SynergyKITRequest;
import com.synergykit.sdk.resources.SynergyKITBatchItem;
import com.synergykit.sdk.resources.SynergyKITConfig;
import com.synergykit.sdk.resources.SynergyKITEmail;
import com.synergykit.sdk.resources.SynergyKITNotification;
import com.synergykit.sdk.resources.SynergyKITObject;
import com.synergykit.sdk.resources.SynergyKITPlatform;
import com.synergykit.sdk.resources.SynergyKITUser;

import java.lang.reflect.Type;
import java.util.LinkedList;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

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

    /* Invoke cloud code*/
    public static void invokeCloudCode(SynergyKITConfig config,SynergyKITObject object,  ResponseListener listener) {
        SynergyKITSdk.getInstance().invokeCloudCode(config, object,  listener);
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
	public static void deleteUser(SynergyKITUser user, DeleteResponseListener listener,	boolean parallelMode) {
		SynergyKITSdk.getInstance().deleteUser(user, listener, parallelMode);
	}

    /* Add platform (GCM) */
    public static void addPlatformToUser(SynergyKITUser user, SynergyKITPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKITSdk.getInstance().addPlatformToUser(user, platform, listener, parallelMode);
    }

    /* Update platform by _id */
    public static void updatePlatformInUser(SynergyKITUser user, SynergyKITPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKITSdk.getInstance().updatePlatformInUser(user, platform, listener, parallelMode);
    }

    /* Delete platform by _id */
    public static void deletePlatform(SynergyKITUser user, SynergyKITPlatform platform, DeleteResponseListener listener,	boolean parallelMode) {
        SynergyKITSdk.getInstance().deletePlatform(user, platform, listener, parallelMode);
    }

    /* Get platform by user and platformId */
    public void getPlatform(SynergyKITUser user, String platformId, PlatformResponseListener listener, boolean parallelMode) {
        SynergyKITSdk.getInstance().getPlatform(user, platformId, listener, parallelMode);
    }

    /* Get platforms[] by user */
    public void getPlatforms(SynergyKITUser user, PlatformsResponseListener listener, boolean parallelMode) {
        SynergyKITSdk.getInstance().getPlatforms(user, listener, parallelMode);
    }
	
	//-------------------------------------------------------------------------------------------------------------------	
	/* Send email */
	public static void sendEmail(String mailId, SynergyKITEmail email, EmailResponseListener listener, boolean parallelMode){
		SynergyKITSdk.getInstance().sendEmail(mailId, email, listener, parallelMode);
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
    //-------------------------------------------------------------------------------------------------------------------
    /* Init batch */
    public static void initBatch(String batchId) {
        SynergyKITSdk.getInstance().initBatch(batchId);
    }

    /* Remove batch */
    public static void removeBatch(String batchId) {
        SynergyKITSdk.getInstance().removeBatch(batchId);
    }

    /* Remove all batches */

    public static void removeAllBatches() {
        SynergyKITSdk.getInstance().removeAllBatches();
    }

    /* Send batch */
    public static void sendBatch(String batchId, BatchResponseListener listener, boolean parallelMode) {
        SynergyKITSdk.getInstance().sendBatch(batchId, listener, parallelMode);
    }

    /*Batch getter*/
    public static LinkedList<SynergyKITBatchItem> getBatch(String batchId) {
        return SynergyKITSdk.getInstance().getBatch(batchId);
    }

    //-------------------------------------------------------------------------------------------------------------------
    /* Init socket */
    public static boolean initSocket() {
        return SynergyKITSdk.getInstance().initSocket();
    }

    /* Is socket inited*/
    public static boolean isSocketInited() {
        return SynergyKITSdk.getInstance().isSocketInited();
    }

    /* Is socket connected*/
    public static boolean isSocketConnected() { return SynergyKITSdk.getInstance().isSocketConnected();  }

    /* Connect socket */
    public static void connectSocket(String message, String collection) {
        SynergyKITSdk.getInstance().connectSocket(message, collection);
    }

    /* Emit via socket */
    public static void emitViaSocket(String event, Object... args) {
        SynergyKITSdk.getInstance().emitViaSocket(event,args);
    }

    /* Emit via socket */
    public static void emitViaSocket(String event, Object[] args, Ack ack) {
        SynergyKITSdk.getInstance().emitViaSocket(event,args,ack);
    }

    /* On socket */
    public static void onSocket(String event, Emitter.Listener listener) {
        SynergyKITSdk.getInstance().onSocket(event,listener);
    }

    /* Off socket */
    public static void offSocket(String event, Emitter.Listener listener) {
        SynergyKITSdk.getInstance().offSocket(event,listener);
    }

    /* Disconnect socket */
    public static void disconnectSocket(String message, String collection) {
        SynergyKITSdk.getInstance().disconnectSocket(message,collection);
    }


}
