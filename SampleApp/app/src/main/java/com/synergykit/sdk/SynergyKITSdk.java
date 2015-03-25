package com.synergykit.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.cache.Cache;
import com.synergykit.sdk.interfaces.IAuthorization;
import com.synergykit.sdk.interfaces.IBatches;
import com.synergykit.sdk.interfaces.ICache;
import com.synergykit.sdk.interfaces.ICloudCodes;
import com.synergykit.sdk.interfaces.IFiles;
import com.synergykit.sdk.interfaces.INotifications;
import com.synergykit.sdk.interfaces.IRecords;
import com.synergykit.sdk.interfaces.ISocket;
import com.synergykit.sdk.interfaces.ISynergyKitSdk;
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
import com.synergykit.sdk.listeners.SocketEventListener;
import com.synergykit.sdk.listeners.SocketStateListener;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.listeners.UsersResponseListener;
import com.synergykit.sdk.log.SynergyKitLog;
import com.synergykit.sdk.request.SynergyKitRequest;
import com.synergykit.sdk.resources.SynergyKitAuthConfig;
import com.synergykit.sdk.resources.SynergyKitBatchItem;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitEmail;
import com.synergykit.sdk.resources.SynergyKitNotification;
import com.synergykit.sdk.resources.SynergyKitObject;
import com.synergykit.sdk.resources.SynergyKitPlatform;
import com.synergykit.sdk.resources.SynergyKitSocketFilter;
import com.synergykit.sdk.resources.SynergyKitUser;

import java.lang.reflect.Type;
import java.util.LinkedList;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKitSdk implements ISynergyKitSdk, IRecords, IUsers, INotifications, ICache, IAuthorization, IFiles, ICloudCodes, IBatches,ISocket{
	
	/* Attributes */
	private static SynergyKitSdk instance = null;
	private SynergyKitAuthConfig authConfig = new SynergyKitAuthConfig();
	private SynergyKitConfig config = new SynergyKitConfig();
	private IRecords records = new Records();
	private IUsers users = new Users();
	private INotifications notifications = new Notifications();
	private IAuthorization authorization = new Authorization();
	private ICache cache = new Cache();
	private IFiles files = new Files();
    private ICloudCodes cloudCode = new CloudCodes();
    private IBatches batches = new Batches();
	private ISocket socket = new Socket();

	//---------------------------------------------------------------------------------------
	/* Instance static getter */
	public static SynergyKitSdk getInstance(){
		
		if(instance == null )
			instance = new SynergyKitSdk();
		
		return instance;
	}
	
	//---------------------------------------------------------------------------------------
	/* Init */
	@Override
	public void init(String tenant, String applicationKey) {
		this.setTenant(tenant);
		this.setApplicationKey(applicationKey);
        this.initSocket();
	}

	/* Reset */
	@Override
	public void reset() {
		authConfig = new SynergyKitAuthConfig();
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

    /* Token getter */
    @Override
    public String getToken() {
        return authConfig.getToken();
    }

    /* Token setter */
    @Override
    public void setToken(String token) {
        authConfig.setToken(token);
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
	public void setConfig(SynergyKitConfig config) {
		this.config = config;
	}

	/* Config getter */
	@Override
	public SynergyKitConfig getConfig() {
		
		if(config==null){
			//Log
			SynergyKitLog.print(Errors.MSG_NO_CONFIG);
		}
		
		return config;
	}
	
	/* Synergylize */
	@Override
	public void synergylize(SynergyKitRequest request, boolean parallelMode) {
		
		if(request==null){

			//Log
			SynergyKitLog.print(Errors.MSG_NO_REQUEST);
			
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
		return SynergyKitLog.isEnabled();
	}
	
	/* Debug mode enabled setter */ 
	@Override
	public void setDebugModeEnabled(boolean debugModeEnabled){
		SynergyKitLog.setEnabled(debugModeEnabled);
	}
	
	//---------------------------------------------------------------------------------------
	/* Get record */
    @Override
    public void invokeCloudCode(SynergyKitConfig config,SynergyKitObject object,  ResponseListener listener) {
        cloudCode.invokeCloudCode(config, object, listener);
    }

	/* Get record */
	@Override
	public void getRecord(SynergyKitConfig config, ResponseListener listener) {
		records.getRecord(config, listener);		
	}

	/* Get record */
	@Override
	public void getRecord(String collectionUrl, String recordId, Type type,	ResponseListener listener, boolean parallelMode) {
		records.getRecord(collectionUrl, recordId, type, listener, parallelMode);		
	}
	
	/* Get records */
	@Override
	public void getRecords(SynergyKitConfig config,	RecordsResponseListener listener) {
		records.getRecords(config, listener);		
	}

	/* Get records */
	@Override
	public void getRecords(String collectionUrl, Type type,	RecordsResponseListener listener, boolean parallelMode) {
		records.getRecords(collectionUrl, type, listener, parallelMode);
	}

	/* Create record */
	@Override
	public void createRecord(String collectionUrl, SynergyKitObject object,	ResponseListener listener, boolean parallelMode) {
		records.createRecord(collectionUrl, object, listener, parallelMode);	
	}

	/* Update record */
	@Override
	public void updateRecord(String collectionUrl,SynergyKitObject object, ResponseListener listener,	boolean parallelMode) {
		records.updateRecord(collectionUrl,  object, listener, parallelMode);
	}

    @Override
    public void patchRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener, boolean parallelMode) {
        records.patchRecord(collectionUrl,  object, listener, parallelMode);
    }

    /* Delete record */
	@Override
	public void deleteRecord(String collectionUrl, String recordId,	DeleteResponseListener listener, boolean parallelMode) {
		records.deleteRecord(collectionUrl, recordId, listener, parallelMode);
	}
		
	//-------------------------------------------------------------------------------------------------------------------
	/* Get user */
	@Override
	public void getUser(SynergyKitConfig config, UserResponseListener listener) {
		users.getUser(config, listener);		
	}
	
	/* Get user */
	@Override
	public void getUser(String userId, Type type, UserResponseListener listener, boolean parallelMode) {
		users.getUser(userId, type, listener, parallelMode);		
	}

	/* Get users */
	@Override
	public void getUsers(SynergyKitConfig config, UsersResponseListener listener) {
		users.getUsers(config, listener);		
	}

	/* Get users */
	@Override
	public void getUsers(Type type, UsersResponseListener listener,	boolean parallelMode) {
		users.getUsers(type, listener, parallelMode);		
	}

	/* Create user */
	@Override
	public void createUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode) {
		users.createUser(user, listener, parallelMode);		
	}
	
	/* Update user */
	@Override
	public void updateUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode) {
		users.updateUser(user, listener, parallelMode);
	}

    @Override
    public void patchUser(SynergyKitUser user, UserResponseListener listener, boolean parallelMode) {
        users.patchUser(user, listener, parallelMode);
    }

    /* Delete user */
	@Override
	public void deleteUser(SynergyKitUser user, DeleteResponseListener listener,	boolean parallelMode) {
		users.deleteUser(user, listener, parallelMode);
	}

    /* Add platform (GCM) */
    public void addPlatformToUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        users.addPlatformToUser(user, platform, listener, parallelMode);
    }

    /* Update platform by _id */
    public void updatePlatformInUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        users.updatePlatformInUser(user, platform, listener, parallelMode);
    }

    @Override
    public void patchPlatformInUser(SynergyKitUser user, SynergyKitPlatform platform, PlatformResponseListener listener, boolean parallelMode) {
        users.patchPlatformInUser(user, platform, listener, parallelMode);
    }

    /* Delete platform by _id */
    public void deletePlatform(SynergyKitUser user, SynergyKitPlatform platform, DeleteResponseListener listener, boolean parallelMode) {
        users.deletePlatform(user, platform, listener, parallelMode);
    }

    /* Platform getter */
    @Override
    public void getPlatform(SynergyKitUser user, String platformId, PlatformResponseListener listener, boolean parallelMode) {
        users.getPlatform(user, platformId, listener, parallelMode);
    }

    @Override
    public void getPlatforms(SynergyKitUser user, PlatformsResponseListener listener, boolean parallelMode) {
        users.getPlatforms(user, listener, parallelMode);
    }



    //-------------------------------------------------------------------------------------------------------------------
	/* Send email */
	@Override
	public void sendEmail(String mailId,SynergyKitEmail email, EmailResponseListener listener, boolean parallelMode) {
		notifications.sendEmail(mailId, email, listener, parallelMode);
	}
	
	/* Send notification */
	@Override
	public void sendNotification(SynergyKitNotification notification, NotificationResponseListener listener, boolean parralelMode) {
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
	public void registerUser(SynergyKitUser user, UserResponseListener listener) {
		authorization.registerUser(user, listener);		
	}

	/* Login user */
	@Override
	public void loginUser(SynergyKitUser user, UserResponseListener listener) {
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
    public LinkedList<SynergyKitBatchItem> getBatch(String batchId) {
        return batches.getBatch(batchId);
    }

    //-------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean initSocket() {
        return socket.initSocket();
    }

    @Override
    public boolean isSocketInitialized() {
        return socket.isSocketInitialized();
    }

    @Override
    public boolean isSocketConnected() {
        return socket.isSocketConnected();
    }

    @Override
    public void connectSocket() {
        socket.connectSocket();
    }

    @Override
    public void connectSocket(@Nullable SocketStateListener listener) {
        socket.connectSocket(listener);
    }

    @Override
    public void disconnectSocket() {
        socket.disconnectSocket();
    }

    @Override
    public void onSocket(String eventName, SocketEventListener listener) {
        socket.onSocket(eventName,listener);
    }

    @Override
    public void onSocket(String eventName, @Nullable String collectionName, SocketEventListener listener) {
        socket.onSocket(eventName,collectionName,listener);
    }

    @Override
    public void onSocket(String eventName, @Nullable String collectionName, @Nullable SynergyKitSocketFilter filter, SocketEventListener listener) {
        socket.onSocket(eventName,collectionName,filter,listener);
    }

    @Override
    public void offSocket(String eventName, SocketEventListener listener) {
        socket.offSocket(eventName,listener);
    }

    @Override
    public void offSocket(String eventName, @Nullable String collectionName, SocketEventListener listener) {
        socket.offSocket(eventName,collectionName,listener);
    }

    @Override
    public void offSocket(String eventName, @Nullable String collectionName, @Nullable SynergyKitSocketFilter filter, SocketEventListener listener) {
        socket.offSocket(eventName,collectionName,filter,listener);
    }

    @Override
    public void emitViaSocket(String eventName, Object object) {
        socket.emitViaSocket(eventName,object);
    }


}
