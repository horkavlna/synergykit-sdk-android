package com.synergykit.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.cache.Cache;
import com.synergykit.sdk.interfaces.IAuthorization;
import com.synergykit.sdk.interfaces.IBatches;
import com.synergykit.sdk.interfaces.ICache;
import com.synergykit.sdk.interfaces.ICloudCode;
import com.synergykit.sdk.interfaces.IFiles;
import com.synergykit.sdk.interfaces.INotification;
import com.synergykit.sdk.interfaces.IRecords;
import com.synergykit.sdk.interfaces.ISynergyKITSdk;
import com.synergykit.sdk.interfaces.IUsers;
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
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.request.SynergyKITRequest;
import com.synergykit.sdk.resources.SynergyKITAuthConfig;
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

public class SynergyKITSdk implements ISynergyKITSdk, IRecords, IUsers, INotification, ICache, IAuthorization, IFiles, ICloudCode, IBatches{
	
	/* Attributes */
	private static SynergyKITSdk instance = null;
	private SynergyKITAuthConfig authConfig = new SynergyKITAuthConfig();
	private SynergyKITConfig config = new SynergyKITConfig();
	private IRecords records = new Records();
	private IUsers users = new Users();
	private INotification notifications = new Notifications();
	private IAuthorization authorization = new Authorization();
	private ICache cache = new Cache();
	private IFiles files = new Files();
    private ICloudCode cloudCode = new CloudCode();
    private IBatches batches = new Batches();
	
	//---------------------------------------------------------------------------------------
	/* Instance static getter */
	public static SynergyKITSdk getInstance(){
		
		if(instance == null )
			instance = new SynergyKITSdk();
		
		return instance;
	}
	
	//---------------------------------------------------------------------------------------
	/* Init */
	@Override
	public void init(String tenant, String applicationKey) {
		this.setTenant(tenant);
		this.setApplicationKey(applicationKey);
	}

	/* Reset */
	@Override
	public void reset() {
		authConfig = new SynergyKITAuthConfig();	
	}

	
	/* Tenant setter */
	@Override
	public void setTenant(String tenant) {
		authConfig.setTenant(tenant);
	}

	/* Tenant getter */
	@Override
	public String getTenant() {
		return authConfig.getTenant();
	}

	/* Application key setter */
	@Override
	public void setApplicationKey(String applicationKey) {		
		authConfig.setApplicationKey(applicationKey);
		
	}

	/* Application key getter */
	@Override
	public String getApplicationKey() {
		return authConfig.getApplicationKey();
	}

	/* Is init */
	@Override
	public boolean isInit() {
		
		if(authConfig.getApplicationKey() == null 
		   || authConfig.getApplicationKey().isEmpty() == true
		   || authConfig.getTenant()==null
		   || authConfig.getTenant().isEmpty() ==true)
		   
		   return false;
		   
		   
		return true;
	}
	
	/*Config setter */ 
	@Override
	public void setConfig(SynergyKITConfig config) {		
		this.config = config;
	}

	/* Config getter */
	@Override
	public SynergyKITConfig getConfig() {		
		
		if(config==null){
			//Log
			SynergyKITLog.print(Errors.MSG_NO_CONFIG);
		}
		
		return config;
	}
	
	/* Synergylize */
	@Override
	public void synergylize(SynergyKITRequest request, boolean parallelMode) {
		
		if(request==null){

			//Log
			SynergyKITLog.print(Errors.MSG_NO_REQUEST);
			
			return;
		}
		
		//execute
		if(parallelMode==false){
			request.execute();
		}else{
			request.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
		
	}
	
	/* Debug mode enabled getter */
	@Override
	public boolean isDebugModeEnabled(){ 
		return SynergyKITLog.isEnabled();
	}
	
	/* Debug mode enabled setter */ 
	@Override
	public void setDebugModeEnabled(boolean debugModeEnabled){
		SynergyKITLog.setEnabled(debugModeEnabled);
	}
	
	//---------------------------------------------------------------------------------------
	/* Get record */
    @Override
    public void invokeCloudCode(SynergyKITConfig config,SynergyKITObject object,  ResponseListener listener) {
        cloudCode.invokeCloudCode(config, object, listener);
    }

	/* Get record */
	@Override
	public void getRecord(SynergyKITConfig config, ResponseListener listener) {
		records.getRecord(config, listener);		
	}

	/* Get record */
	@Override
	public void getRecord(String collectionUrl, String recordId, Type type,	ResponseListener listener, boolean parallelMode) {
		records.getRecord(collectionUrl, recordId, type, listener, parallelMode);		
	}
	
	/* Get records */
	@Override
	public void getRecords(SynergyKITConfig config,	RecordsResponseListener listener) {
		records.getRecords(config, listener);		
	}

	/* Get records */
	@Override
	public void getRecords(String collectionUrl, Type type,	RecordsResponseListener listener, boolean parallelMode) {
		records.getRecords(collectionUrl, type, listener, parallelMode);
	}

	/* Create record */
	@Override
	public void createRecord(String collectionUrl, SynergyKITObject object,	ResponseListener listener, boolean parallelMode) {
		records.createRecord(collectionUrl, object, listener, parallelMode);	
	}

	/* Update record */
	@Override
	public void updateRecord(String collectionUrl,SynergyKITObject object, ResponseListener listener,	boolean parallelMode) {
		records.updateRecord(collectionUrl,  object, listener, parallelMode);
	}

	/* Delete record */
	@Override
	public void deleteRecord(String collectionUrl, String recordId,	DeleteResponseListener listener, boolean parallelMode) {
		records.deleteRecord(collectionUrl, recordId, listener, parallelMode);
	}
		
	//-------------------------------------------------------------------------------------------------------------------
	/* Get user */
	@Override
	public void getUser(SynergyKITConfig config, UserResponseListener listener) {
		users.getUser(config, listener);		
	}
	
	/* Get user */
	@Override
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode) {
		users.getUser(userId, type, listener, parallelMode);		
	}

	/* Get users */
	@Override
	public void getUsers(SynergyKITConfig config, UsersResponseListener listener) {
		users.getUsers(config, listener);		
	}

	/* Get users */
	@Override
	public void getUsers(Type type, UsersResponseListener listener,	boolean parallelMode) {
		users.getUsers(type, listener, parallelMode);		
	}

	/* Create user */
	@Override
	public void createUser(SynergyKITUser user, UserResponseListener listener, boolean parallelMode) {
		users.createUser(user, listener, parallelMode);		
	}
	
	/* Update user */
	@Override
	public void updateUser(SynergyKITUser user, UserResponseListener listener, boolean parallelMode) {
		users.updateUser(user, listener, parallelMode);
	}

	/* Delete user */
	@Override
	public void deleteUser(SynergyKITUser user, DeleteResponseListener listener,	boolean parallelMode) {
		users.deleteUser(user, listener, parallelMode);
	}

    /* Add platform (GCM) */
    public void addPlatformToUser(SynergyKITUser user, SynergyKITPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        users.addPlatformToUser(user, platform, listener, parallelMode);
    }

    /* Update platform by _id */
    public void updatePlatformInUser(SynergyKITUser user, SynergyKITPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        users.updatePlatformInUser(user, platform, listener, parallelMode);
    }

    /* Delete platform by _id */
    public void deletePlatform(SynergyKITUser user, SynergyKITPlatform platform, DeleteResponseListener listener, boolean parallelMode) {
        users.deletePlatform(user, platform, listener, parallelMode);
    }

    @Override
    public void getPlatform(SynergyKITUser user, String platformId, PlatformResponseListener listener, boolean parallelMode) {
        users.getPlatform(user, platformId, listener, parallelMode);
    }

    @Override
    public void getPlatforms(SynergyKITUser user, PlatformsResponseListener listener, boolean parallelMode) {
        users.getPlatforms(user, listener, parallelMode);
    }

    //-------------------------------------------------------------------------------------------------------------------
	/* Send email */
	@Override
	public void sendEmail(String mailId,SynergyKITEmail email, EmailResponseListener listener, boolean parallelMode) {
		notifications.sendEmail(mailId, email, listener, parallelMode);
	}
	
	/* Send notification */
	@Override
	public void sendNotification(SynergyKITNotification notification, NotificationResponseListener listener, boolean parralelMode) {
		notifications.sendNotification(notification, listener, parralelMode);		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	/* Install cache */
	@Override
	public void installCache(Context context) {
		cache.installCache(context);		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	/* Register user */
	@Override
	public void registerUser(SynergyKITUser user, UserResponseListener listener) {
		authorization.registerUser(user, listener);		
	}

	/* Login user */
	@Override
	public void loginUser(SynergyKITUser user, UserResponseListener listener) {
		authorization.loginUser(user, listener);		
	}

	//-------------------------------------------------------------------------------------------------------------------
	/* Upload file */
	@Override
	public void uploadFile(byte[] data, FileDataResponseListener listener) {
		files.uploadFile(data, listener);
		
	}
	
	/* Upload bitmap */
	@Override
	public void uploadBitmap(Bitmap bitmap, FileDataResponseListener listener) {
		files.uploadBitmap(bitmap, listener);
		
	}

	/* Download bitmap */
	@Override
	public void downloadBitmap(String bitmapId, BitmapResponseListener listener) {
		files.downloadBitmap(bitmapId, listener);
		
	}

	/* Download file */
	@Override
	public void downloadFile(String fileId, BytesResponseListener listener) {
		files.downloadFile(fileId, listener);
		
	}

    //-------------------------------------------------------------------------------------------------------------------
    /* Init batch */
    @Override
    public void initBatch(String batchId) {
        batches.initBatch(batchId);
    }

    /* Remove batch */
    @Override
    public void removeBatch(String batchId) {
        batches.removeBatch(batchId);
    }

    /* Remove all batches */
    @Override
    public void removeAllBatches() {
       batches.removeAllBatches();
    }

    /* Send batch */
    @Override
    public void sendBatch(String batchId, BatchResponseListener listener, boolean parallelMode) {
        batches.sendBatch(batchId, listener, parallelMode);
    }

    /*Batch getter*/
    @Override
    public LinkedList<SynergyKITBatchItem> getBatch(String batchId) {
        return batches.getBatch(batchId);
    }
}
