package com.letsgood.synergykitsdkandroid.listeners;


import com.letsgood.synergykitsdkandroid.resources.SynergyKitBatchResponse;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public interface BatchResponseListener {
    public void doneCallback(int statusCode, SynergyKitBatchResponse[] batchResponse);
    public void errorCallback(int statusCode, SynergyKitError errorObject);

}
