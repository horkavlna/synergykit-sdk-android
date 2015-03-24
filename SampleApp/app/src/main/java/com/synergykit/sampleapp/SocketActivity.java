package com.synergykit.sampleapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.synergykit.sampleapp.beans.Message;
import com.synergykit.sdk.Socket;
import com.synergykit.sdk.SynergyKit;
import com.synergykit.sdk.SynergyKitSdk;
import com.synergykit.sdk.addons.GsonWrapper;
import com.synergykit.sdk.listeners.ResponseListener;
import com.synergykit.sdk.listeners.SocketEventListener;
import com.synergykit.sdk.listeners.SocketStateListener;
import com.synergykit.sdk.listeners.UserResponseListener;
import com.synergykit.sdk.log.SynergyKitLog;
import com.synergykit.sdk.resources.SynergyKitError;
import com.synergykit.sdk.resources.SynergyKitObject;
import com.synergykit.sdk.resources.SynergyKitUser;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SocketActivity extends ActionBarActivity implements TextWatcher{

    private String name = new String();
    private Button sendButton = null;
    private LinearLayout messageLinearLayout = null;
    private EditText messageEditText = null;

    private TextView typingTextView = null;
    private TextView stateTextView = null;
    RelativeLayout loadingLayout;
    SynergyKitSdk sdk = new SynergyKitSdk();


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
        messageEditText.addTextChangedListener(this);
        loadingLayout = (RelativeLayout) findViewById(R.id.loadingLayout);
        stateTextView = (TextView )findViewById(R.id.stateTextView);
        typingTextView = (TextView )findViewById(R.id.typingTextView);




        if(getIntent()!=null && getIntent().getStringExtra("name")!=null)
            name = getIntent().getStringExtra("name");

        //send button listener
        sendButton.setOnClickListener(new SendButtonOnClickListener());

        SynergyKitUser user = new SynergyKitUser();
        user.setPassword("test");
        user.setEmail("test@teest.eu");

        SynergyKit.loginUser(user,new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergyKitUser user) {
                SynergyKitLog.print("User logged");
                SynergyKit.onSocket("created", "messages", new CreatedMessagesListener());
                SynergyKit.onSocket("joined",new UserJoinedListener());
                SynergyKit.onSocket("leave",new UserLeaveListener());
                SynergyKit.onSocket("typing",new TypingListener());
                SynergyKit.connectSocket(new ConnectionStateListener());

            }

            @Override
            public void errorCallback(int statusCode, SynergyKitError errorObject) {

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

      SynergyKit.emitViaSocket("leave",name);

       SynergyKit.disconnectSocket();
        super.onDestroy();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        SynergyKit.emitViaSocket("typing",name);
    }

    /** **************************************************************** */
    private class SendButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Message message = new Message();
            message.setName(name);
            message.setText(messageEditText.getText().toString());

            if (message.getText() == null || message.getText().isEmpty())
                return;

            messageEditText.setText("");

            message.setText(message.getText());

            SynergyKit.createRecord("messages", message, new ResponseListener() {
                @Override
                public void doneCallback(int statusCode, SynergyKitObject object) {

                }

                @Override
                public void errorCallback(int statusCode, SynergyKitError errorObject) {

                }
            }, false);
        }
    }

    /** **************************************************************** */
    private class ConnectionStateListener implements SocketStateListener{

        @Override
        public void connected() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    stateTextView.setText("Connected");
                    stateTextView.setBackgroundResource(R.color.green);
                }
            });
        }

        @Override
        public void disconnected() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    stateTextView.setText("Disconnected");
                    stateTextView.setBackgroundResource(R.color.red);
                    sendButton.setEnabled(false);
                    sendButton.setBackgroundResource(R.color.gray_brightness77);
                }
            });
        }

        @Override
        public void reconnected() {
                //empty
        }
    }

    /** **************************************************************** */
    private class CreatedMessagesListener implements SocketEventListener{
        @Override
        public void call(Object... args) {
            JSONObject jsonObject = (JSONObject) args[0];
            String data = null;

                data = jsonObject.toString();
                final Message message = GsonWrapper.getGson().fromJson(data, Message.class);
                final TextView textView = new TextView(SocketActivity.this);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(message.getName() + ": " + message.getText());
                        messageLinearLayout.addView(textView);
                    }
                });




        }

        @Override
        public void subscribed() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadingLayout.setVisibility(View.GONE);
                    sendButton.setEnabled(true);
                    sendButton.setBackgroundResource(R.color.white);


                    SynergyKit.emitViaSocket("joined",name);

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

            SynergyKit.emitViaSocket("leave",name);
        }

        @Override
        public void unauthorized() {

        }
    }


    /** **************************************************************** */
    private class UserJoinedListener implements SocketEventListener{
        @Override
        public void call(Object... args) {

                String data =(String)args[0];;

                final String message = GsonWrapper.getGson().fromJson(data, String.class);
                final TextView textView = new TextView(SocketActivity.this);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setText(message + "  connected" );
                textView.setGravity(Gravity.CENTER_HORIZONTAL);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messageLinearLayout.addView(textView);
                    }
                });


        }

        @Override
        public void subscribed() {



        }

        @Override
        public void unsubscribed() {

        }

        @Override
        public void unauthorized() {

        }
    }

    /** **************************************************************** */
    private class UserLeaveListener implements SocketEventListener{
        @Override
        public void call(Object... args) {

            String data =(String)args[0];;

            final String message = GsonWrapper.getGson().fromJson(data, String.class);
            final TextView textView = new TextView(SocketActivity.this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(message + "  disconnected" );
            textView.setGravity(Gravity.CENTER_HORIZONTAL);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageLinearLayout.addView(textView);
                }
            });


        }

        @Override
        public void subscribed() {



        }

        @Override
        public void unsubscribed() {

        }

        @Override
        public void unauthorized() {

        }
    }

    /** **************************************************************** */
    private class TypingListener implements SocketEventListener{
        @Override
        public void call(Object... args) {

            String data =(String)args[0];;

            final String message = GsonWrapper.getGson().fromJson(data, String.class);


            if(name.equals(message))
                return;


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    typingTextView.setText(message + " is typing");

                }
            });

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    typingTextView.setText("");

                }
            });
        }

        @Override
        public void subscribed() {



        }

        @Override
        public void unsubscribed() {

        }

        @Override
        public void unauthorized() {

        }
    }

}
