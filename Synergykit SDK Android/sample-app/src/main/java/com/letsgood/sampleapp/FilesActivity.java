package com.letsgood.sampleapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
import android.widget.Toast;

import com.letsgood.sampleapp.model.DemoUser;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.builders.UriBuilder;
import com.letsgood.synergykitsdkandroid.builders.uri.Resource;
import com.letsgood.synergykitsdkandroid.config.SynergykitConfig;
import com.letsgood.synergykitsdkandroid.listeners.BitmapResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.FileResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFile;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Marek on 1/13/15.
 */
public class FilesActivity extends ActionBarActivity implements View.OnClickListener {

    /* Attributes */
    private Button uploadFileFromBundleButton;
    private Button getLastOneButton;
    private Button destroyLastOneButton;
    private LinearLayout outputLinearLayout;


    /* On create */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        uploadFileFromBundleButton = (Button)findViewById(R.id.buttonUploadFromBundle);
        getLastOneButton = (Button)findViewById(R.id.buttonGetLastOne);
        destroyLastOneButton = (Button)findViewById(R.id.buttonDestroyLastOne);

        outputLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutOutput);

        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(),"You're not signed in. Sign in first.",Toast.LENGTH_SHORT).show();
            return;
        }

        uploadFileFromBundleButton.setOnClickListener(this);
        getLastOneButton.setOnClickListener(this);
        destroyLastOneButton.setOnClickListener(this);
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
            case R.id.buttonUploadFromBundle:
                uploadFileFromBundle();
                break;

            case R.id.buttonGetLastOne:
                getLastOne();
                break;

            case R.id.buttonDestroyLastOne:
                destroyLastOne();
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




    private void uploadFileFromBundle(){
        Bitmap logoBitmap = generateBitmap();


        setEnabled(false);
        outputLinearLayout.removeAllViews();
        printOutput("Uploading file...");

        Synergykit.createFile(logoBitmap, new FileResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitFile file) {
                printOutput("Uploading done.");
                printOutput("File: " + Synergykit.getGson().toJson(file));

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
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
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
                printOutput("Getting failed.");
                printOutput(errorObject.toString());
                setEnabled(true);
            }
        });
    }

    private void getLastOne(){

        setEnabled(false);
        outputLinearLayout.removeAllViews();
        printOutput("Getting last one...");

        SynergykitUri uri = UriBuilder.newInstance()
                                        .setResource(Resource.RESOURCE_FILES)
                                        .setOrderByDesc("createdAt")
                                        .setTop(1)
                                        .build();

        SynergykitConfig config = SynergykitConfig.newInstance()
                                                    .setUri(uri)
                                                    .setType(SynergykitFile.class);


        Synergykit.getFile(config, new FileResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitFile file) {
                printOutput("Getting failed.");
                printOutput("File: " + Synergykit.getGson().toJson(file));


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
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
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
                printOutput("Getting failed.");
                printOutput(errorObject.toString());
                setEnabled(true);
            }
        });
    }

    private void destroyLastOne(){
        setEnabled(false);
        outputLinearLayout.removeAllViews();
        printOutput("Getting last one...");

        SynergykitUri uri = UriBuilder.newInstance()
                .setResource(Resource.RESOURCE_FILES)
                .setOrderByDesc("createdAt")
                .setTop(1)
                .build();

        SynergykitConfig config = SynergykitConfig.newInstance()
                .setUri(uri)
                .setType(SynergykitFile.class);


        Synergykit.getFile(config, new FileResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitFile file) {
                printOutput("Getting done.");
                printOutput("File: " + Synergykit.getGson().toJson(file));

                printOutput("Deleting file...");

                Synergykit.deleteFile(file.getId(), new DeleteResponseListener() {
                    @Override
                    public void doneCallback(int statusCode) {
                        printOutput("Deleting done.");
                        printOutput("File destroyed");
                        setEnabled(true);
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        printOutput("Deleting failed.");
                        setEnabled(true);
                    }
                }, true);


            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                printOutput("Getting failed.");
                printOutput(errorObject.toString());
                setEnabled(true);
            }
        });
    }


    private void setEnabled(boolean enabled){
        getLastOneButton.setEnabled(enabled);
        destroyLastOneButton.setEnabled(enabled);
        uploadFileFromBundleButton.setEnabled(enabled);

    }


    private Bitmap generateBitmap(){
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        Bitmap outputBitmap = Bitmap.createBitmap(400, logoBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Paint textPaint = new Paint();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = "[" + simpleDateFormat.format(Calendar.getInstance().getTime()) + "]"; // reading local time in the system

        Canvas canvas = new Canvas(outputBitmap);
        canvas.drawBitmap(logoBitmap,0,0,new Paint());

        textPaint.setTextSize(24);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        float textHeight = textPaint.measureText("yY");

        if(Synergykit.getLoggedUser()!=null && ((DemoUser) Synergykit.getLoggedUser()).getName()!=null)
            dateTime += " " + ((DemoUser) Synergykit.getLoggedUser()).getName();

        canvas.drawText(dateTime,logoBitmap.getWidth() + 10, outputBitmap.getHeight() - textHeight, textPaint);

        return outputBitmap;
    }
}
