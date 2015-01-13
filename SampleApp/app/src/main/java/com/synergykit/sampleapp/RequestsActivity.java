package com.synergykit.sampleapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.synergykit.sampleapp.adapters.DemoObjectAdapter;
import com.synergykit.sampleapp.model.DemoObject;
import com.synergykit.sampleapp.widgets.CustomProgressDialog;
import com.synergykit.sampleapp.widgets.ErrorAlertDialog;
import com.synergykit.sdk.SynergyKIT;
import com.synergykit.sdk.listeners.RecordsResponseListener;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marek on 1/13/15.
 */
public class RequestsActivity  extends ActionBarActivity {

    private ListView list;
    private DemoObjectAdapter adapter;
    private ArrayList<SynergyKITObject> demoObjects;
    private Button postButton;
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

        list = (ListView) findViewById(R.id.listView);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textEdit.getText().toString().length()<1)
                    return;

                final CustomProgressDialog pd = new CustomProgressDialog(
                        RequestsActivity.this, "Loading...");


                DemoObject demoObject = new DemoObject();
                demoObject.setText(textEdit.getText().toString());

                SynergyKIT.createRecord("demo-objects",demoObject, new ResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, final SynergyKITObject object) {
                        pd.dismiss();
                        loadData();
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergyKITError errorObject) {
                        pd.dismiss();
                        if (errorObject != null) {
                            new ErrorAlertDialog(RequestsActivity.this, errorObject
                                    .getMessage()).show();
                        }

                    }
                },false);
            }
        });

        loadData();

    }


    private void loadData(){
        final CustomProgressDialog pd = new CustomProgressDialog(
                this, "Loading...");

        SynergyKIT.getRecords("demo-objects", DemoObject[].class, new RecordsResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergyKITObject[] objects) {
                pd.dismiss();

                if (objects != null)
                    demoObjects = new ArrayList<SynergyKITObject>(Arrays.asList(objects));

                adapter = new DemoObjectAdapter(RequestsActivity.this, R.layout.item_demo_object,
                        demoObjects);

                list.setAdapter(adapter);
            }

            @Override
            public void errorCallback(int statusCode, SynergyKITError errorObject) {
                pd.dismiss();
                if (errorObject != null) {
                    new ErrorAlertDialog(RequestsActivity.this, errorObject
                            .getMessage()).show();
                }
            }
        },false);
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
