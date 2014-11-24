package com.letsgood.synergykit.interfaces;

import java.lang.reflect.Type;

import com.letsgood.synergykit.listeners.DeleteResponseListener;
import com.letsgood.synergykit.listeners.RecordsResponseListener;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITObject;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public interface IRecords {
	public void getRecord(SynergyKITConfig config, ResponseListener listener);
	public void getRecord(String collectionUrl, String recordId, Type type, ResponseListener listener, boolean parallelMode );
	public void getRecords(SynergyKITConfig config, RecordsResponseListener listener);
	public void getRecords(String collectionUrl, Type type, RecordsResponseListener listener, boolean parallelMode);
	public void createRecord(String collectionUrl, SynergyKITObject object, ResponseListener listener, boolean parallelMode);
	public void updateRecord(String collectionUrl, SynergyKITObject object, ResponseListener listener, boolean parallelMode);
	public void deleteRecord(String collectionUrl, String recordId,DeleteResponseListener listener, boolean parallelMode);
}
