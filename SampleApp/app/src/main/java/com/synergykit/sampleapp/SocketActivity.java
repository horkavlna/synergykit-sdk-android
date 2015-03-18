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

import com.github.nkzawa.emitter.Emitter;
import com.synergykit.sampleapp.beans.Message;
import com.synergykit.sdk.SynergyKIT;
import com.synergykit.sdk.SynergyKITSdk;
import com.synergykit.sdk.addons.GsonWrapper;
import com.synergykit.sdk.listeners.ResponseListener;
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
        sendButton.setOnClickListener(this);


        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdk.onSocket("created","messages",new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {

                        JSONObject jsonObject = (JSONObject) args[0];
                        String data = null;
                        try {

                            data = jsonObject.get("data").toString();
                            final Message message = GsonWrapper.getGson().fromJson(data,Message.class);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textView = new TextView(SocketActivity.this);
                                    textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                                    textView.setText("Kopie: " + message.getText());
                                    messageLinearLayout.addView(textView,0);
                                }
                            });



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });






        sdk.initSocket();
        sdk.onSocket("created","messages",new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];
                String data = null;
                try {

                    data = jsonObject.get("data").toString();
                    final Message message = GsonWrapper.getGson().fromJson(data,Message.class);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = new TextView(SocketActivity.this);
                            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                            textView.setText(message.getText());
                            messageLinearLayout.addView(textView,0);
                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        sdk.connectSocket();







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
    }
}
