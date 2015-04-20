package com.letsgood.sampleapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.listeners.DeleteResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    /* Attributes */
    private Button dataButton;
    private Button queriesButton;
    private Button signUpButton;
    private Button signInButton;
    private Button filesButton;
    private Button cloudCodeButton;
    private Button notificationButton;
    private Button emailButton;
    private Button batchButton;
    private Button socketButton;


    /* On create*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find views
        dataButton = (Button) findViewById(R.id.buttonData);
        queriesButton= (Button) findViewById(R.id.buttonQueries);
        signUpButton= (Button) findViewById(R.id.buttonSignUp);
        signInButton= (Button) findViewById(R.id.buttonSignIn);
        filesButton= (Button) findViewById(R.id.buttonFiles);
        cloudCodeButton= (Button) findViewById(R.id.buttonCloudCode);
        notificationButton= (Button) findViewById(R.id.buttonNotifications);
        emailButton= (Button) findViewById(R.id.buttonEmails);
        batchButton= (Button) findViewById(R.id.buttonBatch);
        socketButton= (Button) findViewById(R.id.buttonSocket);

        dataButton.setOnClickListener(this);
        queriesButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        filesButton.setOnClickListener(this);
        cloudCodeButton.setOnClickListener(this);
        notificationButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);
        batchButton.setOnClickListener(this);
        socketButton.setOnClickListener(this);
    }


    /* On create option menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        Synergykit.deleteRecord("collection","recordId",new DeleteResponseListener() {
            @Override
            public void doneCallback(int statusCode) {

            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {

            }
        }, false);

        return true;
    }

    /* On option item selected */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /* On click */
    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()){

            case R.id.buttonData:
                intent = new Intent(MainActivity.this,DataActivity.class);
                break;

            case R.id.buttonQueries:
                intent = new Intent(MainActivity.this,QueriesActivity.class);
                break;

            case R.id.buttonSignUp: {
                intent = new Intent(MainActivity.this, SignUpActivity.class);
                break;
            }

            case R.id.buttonSignIn: {
                intent = new Intent(MainActivity.this, SignInActivity.class);
                break;
            }

            case R.id.buttonFiles: {
                intent = new Intent(MainActivity.this, FilesActivity.class);
                break;
            }

            case R.id.buttonCloudCode: {
                intent = new Intent(MainActivity.this, CloudCodeActivity.class);
                break;
            }

            case R.id.buttonNotifications: {
                intent = new Intent(MainActivity.this, NotificationsActivity.class);
                break;
            }

            case R.id.buttonEmails: {
                intent = new Intent(MainActivity.this, EmailActivity.class);
                break;
            }

            case R.id.buttonBatch: {
                intent = new Intent(MainActivity.this, BatchActivity.class);
                break;
            }

            case R.id.buttonSocket: {
                intent = new Intent(MainActivity.this, SocketActivity.class);
                break;
            }


        }


        if(intent!=null)
            startActivity(intent);
    }
}
