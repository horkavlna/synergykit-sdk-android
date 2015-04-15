package com.letsgood.sampleapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.letsgood.sampleapp.model.DemoObject;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.listeners.BatchResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchItem;
import com.letsgood.synergykitsdkandroid.resources.SynergykitBatchResponse;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEndpoint;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;


/**
 * Created by Marek on 1/13/15.
 */
public class BatchActivity extends ActionBarActivity implements View.OnClickListener {

    /* Constants */
    private static final String COLLECTION_DEMO_OBJECTS = "demo-objects";
    private static final String BATCH_ID = "my-batch-name";

    /* Attributes */
    private Button batchButton;
    private LinearLayout outputLinearLayout;
    private EditText inputEditText;

    /* On create */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        batchButton =(Button) findViewById(R.id.buttonBatch);
        outputLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOutput);
        inputEditText = (EditText) findViewById(R.id.inputEditText);
        batchButton.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // send action id to manager
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBatch:
                postBatch();
                break;
        }
    }

    private void printOutput(String message){
        TextView textView = new TextView(getApplicationContext());
        textView.setTextColor(getResources().getColor(R.color.black));

        if(message==null)
            return;

        textView.setText(message);
        outputLinearLayout.addView(textView);

    }

    private void postBatch(){

        if(inputEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "No inserted text. Insert text first", Toast.LENGTH_SHORT).show();
            return;
        }

        batchButton.setEnabled(false);
        outputLinearLayout.removeAllViews();

        SynergykitUri uri = UriBuilder
                                .newInstance()
                                .setResource(Resource.RESOURCE_DATA)
                                .setCollection(COLLECTION_DEMO_OBJECTS)
                                .setTop(1)
                                .build();

        SynergykitConfig config = SynergykitConfig
                                        .newInstance()
                                        .setUri(uri)
                                        .setType(DemoObject.class);


        printOutput("Getting last record....");
        Synergykit.getRecord(config, new ResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject object) {
                DemoObject demoObject = (DemoObject) object;
                DemoObject demoObjectPatch = new DemoObject();

                printOutput("Getting done.");

                //set demo object
                demoObject.setText(inputEditText.getText().toString());

                //set patch object
                demoObjectPatch.setText("another string");
                demoObjectPatch.setId(demoObject.getId());
                demoObjectPatch.setVersion(demoObject.getVersion());


                Synergykit.initBatch(BATCH_ID);

                //Batch item 0
                DemoObject demoObjectBatch0 = new DemoObject();
                demoObject.setText(inputEditText.getText().toString());


                SynergykitEndpoint batchEndpoint0 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .buildEndpoint();

                SynergykitBatchItem batchItem0 = new SynergykitBatchItem(Synergykit.POST, batchEndpoint0, demoObjectBatch0);
                Synergykit.getBatch(BATCH_ID).add(batchItem0);

                //Batch item 1
                SynergykitEndpoint batchEndpoint1 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .setRecordId(demoObject.getId())
                        .buildEndpoint();

                SynergykitBatchItem batchItem1 = new SynergykitBatchItem(Synergykit.PUT, batchEndpoint1, demoObject);
                Synergykit.getBatch(BATCH_ID).add(batchItem1);

                //Batch item 2
                SynergykitEndpoint batchEndpoint2 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .setRecordId(demoObjectPatch.getId())
                        .buildEndpoint();

                demoObjectPatch.setVersion(demoObject.getVersion() + 1);
                SynergykitBatchItem batchItem2 = new SynergykitBatchItem(Synergykit.PATCH, batchEndpoint2, demoObjectPatch);
                Synergykit.getBatch(BATCH_ID).add(batchItem2);

                //Batch item 3
                SynergykitEndpoint batchEndpoint3 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .setRecordId(demoObject.getId())
                        .buildEndpoint();

                SynergykitBatchItem batchItem3 = new SynergykitBatchItem(Synergykit.DELETE, batchEndpoint3);
                Synergykit.getBatch(BATCH_ID).add(batchItem3);

                //Batch item 4
                SynergykitEndpoint batchEndpoint4 = UriBuilder
                        .newInstance()
                        .setResource(Resource.RESOURCE_DATA)
                        .setCollection(COLLECTION_DEMO_OBJECTS)
                        .setRecordId(demoObject.getId())
                        .buildEndpoint();


                SynergykitBatchItem batchItem4 = new SynergykitBatchItem(Synergykit.PUT, batchEndpoint4, demoObject);
                Synergykit.getBatch(BATCH_ID).add(batchItem4);

                printOutput("Sending batch...");
                Synergykit.sendBatch(BATCH_ID, new BatchResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, SynergykitBatchResponse[] batchResponse) {
                        printOutput("Sending batch done.");


                        for (int i = 0; batchResponse != null && i < batchResponse.length; i++) {
                            printOutput("Batch item " + i + " response: " + Integer.toString(batchResponse[i].getStatusCode()) + " " + Synergykit.getGson().toJson(batchResponse[i].getBody()));

                        }

                        batchButton.setEnabled(true);
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        printOutput("Sending batch failed.");
                        printOutput(errorObject.toString());
                        batchButton.setEnabled(true);
                    }
                }, false);


            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Getting failed.");
                batchButton.setEnabled(true);
            }
        });


    }
}
