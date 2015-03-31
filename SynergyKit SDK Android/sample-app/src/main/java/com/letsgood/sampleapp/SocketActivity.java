package com.letsgood.sampleapp;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.letsgood.sampleapp.adapters.MessageAdapter;
import com.letsgood.synergykitsdkandroid.SynergyKit;
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;
import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketEventListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketStateListener;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergyKitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitError;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergyKitUser;


import org.json.JSONObject;

import java.util.Random;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SocketActivity extends ActionBarActivity implements TextWatcher {

    private String name = new String();
    private Button sendButton = null;
    private ListView messageListView = null;
    private EditText messageEditText = null;
    private int userId = 0;

    private TextView typingTextView = null;
    private TextView stateTextView = null;
    RelativeLayout loadingLayout;

    MessageAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        sendButton = (Button) findViewById(R.id.buttonSend);
        messageListView = (ListView) findViewById(R.id.messageListView);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        messageEditText.addTextChangedListener(this);
        loadingLayout = (RelativeLayout) findViewById(R.id.loadingLayout);
        stateTextView = (TextView )findViewById(R.id.stateTextView);
        typingTextView = (TextView )findViewById(R.id.typingTextView);

        adapter = new MessageAdapter(getBaseContext(),R.layout.item_socket_message_right);
        messageListView.setAdapter(adapter);

        Random rand = new Random();

        userId = rand.nextInt();


        if(getIntent()!=null && getIntent().getStringExtra("name")!=null)
            name = getIntent().getStringExtra("name");

        //send button listener
        sendButton.setOnClickListener(new SendButtonOnClickListener());

        SynergyKitUser user = new SynergyKitUser();
        user.setPassword("test");
        user.setEmail("test@teest.eu");

        SynergyKit.loginUser(user, new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergyKitUser user) {
                SynergyKitLog.print("User logged");
                SynergyKit.onSocket("created", "messages", new CreatedMessagesListener());
                SynergyKit.onSocket("user_state", new UserStateListener());
                SynergyKit.onSocket("typing", new TypingListener());
                SynergyKit.connectSocket(new ConnectionStateListener());

            }

            @Override
            public void errorCallback(int statusCode, SynergyKitError errorObject) {
                SynergyKitLog.print("User not logged: " + errorObject.toString());
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
      com.letsgood.sampleapp.SocketMessage message = new com.letsgood.sampleapp.SocketMessage();
      message.setName(name);
      message.setText("leaved");
      message.setUserId(userId);
      SynergyKit.emitViaSocket("user_state",message);

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
        com.letsgood.sampleapp.SocketMessage message = new com.letsgood.sampleapp.SocketMessage();
        message.setName(name);
        message.setUserId(userId);
        message.setText("");



        SynergyKit.emitViaSocket("typing",message);
    }

    /** **************************************************************** */
    private class SendButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            com.letsgood.sampleapp.SocketMessage message = new com.letsgood.sampleapp.SocketMessage();
            message.setName(name);
            message.setUserId(userId);
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
    private class ConnectionStateListener implements SocketStateListener {

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
    private class CreatedMessagesListener implements SocketEventListener {
        @Override
        public void call(Object... args) {
            JSONObject jsonObject = (JSONObject) args[0];
            String data = null;

                data = jsonObject.toString();
                final com.letsgood.sampleapp.SocketMessage message = GsonWrapper.getGson().fromJson(data, com.letsgood.sampleapp.SocketMessage.class);


                if(userId == message.getUserId())
                    message.setType(1);
                else
                    message.setType(0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.add(message);
                        adapter.notifyDataSetChanged();
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

                    com.letsgood.sampleapp.SocketMessage message = new com.letsgood.sampleapp.SocketMessage();
                    message.setName(name);
                    message.setUserId(userId);
                    message.setText("joined");

                    SynergyKit.emitViaSocket("user_state",message);

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

            com.letsgood.sampleapp.SocketMessage message = new com.letsgood.sampleapp.SocketMessage();
            message.setName(name);
            message.setUserId(userId);
            message.setText("leaved");

            SynergyKit.emitViaSocket("user_state",message);
        }

        @Override
        public void unauthorized() {

        }
    }


    /** **************************************************************** */
    private class UserStateListener implements SocketEventListener{
        @Override
        public void call(Object... args) {

                String data =((JSONObject) args[0]).toString();

                final com.letsgood.sampleapp.SocketMessage message = GsonWrapper.getGson().fromJson(data, com.letsgood.sampleapp.SocketMessage.class);

                message.setType(2);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.add(message);
                        adapter.notifyDataSetChanged();
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

            String data =((JSONObject) args[0]).toString();

            final com.letsgood.sampleapp.SocketMessage message = GsonWrapper.getGson().fromJson(data, com.letsgood.sampleapp.SocketMessage.class);

            if(userId == message.getUserId())
                return;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    typingTextView.setText(message.getName() +" "+ message.getText());

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
