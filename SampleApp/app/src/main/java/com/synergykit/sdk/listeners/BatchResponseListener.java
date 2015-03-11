package com.synergykit.sdk.listeners;

import com.synergykit.sdk.resources.SynergyKITBatchResponse;
import com.synergykit.sdk.resources.SynergyKITError;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public interface BatchResponseListener {
    public void doneCallback(int statusCode, SynergyKITBatchResponse[] batchResponse);
    public void errorCallback(int statusCode,SynergyKITError errorObject);

}
