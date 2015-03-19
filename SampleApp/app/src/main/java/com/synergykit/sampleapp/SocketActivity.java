package com.synergykit.sampleapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.synergykit.sampleapp.beans.Message;
import com.synergykit.sdk.SynergyKIT;
import com.synergykit.sdk.SynergyKITSdk;
import com.synergykit.sdk.addons.GsonWrapper;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.listeners.SocketListener;
import com.synergykit.sdk.log.SynergyKITLog;
import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITObject;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SocketActivity extends ActionBarActivity implements View.OnClickListener{

    private Button sendButton = null;
    private Button copyButton = null;
    private LinearLayout messageLinearLayout = null;
    private EditText messageEditText = null;
    SynergyKITSdk sdk = new SynergyKITSdk();
    private boolean copyEnabled = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        sendButton = (Button) findViewById(R.id.buttonSend);
        copyButton = (Button) findViewById(R.id.buttonCopy);
        messageLinearLayout = (LinearLayout)findViewById(R.id.messageLinearLayout);
        messageEditText = (EditText)findViewById(R.id.messageEditText);

        //send button listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.setName("Pavel");
                message.setText(messageEditText.getText().toString());

                if(message.getText()==null || message.getText().isEmpty())
                    return;

                messageEditText.setText("");

                SynergyKIT.createRecord("messages",message,new ResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, SynergyKITObject object) {

                    }

                    @Override
                    public void errorCallback(int statusCode, SynergyKITError errorObject) {

                    }
                },false);
            }}
            );

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdk.onSocket("created","messages",new SocketListener() {
                    @Override
                    public void call(Object... args) {
                        
                    }

                    @Override
                    public void subscribed() {
                        sendButton.setEnabled(true);
                        Toast.makeText(getApplicationContext(),"Next listener subscribed",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void unsubscribed() {
                        Toast.makeText(getApplicationContext(),"Next listener unsubscribed",Toast.LENGTH_LONG).show();
                    }
                });
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

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

