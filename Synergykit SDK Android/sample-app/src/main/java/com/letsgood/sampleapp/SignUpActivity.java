package com.letsgood.sampleapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letsgood.sampleapp.model.DemoUser;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

/**
 * Created by Marek on 1/13/15.
 */
public class SignUpActivity extends ActionBarActivity implements View.OnClickListener{

    /* Attributes */
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);



        nameEditText =(EditText) findViewById(R.id.nameEditText);
        emailEditText =(EditText) findViewById(R.id.emailEditText);
        passwordEditText =(EditText) findViewById(R.id.passwordEditText);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(this);

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


    /* Sign up */
    private void signUp(){

        //check values

        if(nameEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Name is empty. Fill it first",Toast.LENGTH_SHORT).show();
            return;
        }

        if(emailEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Email is empty. Fill it first",Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Password is empty. Fill it first",Toast.LENGTH_SHORT).show();
            return;
        }

        //show progress dialog

        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Signing up ...");

        //create user object
        DemoUser user = new DemoUser();
        user.setName(nameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        // Sign up via SynergyKit
        Synergykit.registerUser(user, new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {
                DemoUser demoUser = (DemoUser) user;
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), demoUser.getName() + " was signed up!", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), errorObject.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /* On click */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpButton:
                signUp();
                break;

        }
    }
}
