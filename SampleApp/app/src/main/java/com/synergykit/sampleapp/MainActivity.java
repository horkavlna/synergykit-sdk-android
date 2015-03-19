package com.synergykit.sampleapp;

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


public class MainActivity extends ActionBarActivity {

    private Button notificationsButton;
    private Button signInButton;
    private Button signUpButton;
    private Button requestsButton;
    private Button buttonCloudCode;
    private Button buttonSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        notificationsButton =(Button) findViewById(R.id.buttonNotifications);
        signInButton = (Button) findViewById(R.id.buttonSignIn);
        signUpButton = (Button) findViewById(R.id.buttonSignUp);
        requestsButton = (Button) findViewById(R.id.buttonRequests);
        buttonCloudCode = (Button) findViewById(R.id.buttonCloudCode);
        buttonSocket = (Button) findViewById(R.id.buttonSocket);


        notificationsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


        requestsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequestsActivity.class);
                startActivity(intent);
            }
        });

        buttonCloudCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CloudCodeActivity.class);
                startActivity(intent);
            }
        });

        buttonSocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openSocketActivity();
            }
        });


    }


    private void openSocketActivity(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your name:");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(input.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name must be set. Set it first", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, SocketActivity.class);
                    intent.putExtra("name",input.getText().toString());
                    dialog.cancel();
                    startActivity(intent);
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
}
