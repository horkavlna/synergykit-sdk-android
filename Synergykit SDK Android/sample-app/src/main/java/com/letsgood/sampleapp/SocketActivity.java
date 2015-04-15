package com.letsgood.sampleapp;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.letsgood.sampleapp.adapters.MessageAdapter;
import com.letsgood.sampleapp.model.DemoMessage;
import com.letsgood.sampleapp.model.DemoUser;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.addons.GsonWrapper;

import com.letsgood.synergykitsdkandroid.listeners.ResponseListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketEventListener;
import com.letsgood.synergykitsdkandroid.listeners.SocketStateListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;


import org.json.JSONObject;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 11. 3. 2015.
 */
public class SocketActivity extends ActionBarActivity implements TextWatcher, View.OnKeyListener {



    private Button sendButton = null;
    private ListView messageListView = null;
    private EditText messageEditText = null;
    private TextView typingTextView = null;
    private TextView stateTextView = null;
    RelativeLayout loadingLayout;
    boolean nextTyping = false;
    MessageAdapter adapter;
    private Thread typingThread = null;
    boolean isTyping = false;



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




        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(),"You're not signed in. Sign in first.",Toast.LENGTH_SHORT).show();
        }else{
            Synergykit.onSocket("created", "messages", new CreatedMessagesListener());
            Synergykit.onSocket("user_state", new UserStateListener());
            Synergykit.onSocket("typing", new TypingListener());
            Synergykit.connectSocket(new ConnectionStateListener());
        }

        //send button listener
        sendButton.setOnClickListener(new SendButtonOnClickListener());
        messageEditText.setOnKeyListener(this);
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

      if(Synergykit.getLoggedUser()!=null){
          DemoMessage message = new DemoMessage();
          message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
          message.setText("leaved");
          message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
          Synergykit.emitViaSocket("user_state", message);

           Synergykit.disconnectSocket();
      }
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
        DemoMessage message = new DemoMessage();
        message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
        message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
        message.setText("is typing");



        Synergykit.emitViaSocket("typing", message);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
            {
                sendMessage(v);
            }
            return false;

    }

    /** **************************************************************** */
    private class SendButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            sendMessage(v);
        }
    }

    private void sendMessage(View v){
        DemoMessage message = new DemoMessage();
        message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
        message.setUserId(Synergykit.getLoggedUser().getId());
        message.setText(messageEditText.getText().toString());

        if (message.getText() == null || message.getText().isEmpty())
            return;

        messageEditText.setText("");

        message.setText(message.getText());

        Synergykit.createRecord("messages", message, new ResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitObject object) {

            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                SynergykitLog.print(errorObject.toString());
            }
        }, false);
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
                    sendButton.setTextColor(getResources().getColor(R.color.gray_brightness50));
                }
            });
        }

        @Override
        public void reconnected() {
            //empty
        }

        @Override
        public void unauthorized() {
            //empty
        }
    }

    /** **************************************************************** */
    private class CreatedMessagesListener implements SocketEventListener {
        @Override
        public void call(Object... args) {
            SynergykitLog.print("Incomming socket message");

            JSONObject jsonObject = (JSONObject) args[0];
            String data = null;

                data = jsonObject.toString();
                final DemoMessage message = GsonWrapper.getGson().fromJson(data, DemoMessage.class);



                if(((DemoUser) Synergykit.getLoggedUser()).getId().equals(message.getUserId()))
                    message.setType(DemoMessage.TYPE_MY_MSG);
                else
                    message.setType(DemoMessage.TYPE_INCOMMING_MSG);

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
                    sendButton.setBackgroundResource(R.color.orange);
                    sendButton.setTextColor(getResources().getColor(R.color.white));

                    DemoMessage message = new DemoMessage();
                    message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
                    message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
                    message.setText("joined");

                    Synergykit.emitViaSocket("user_state", message);

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
                    sendButton.setTextColor(getResources().getColor(R.color.gray_brightness50));
                }
            });

            DemoMessage message = new DemoMessage();
            message.setName(((DemoUser) Synergykit.getLoggedUser()).getName());
            message.setUserId(((DemoUser) Synergykit.getLoggedUser()).getId());
            message.setText("leaved");

            Synergykit.emitViaSocket("user_state", message);
        }

    }


    /** **************************************************************** */
    private class UserStateListener implements SocketEventListener{
        @Override
        public void call(Object... args) {

                String data =((JSONObject) args[0]).toString();

                final DemoMessage message = GsonWrapper.getGson().fromJson(data, DemoMessage.class);

                message.setType(DemoMessage.TYPE_STATE);


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
    }

    /** **************************************************************** */
    private class TypingListener implements SocketEventListener{
        @Override
        public void call(final Object... args) {


            if(isTyping==true)
                return;


            new Thread(new Runnable() {
                @Override
                public void run() {
                    String data =((JSONObject) args[0]).toString();
                    final DemoMessage message = GsonWrapper.getGson().fromJson(data, DemoMessage.class);

                    if(((DemoUser) Synergykit.getLoggedUser()).getId().equals(message.getUserId()))
                        return;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            typingTextView.setText(message.getName() +" "+ message.getText());

                        }
                    });

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        setTyping(false);
                        e.printStackTrace();
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            typingTextView.setText("");
                            setTyping(false);

                        }
                    });
                }
            }).start();








        }

        @Override
        public void subscribed() {



        }

        @Override
        public void unsubscribed() {

        }
    }

    /** **************************************************************** */
    private synchronized void setTyping(boolean typing){
        isTyping = typing;
    }


}
