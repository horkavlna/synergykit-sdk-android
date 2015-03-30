package com.letsgood.synergykitandroidsdk.interfaces;


import com.letsgood.synergykitandroidsdk.listeners.DeleteResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.RecordsResponseListener;
import com.letsgood.synergykitandroidsdk.listeners.ResponseListener;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitConfig;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitObject;

import java.lang.reflect.Type;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IRecords {
	public void getRecord(SynergyKitConfig config, ResponseListener listener);
	public void getRecord(String collectionUrl, String recordId, Type type, ResponseListener listener, boolean parallelMode);
	public void getRecords(SynergyKitConfig config, RecordsResponseListener listener);
	public void getRecords(String collectionUrl, Type type, RecordsResponseListener listener, boolean parallelMode);
	public void createRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener, boolean parallelMode);
	public void updateRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener, boolean parallelMode);
    public void patchRecord(String collectionUrl, SynergyKitObject object, ResponseListener listener, boolean parallelMode);
    public void deleteRecord(String collectionUrl, String recordId, DeleteResponseListener listener, boolean parallelMode);
}
