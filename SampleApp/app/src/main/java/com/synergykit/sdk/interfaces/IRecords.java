package com.synergykit.sdk.interfaces;

import com.synergykit.sdk.listeners.DeleteResponseListener;
import com.synergykit.sdk.listeners.RecordsResponseListener;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitObject;

import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IRecords {
	public void getRecord(SynergyKitConfig config, ResponseListener listener);
	public void getRecord(String collectionUrl, String recordId, Type type, ResponseListener listener, boolean parallelMode );
	public void getRecords(SynergyKitConfig config, RecordsResponseListener listener);
	public void getRecords(String collectionUrl, Type type, RecordsResponseListener listener, boolean parallelMode);
	public void createRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener, boolean parallelMode);
	public void updateRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener, boolean parallelMode);
    public void patchRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener, boolean parallelMode);
    public void deleteRecord(String collectionUrl, String recordId,DeleteResponseListener listener, boolean parallelMode);
}
