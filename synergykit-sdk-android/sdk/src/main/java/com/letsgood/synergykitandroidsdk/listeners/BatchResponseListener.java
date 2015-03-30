package com.letsgood.synergykitandroidsdk.listeners;


import com.letsgood.synergykitandroidsdk.resources.SynergyKitBatchResponse;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public interface BatchResponseListener {
    public void doneCallback(int statusCode, SynergyKitBatchResponse[] batchResponse);
    public void errorCallback(int statusCode, SynergyKitError errorObject);

}
