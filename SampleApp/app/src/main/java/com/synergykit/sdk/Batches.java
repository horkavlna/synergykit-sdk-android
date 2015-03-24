package com.synergykit.sdk;

import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.errors.Errors;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.interfaces.IBatches;
import com.synergykit.sdk.listeners.BatchResponseListener;
import com.synergykit.sdk.log.SynergyKitLog;
import com.synergykit.sdk.request.BatchRequestPost;
import com.synergykit.sdk.resources.SynergyKitBatchItem;
import com.synergykit.sdk.resources.SynergyKitBatchResponse;
import com.synergykit.sdk.resources.SynergyKitConfig;
import com.synergykit.sdk.resources.SynergyKitError;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public class Batches implements IBatches{

    /* Attributes */
    private HashMap<String,LinkedList<SynergyKitBatchItem>> batches = new HashMap<>();

    /* Init */
    @Override
    public void initBatch(String batchId) {
        //if contains -> return
        if(batchId==null || batches.containsKey(batchId)){
              batches.get(batchId).clear();
              return;
        }

        batches.put(batchId,new LinkedList<SynergyKitBatchItem>()); //init
    }

    /* Remove */
    @Override
    public void removeBatch(String batchId) {
        if(batchId!=null && batches.containsKey(batchId))
            batches.remove(batchId);
    }

    /* Remove all */
    @Override
    public void removeAllBatches() {
        batches = new HashMap<>();
    }

    /* Send */
    @Override
    public void sendBatch(String batchId, BatchResponseListener listener, boolean parallelMode) {
        SynergyKitConfig config = new SynergyKitConfig();
        BatchRequestPost request = new BatchRequestPost();

        //Batch check
        if(batchId==null || !batches.containsKey(batchId)){
            //Log
            SynergyKitLog.print(Errors.MSG_BATCH_NOT_FOUND);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_BATCH_NOT_FOUND, new SynergyKitError(Errors.SC_BATCH_NOT_FOUND, Errors.MSG_BATCH_NOT_FOUND));
            else if(SynergyKit.isDebugModeEnabled())
                SynergyKitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(Resource.RESOURCE_BATCH);

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(SynergyKitBatchResponse[].class);


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(batches.get(batchId));

        //execute
        SynergyKit.synergylize(request, parallelMode);

    }

    /* Batch getter*/
    @Override
    public LinkedList<SynergyKitBatchItem> getBatch(String batchId) {
       return batches.get(batchId);

    }
}
