package com.letsgood.synergykit;

import java.lang.reflect.Type;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.cache.Cache;
import com.letsgood.synergykit.interfaces.IAuthorization;
import com.letsgood.synergykit.interfaces.ICache;
import com.letsgood.synergykit.interfaces.INotification;
import com.letsgood.synergykit.interfaces.IRecords;
import com.letsgood.synergykit.interfaces.ISynergyKITSdk;
import com.letsgood.synergykit.interfaces.IUsers;
import com.letsgood.synergykit.listeners.DeleteResponseListener;
import com.letsgood.synergykit.listeners.EmailResponseListener;
import com.letsgood.synergykit.listeners.RecordsResponseListener;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.listeners.UserResponseListener;
import com.letsgood.synergykit.listeners.UsersResponseListener;
import com.letsgood.synergykit.request.SynergyKITRequest;
import com.letsgood.synergykit.resources.SynergyKITAuthConfig;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITEmail;
import com.letsgood.synergykit.resources.SynergyKITObject;
import com.letsgood.synergykit.resources.SynergyKITUser;

public class SynergyKITSdk implements ISynergyKITSdk, IRecords, IUsers, INotification, ICache, IAuthorization{

	/* Constants */
	public static final String TAG = "SynergyKIT";
	
	/* Attributes */
	private static SynergyKITSdk instance = null;
	private boolean debugModeEnabled = false;
	private SynergyKITAuthConfig authConfig = new SynergyKITAuthConfig();
	private SynergyKITConfig config = new SynergyKITConfig();
	private IRecords records = new Records();
	private IUsers users = new Users();
	private INotification notifications = new Notifications();
	private IAuthorization authorization = new Authorization();
	private ICache cache = new Cache();
	
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
			if(SynergyKIT.isDebugModeEnabled()){
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NO_CONFIG);
			}
		}
		
		return config;
	}
	
	/* Synergylize */
	@Override
	public void synergylize(SynergyKITRequest request, boolean parallelMode) {
		
		if(request==null){

			//Log
			if(SynergyKIT.isDebugModeEnabled())
				Log.e(SynergyKITSdk.TAG,Errors.MSG_NO_REQUEST);
			
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
		return debugModeEnabled;
	}
	
	/* Debug mode enabled setter */ 
	@Override
	public void setDebugModeEnabled(boolean debugModeEnabled){
		this.debugModeEnabled = debugModeEnabled;
	}
	
	//---------------------------------------------------------------------------------------
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
	public void deleteUser(String userId, DeleteResponseListener listener,	boolean parallelMode) {
		users.deleteUser(userId, listener, parallelMode);		
	}

	//-------------------------------------------------------------------------------------------------------------------
	/* Send email */
	@Override
	public void sendEmail(SynergyKITEmail email, EmailResponseListener listener, boolean parallelMode) {
		notifications.sendEmail(email, listener, parallelMode);		
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

}
