package com.synergykit.android.provider;

import java.lang.reflect.Type;
import com.synergykit.android.exception.NotInitializedException;
import com.synergykit.android.gsonwrapper.GsonWrapper;
import com.synergykit.android.request.Request;
import com.synergykit.android.requesturl.RequestUrl;
import com.synergykit.android.response.BaseResponseListener;
import com.synergykit.android.response.DeleteResponseListener;
import com.synergykit.android.response.GetAllResponseListener;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class Provider{

	/* Attributes */
	private static Provider mInstance = null;
	private Config mConfig=null;
	
	
	/* Instance getter */
	public static Provider getInstance(){
		if(mInstance==null){
			mInstance=new Provider();
		}
		
		return mInstance;
	}
	
	/* Initialization */
	public void init(String tenant, String appKey){		
		mConfig = new Config(tenant, appKey);
	}
	
	/* Reset */
	public void reset(){
		mConfig=null;
	}
	
	/* Tenant setter */
	public void setTenant(String tenant) {
		mConfig.setTenant(tenant);
	}

	/* Tenant getter */
	public String getTenant() {
		return mConfig.getTenant();
	}

	/* Application key setter */
	public void setAppKey(String appKey) {
		mConfig.setApplicationKey(appKey);
		
	}

	/* Application key getter */
	public String getApplicationKey() {
		return mConfig.getApplicationKey();
	}
	
	/* All record getter */
	public void getAllRecords(String collectionUrl, GetAllResponseListener listener, Type type) throws NotInitializedException {
		
		//initialization check
		this.initCheck();
		
		//url initialization
		String url = RequestUrl.getAllRecordsUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl);
		
		//request GET ALL
		Request.getAll(url, listener, type);
	}

	/* Get record */
	public void getRecord(String collectionUrl, String recordId, BaseResponseListener listener, Type type) throws NotInitializedException {
		
		//initialization check
		this.initCheck();
				
		//url initialization
		String url = RequestUrl.getRecordUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl, recordId);
		
		//request GET
		Request.get(url, listener, type);
	}
	
	/* Create record */
	public void createRecord(String collectionUrl, Object object, BaseResponseListener listener, Type type) throws NotInitializedException {
		
		//initialization check
		this.initCheck();
		
		//json & url initialization
		String json = GsonWrapper.getInstance().getGson().toJson(object);
		String url = RequestUrl.postRecordUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl);

		//request POST
		Request.post(url, json,listener,type);
	}

	/* Update record */
	public void updateRecord(String collectionUrl, String recordId, Object object, BaseResponseListener listener, Type type) throws NotInitializedException {
		
		//initialization check
		this.initCheck();
				
		//json & url initialization
		String json = GsonWrapper.getInstance().getGson().toJson(object);
		String url = RequestUrl.putRecordUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl,recordId);
		
		//request PUT
		Request.put(url, json, listener, type);
	}

	public boolean deleteRecord(String collectionUrl, String recordId, DeleteResponseListener listener) throws NotInitializedException {
		
		//initialization check
		this.initCheck();
		
		//url initialization
		String url = RequestUrl.deleteRecordUrl(mConfig.getTenant(), mConfig.getApplicationKey(), collectionUrl, recordId);
		
		//request DELETE
		Request.delete(url,listener);
		
		return false;
	}


	/* initialization check */
	public void initCheck() throws NotInitializedException{
		if(mConfig==null)
			throw new NotInitializedException();
	}
}
