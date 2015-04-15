package com.letsgood.sampleapp;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.letsgood.sampleapp.model.DemoObject;
import com.letsgood.sampleapp.model.DemoUser;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.listeners.BitmapResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FilesResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.RecordsResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.UsersResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

/**
 * Created by Marek on 1/13/15.
 */
public class QueriesActivity extends ActionBarActivity implements View.OnClickListener {

    /* Constants */
    private static final String COLLECTION_DEMO_OBJECTS = "demo-objects";

    /* Attributes */
    private Button findLastUserButton;
    private Button fiveEmailsButton;
    private Button findLastRecordButton;
    private Button fiveTextsButton;
    private Button findLastFileButton;
    private Button fiveFilesButton;
    private LinearLayout outputLinearLayout;


    /* On create */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        findLastUserButton = (Button) findViewById(R.id.buttonFindLastUser);
        fiveEmailsButton = (Button) findViewById(R.id.button5Emails);
        findLastRecordButton = (Button) findViewById(R.id.buttonFindLastRecord);
        fiveTextsButton = (Button) findViewById(R.id.button5Texts);
        findLastFileButton = (Button) findViewById(R.id.buttonFindLastFile);
        fiveFilesButton = (Button) findViewById(R.id.button5Files);
        outputLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOutput);

        findLastUserButton.setOnClickListener(this);
        fiveEmailsButton.setOnClickListener(this);
        findLastRecordButton.setOnClickListener(this);
        fiveTextsButton.setOnClickListener(this);
        findLastFileButton.setOnClickListener(this);
        fiveFilesButton.setOnClickListener(this);

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
            case R.id.buttonFindLastUser:
                findLastUser();
                break;

            case R.id.button5Emails:
                getFiveEmails();
                break;
            case R.id.buttonFindLastRecord:
                findLastRecord();
                break;

            case R.id.button5Texts:
                getFiveTexts();
                break;
            case R.id.buttonFindLastFile:
                findLastFile();
                break;

            case R.id.button5Files:
                getFiveFiles();
                break;
        }
    }

    private void findLastUser(){
        setEnabled(false);

        outputLinearLayout.removeAllViews();
        printOutput("Finding last user...");

        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_USERS)
                .setOrderByDesc("createdAt")
                .setTop(1)
                .build();

        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(DemoUser.class)
                .setUri(synergyKitUri);


        Synergykit.getUser(config, new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {
                printOutput("Finding done");
                printOutput("User: " + ((DemoUser) user).getName());

                setEnabled(true);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Finding failed");
                printOutput(errorObject.toString());
                setEnabled(true);
            }
        });

    }

    private void getFiveEmails(){
        setEnabled(false);

        outputLinearLayout.removeAllViews();
        printOutput("Getting 5 emails...");

        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_USERS)
                .setTop(5)
                .setOrderByDesc("createdAt")
                .build();

        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(DemoUser[].class)
                .setUri(synergyKitUri);


        Synergykit.getUsers(config, new UsersResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser[] users) {
                printOutput("Getting done");

                for (int i = 0; users != null && i < users.length; i++)
                    printOutput("Email " + Integer.toString(i + 1) + ": " + ((DemoUser) users[i]).getEmail());

                setEnabled(true);

            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Getting failed");
                printOutput(errorObject.toString());
                setEnabled(true);
            }
        });
    }

    private void findLastRecord(){
        setEnabled(false);

        outputLinearLayout.removeAllViews();
        printOutput("Finding last record...");

        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_DATA)
                .setCollection(COLLECTION_DEMO_OBJECTS)
                .setOrderByDesc("createdAt")
                .setTop(1)
                .build();

        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(DemoObject.class)
                .setUri(synergyKitUri);


        Synergykit.getRecord(config, new ResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject object) {
                printOutput("Finding done");
                printOutput("Record: " + ((DemoObject) object).getText());

                setEnabled(true);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Finding failed");
                printOutput(errorObject.toString());
                setEnabled(true);
            }
        });

    }

    private void getFiveTexts(){
        setEnabled(false);

        outputLinearLayout.removeAllViews();
        printOutput("Getting 5 texts...");

        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_DATA)
                .setCollection(COLLECTION_DEMO_OBJECTS)
                .setOrderByDesc("createdAt")
                .setTop(5)
                .build();

        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(DemoObject[].class)
                .setUri(synergyKitUri);


        Synergykit.getRecords(config, new RecordsResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject[] objects) {
                printOutput("Getting done");

                for (int i = 0; objects != null && i < objects.length; i++)
                    printOutput("Text " + Integer.toString(i + 1) + ": " + ((DemoObject) objects[i]).getText());

                setEnabled(true);

            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Getting failed");
                printOutput(errorObject.toString());
                setEnabled(true);
            }
        });

    }

    private void findLastFile(){
        setEnabled(false);

        outputLinearLayout.removeAllViews();
        printOutput("Finding last file record...");

        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_FILES)
                .setOrderByDesc("createdAt")
                .setTop(1)
                .build();

        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(SynergykitFile.class)
                .setUri(synergyKitUri);


        Synergykit.getFile(config, new FileResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitFile file) {
                printOutput("Finding done");
                printOutput("File path: " + file.getPath());


                if (!file.getPath().endsWith(".jpg")) {
                    setEnabled(true);
                    return;
                }

                printOutput("Download image...");

                Synergykit.downloadBitmap(file.getPath(), new BitmapResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, Bitmap bitmap) {
                        printOutput("Download done...");
                        ImageView imageView = new ImageView(getApplicationContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(android.app.ActionBar.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        imageView.setImageBitmap(bitmap);
                        imageView.setLayoutParams(layoutParams);
                        outputLinearLayout.addView(imageView);

                        setEnabled(true);
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        printOutput("Downloading failed");
                        printOutput(errorObject.toString());
                        setEnabled(true);
                    }
                });
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Finding failed");
                printOutput(errorObject.toString());
                setEnabled(true);
            }
        });


    }

    private void getFiveFiles(){
        setEnabled(false);

        outputLinearLayout.removeAllViews();
        printOutput("Finding last file record...");

        SynergykitUri synergyKitUri = UriBuilder
                .newInstance()
                .setResource(Resource.RESOURCE_FILES)
                .setOrderByDesc("createdAt")
                .setTop(5)
                .build();

        SynergykitConfig config = SynergykitConfig
                .newInstance()
                .setParallelMode(false)
                .setType(SynergykitFile[].class)
                .setUri(synergyKitUri);


        Synergykit.getFiles(config, new FilesResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitFile[] files) {
                printOutput("Finding done");

                for (int i = 0; files != null && i < files.length; i++)
                    printOutput("File " + Integer.toString(i + 1) + " path: " + files[i].getPath());

                setEnabled(true);
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Finding failed");
                printOutput(errorObject.toString());
                setEnabled(true);
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


    private void setEnabled(boolean enabled){
        findLastUserButton.setEnabled(enabled);
        fiveEmailsButton.setEnabled(enabled);
        findLastRecordButton.setEnabled(enabled);
        fiveTextsButton.setEnabled(enabled);
        findLastFileButton.setEnabled(enabled);
        fiveFilesButton.setEnabled(enabled);
    }

}
