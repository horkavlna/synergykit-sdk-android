package com.letsgood.synergykitsdkandroid;


import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.interfaces.IBatches;
import com.letsgood.synergykitsdkandroid.listeners.BatchResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.request.BatchRequestPost;
import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchItem;
import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchResponse;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 23. 2. 2015.
 */
public class Batches implements IBatches {

    /* Attributes */
    private HashMap<String,LinkedList<SynergykitBatchItem>> batches = new HashMap<>();

    /* Init */
    @Override
    public void initBatch(String batchId) {
        //if contains -> return
        if(batchId==null || batches.containsKey(batchId)){
              batches.get(batchId).clear();
              return;
        }

        batches.put(batchId,new LinkedList<SynergykitBatchItem>()); //init
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
        SynergykitConfig config = new SynergykitConfig();
        BatchRequestPost request = new BatchRequestPost();

        //Batch check
        if(batchId==null || !batches.containsKey(batchId)){
            //Log
            SynergykitLog.print(Errors.MSG_BATCH_NOT_FOUND);

            //error callback
            if(listener!=null)
                listener.errorCallback(Errors.SC_BATCH_NOT_FOUND, new SynergykitError(Errors.SC_BATCH_NOT_FOUND, Errors.MSG_BATCH_NOT_FOUND));
            else if(Synergykit.isDebugModeEnabled())
                SynergykitLog.print(Errors.MSG_NO_CALLBACK_LISTENER);

            return;
        }

        //Uri builder
        UriBuilder uriBuilder = new UriBuilder()
                .setResource(Resource.RESOURCE_BATCH);

        //set config
        config.setUri(uriBuilder.build());
        config.setParallelMode(parallelMode);
        config.setType(SynergykitBatchResponse[].class);


        //set request
        request.setConfig(config);
        request.setListener(listener);
        request.setObject(batches.get(batchId));

        //execute
        Synergykit.synergylize(request, parallelMode);

    }

    /* Batch getter*/
    @Override
    public LinkedList<SynergykitBatchItem> getBatch(String batchId) {
       return batches.get(batchId);

    }
}
