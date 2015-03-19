package com.synergykit.sampleapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.synergykit.sampleapp.beans.Message;
import com.synergykit.sdk.SynergyKIT;
import com.synergykit.sdk.SynergyKITSdk;
import com.synergykit.sdk.addons.GsonWrapper;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.listeners.SocketEventListener;
import com.synergykit.sdk.listeners.SocketStateListener;
import com.synergykit.sdk.resources.SynergyKITError;
import com.synergykit.sdk.resources.SynergyKITObject;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SocketActivity extends ActionBarActivity {

    private String name = new String();
    private Button sendButton = null;
    private LinearLayout messageLinearLayout = null;
    private EditText messageEditText = null;
    RelativeLayout loadingLayout;
    SynergyKITSdk sdk = new SynergyKITSdk();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        sendButton = (Button) findViewById(R.id.buttonSend);
        messageLinearLayout = (LinearLayout) findViewById(R.id.messageLinearLayout);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        loadingLayout = (RelativeLayout) findViewById(R.id.loadingLayout);




        if(getIntent()!=null && getIntent().getStringExtra("name")!=null)
            name = getIntent().getStringExtra("name");

        //send button listener
        sendButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Message message = new Message();
                  message.setName(name);
                  message.setText(messageEditText.getText().toString());

                  if (message.getText() == null || message.getText().isEmpty())
                      return;

                  messageEditText.setText("");

                  SynergyKIT.createRecord("messages", message, new ResponseListener() {
                      @Override
                      public void doneCallback(int statusCode, SynergyKITObject object) {

                      }

                      @Override
                      public void errorCallback(int statusCode, SynergyKITError errorObject) {

                      }
                  }, false);
              }
          }
        );


       SynergyKIT.onSocket("created", "messages", new SocketEventListener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];
                String data = null;
                try {

                    data = jsonObject.get("data").toString();
                    final Message message = GsonWrapper.getGson().fromJson(data, Message.class);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = new TextView(SocketActivity.this);
                            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            textView.setText(message.getName() + ": " + message.getText());
                            messageLinearLayout.addView(textView);
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void subscribed() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        loadingLayout.setVisibility(View.GONE);

                        TextView textView = new TextView(SocketActivity.this);
                        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textView.setText(name + " subscribed");
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        messageLinearLayout.addView(textView);

                        sendButton.setEnabled(true);
                        sendButton.setBackgroundResource(R.color.white);



                    }
                });
            }

            @Override
            public void unsubscribed() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sendButton.setEnabled(false);
                        sendButton.setBackgroundResource(R.color.gray_brightness77);
                    }
                });
            }
        });




        SynergyKIT.onSocket("joined","user",new SocketEventListener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];
                String data = null;
                try {

                    data = jsonObject.get("data").toString();
                    final Message message = GsonWrapper.getGson().fromJson(data, Message.class);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = new TextView(SocketActivity.this);
                            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            textView.setText(message.getName() + ": " + message.getText());
                            textView.setGravity(Gravity.CENTER_HORIZONTAL);
                            messageLinearLayout.addView(textView);
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void subscribed() {

            }

            @Override
            public void unsubscribed() {

            }
        });






        SynergyKIT.connectSocket(new SocketStateListener() {
            @Override
            public void connected() {

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        loadingLayout.setVisibility(View.GONE);

                        TextView textView = new TextView(SocketActivity.this);
                        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textView.setText(name + " connected");
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        messageLinearLayout.addView(textView);

                    }
                });
            }

            @Override
            public void disconnected() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        TextView textView = new TextView(SocketActivity.this);
                        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textView.setText(name + " disconnected");
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);

                        messageLinearLayout.addView(textView);
                        sendButton.setEnabled(false);
                        sendButton.setBackgroundResource(R.color.gray_brightness77);
                    }
                });

            }

            @Override
            public void reconnected() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView textView = new TextView(SocketActivity.this);
                        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textView.setText(name + " reconnected");
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);

                        messageLinearLayout.addView(textView);

                    }
                });
            }
        });






    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // send action id to manager
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {


       SynergyKIT.disconnectSocket();
        super.onDestroy();
    }
}
