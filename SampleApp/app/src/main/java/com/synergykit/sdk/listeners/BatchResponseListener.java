package com.synergykit.sdk.listeners;

import com.synergykit.sdk.resources.SynergyKitBatchResponse;
import com.synergykit.sdk.resources.SynergyKitError;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public interface BatchResponseListener {
    public void doneCallback(int statusCode, SynergyKitBatchResponse[] batchResponse);
    public void errorCallback(int statusCode,SynergyKitError errorObject);

}
