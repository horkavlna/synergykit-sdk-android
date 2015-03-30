package com.letsgood.synergykitandroidsdk.interfaces;



import com.letsgood.synergykitandroidsdk.listeners.BatchResponseListener;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitBatchItem;

import java.util.LinkedList;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public interface IBatches {

    public void initBatch(String batchId);
    public void removeBatch(String batchId);
    public void removeAllBatches();
    public void sendBatch(String batchId, BatchResponseListener listener, boolean parallelMode);
    public LinkedList<SynergyKitBatchItem> getBatch(String batchId);


}
