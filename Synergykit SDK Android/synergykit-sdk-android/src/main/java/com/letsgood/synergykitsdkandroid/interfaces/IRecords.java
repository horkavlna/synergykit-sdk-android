package com.letsgood.synergykitsdkandroid.interfaces;




/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.RecordsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;

import java.lang.reflect.Type;

public interface IRecords {
	public void getRecord(SynergykitConfig config, ResponseListener listener);
	public void getRecord(String collectionUrl, String recordId, Type type, ResponseListener listener, boolean parallelMode);
	public void getRecords(SynergykitConfig config, RecordsResponseListener listener);
	public void getRecords(String collectionUrl, Type type, RecordsResponseListener listener, boolean parallelMode);
	public void createRecord(String collectionUrl, SynergykitObject object, ResponseListener listener, boolean parallelMode);
	public void updateRecord(String collectionUrl, SynergykitObject object, ResponseListener listener, boolean parallelMode);
    public void patchRecord(String collectionUrl, SynergykitObject object, ResponseListener listener, boolean parallelMode);
    public void deleteRecord(String collectionUrl, String recordId, DeleteResponseListener listener, boolean parallelMode);
    public void addAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode);
    public void removeAccess(String collectionUrl, String recordId, String userId, ResponseListener listener, boolean parallelMode);
}
