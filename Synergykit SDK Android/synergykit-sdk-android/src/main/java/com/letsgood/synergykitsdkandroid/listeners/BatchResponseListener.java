package com.letsgood.synergykitsdkandroid.listeners;


import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchResponse;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public interface BatchResponseListener {
    public void doneCallback(int statusCode, SynergykitBatchResponse[] batchResponse);
    public void errorCallback(int statusCode, SynergykitError errorObject);

}
