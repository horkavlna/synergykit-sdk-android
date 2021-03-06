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
import com.letsgood.synergykitsdkandroid.listeners.RecordsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

/**
 * Created by Marek on 1/13/15.
 */
public class DataActivity extends ActionBarActivity implements View.OnClickListener {

    /* Constants */
    private static final String COLLECTION_DEMO_OBJECTS = "demo-objects";

    /* Attributes */
    private Button postDataButton;
    private Button getDataButton;
    private LinearLayout outputLinearLayout;
    private EditText inputEditText;


    /* On create */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        postDataButton =(Button) findViewById(R.id.buttonPostData);
        getDataButton =(Button) findViewById(R.id.buttonGetData);
        outputLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOutput);
        inputEditText = (EditText) findViewById(R.id.inputEditText);
        postDataButton.setOnClickListener(this);
        getDataButton.setOnClickListener(this);

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



    /* On click */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPostData:
                postData();
                break;

            case R.id.buttonGetData:
                getData();
                break;
        }
    }



    private void postData(){

        if(inputEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"No inserted text. Insert text first",Toast.LENGTH_SHORT).show();
            return;
        }

        postDataButton.setEnabled(false);
        getDataButton.setEnabled(false);

        final DemoObject demoObject = new DemoObject();
        demoObject.setText(inputEditText.getText().toString());

        outputLinearLayout.removeAllViews();
        printOutput("Posting text...");

        Synergykit.createRecord(COLLECTION_DEMO_OBJECTS, demoObject, new ResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject object) {
                printOutput("Posting done");
                printOutput("Text: " + demoObject.getText());
                postDataButton.setEnabled(true);
                getDataButton.setEnabled(true);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Posting failed");
                printOutput(errorObject.toString());
                postDataButton.setEnabled(true);
                getDataButton.setEnabled(true);
            }
        }, false);

    }

    private void getData(){
        postDataButton.setEnabled(false);
        getDataButton.setEnabled(false);

        outputLinearLayout.removeAllViews();
        printOutput("Geting last 10 texts...");


        SynergykitUri synergyKitUri = UriBuilder
                                        .newInstance()
                                        .setResource(Resource.RESOURCE_DATA)
                                        .setCollection(COLLECTION_DEMO_OBJECTS)
                                        .setOrderByDesc("createdAt")
                                        .setTop(10)
                                        .build();

        SynergykitConfig config = SynergykitConfig
                                    .newInstance()
                                    .setParallelMode(false)
                                    .setType(DemoObject[].class)
                                    .setUri(synergyKitUri);


        Synergykit.getRecords(config, new RecordsResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject[] object) {
                printOutput("Getting done");

                for (int i = 0; i < object.length; i++)
                    printOutput("Text " + Integer.toString(i + 1) + ": " + ((DemoObject) object[i]).getText());

                postDataButton.setEnabled(true);
                getDataButton.setEnabled(true);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Getting failed");
                printOutput(errorObject.toString());
                postDataButton.setEnabled(true);
                getDataButton.setEnabled(true);
            }
        });
    }

    private void printOutput(String message){
        TextView textView = new TextView(getApplicationContext());
        textView.setTextColor(getResources().getColor(R.color.black));

        if(message==null)
            return;

        textView.setText(message);
        outputLinearLayout.addView(textView);

    }



}
