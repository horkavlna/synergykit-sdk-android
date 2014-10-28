package com.letsgood.synergykit.interfaces;

import java.lang.reflect.Type;

import com.letsgood.synergykit.listeners.DeleteListener;
import com.letsgood.synergykit.listeners.RecordsResponseListener;
import com.letsgood.synergykit.listeners.ResponseListener;
import com.letsgood.synergykit.resources.SynergyKITConfig;
import com.letsgood.synergykit.resources.SynergyKITObject;

public interface IRecords {
	public void getRecord(SynergyKITConfig config, ResponseListener listener);
	public void getRecord(String collectionUrl, String recordId, Type type, ResponseListener listener, boolean parallelMode );
	public void getRecords(SynergyKITConfig config, RecordsResponseListener listener);
	public void getRecords(String collectionUrl, Type type, RecordsResponseListener listener, boolean parallelMode);
	public void createRecord(String collectionUrl, SynergyKITObject object, ResponseListener listener, boolean parallelMode);
	public void updateRecord(String collectionUrl, String recordId, SynergyKITObject object, ResponseListener listener, boolean parallelMode);
	public void deleteRecord(String collectionUrl, String recordId,DeleteListener listener, boolean parallelMode);
}
