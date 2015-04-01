package com.letsgood.sampleapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.letsgood.sampleapp.adapters.DemoObjectAdapter;
import com.letsgood.sampleapp.model.DemoObject;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.sampleapp.widgets.ErrorAlertDialog;
import com.letsgood.synergykitsdkandroid.SynergyKit;
import com.letsgood.synergykitsdkandroid.listeners.RecordsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitObject;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marek on 1/13/15.
 */
public class RequestsActivity  extends ActionBarActivity {

    private ListView list;
    private DemoObjectAdapter adapter;
    private ArrayList<SynergyKitObject> demoObjects;
    private Button postButton;
    private Button postButton2;
    private EditText textEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        textEdit = (EditText) findViewById(R.id.text);
        postButton = (Button) findViewById(R.id.postButton);
        postButton2 = (Button) findViewById(R.id.postButton2);

        list = (ListView) findViewById(R.id.listView);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textEdit.getText().toString().length() < 1)
                    return;

                final CustomProgressDialog pd = new CustomProgressDialog(
                        RequestsActivity.this, "Loading...");


                DemoObject demoObject = new DemoObject();
                demoObject.setText(textEdit.getText().toString());

                SynergyKit.createRecord("demo-objects", demoObject, new ResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, final SynergyKitObject object) {
                        pd.dismiss();
                        loadData();
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergyKitError errorObject) {
                        pd.dismiss();
                        if (errorObject != null) {
                            new ErrorAlertDialog(RequestsActivity.this, errorObject
                                    .getMessage()).show();
                        }

                    }
                }, false);
            }
        });


        postButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textEdit.getText().toString().length() < 1)
                    return;

                final CustomProgressDialog pd = new CustomProgressDialog(
                        RequestsActivity.this, "Loading...");


                DemoObject demoObject = new DemoObject("demo-objects");
                demoObject.setText(textEdit.getText().toString());
                demoObject.save(new ResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, final SynergyKitObject object) {
                        pd.dismiss();
                        loadData2(object.getRecordId());
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergyKitError errorObject) {
                        pd.dismiss();
                        if (errorObject != null) {
                            new ErrorAlertDialog(RequestsActivity.this, errorObject
                                    .getMessage()).show();
                        }

                    }
                });
            }
        });

        loadData();
    }


    private void loadData(){
        final CustomProgressDialog pd = new CustomProgressDialog(
                this, "Loading...");

        SynergyKit.getRecords("demo-objects", DemoObject[].class, new RecordsResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergyKitObject[] objects) {
                pd.dismiss();

                if (objects != null)
                    demoObjects = new ArrayList<SynergyKitObject>(Arrays.asList(objects));

                adapter = new DemoObjectAdapter(RequestsActivity.this, R.layout.item_demo_object,
                        demoObjects);

                list.setAdapter(adapter);
            }

            @Override
            public void errorCallback(int statusCode, SynergyKitError errorObject) {
                pd.dismiss();
                if (errorObject != null) {
                    new ErrorAlertDialog(RequestsActivity.this, errorObject
                            .getMessage()).show();
                }
            }
        }, false);
    }


    private void loadData2(String recordId){
        final CustomProgressDialog pd = new CustomProgressDialog(
                this, "Loading...");

        new DemoObject("demo-objects",recordId).fetch(new ResponseListener() {

            @Override
            public void doneCallback(int statusCode, SynergyKitObject object) {
                pd.dismiss();
                DemoObject[] demoObject = new DemoObject[1];
                demoObject[0] = (DemoObject) object;


                if (demoObjects != null)
                    demoObjects = new ArrayList<SynergyKitObject>(Arrays.asList(demoObject));

                adapter = new DemoObjectAdapter(RequestsActivity.this, R.layout.item_demo_object,
                        demoObjects);

                list.setAdapter(adapter);
            }

            @Override
            public void errorCallback(int statusCode, SynergyKitError errorObject) {
                pd.dismiss();
                if (errorObject != null) {
                    new ErrorAlertDialog(RequestsActivity.this, errorObject
                            .getMessage()).show();
                }
            }
        });

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
}
