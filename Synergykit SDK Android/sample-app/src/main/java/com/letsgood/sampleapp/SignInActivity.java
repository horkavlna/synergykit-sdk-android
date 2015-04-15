package com.letsgood.sampleapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.letsgood.sampleapp.model.DemoUser;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFacebookAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Marek on 1/13/15.
 */
public class SignInActivity extends ActionBarActivity implements View.OnClickListener {

    /* Constants */
    private static final String USER_SHARED_PREF = "user-shared-pref";
    private static final String USER_PASSWD_TAG = "user-password-tag";
    private static final String USER_EMAIL_TAG = "user-email-tag";

    /* Attributes */
    private SharedPreferences sharedPreferences = null;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private LoginButton facebookButton;
    private Button addRoleButton;
    private Button removeRoleButton;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_signin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);



        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.letsgood.sampleapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }







        callbackManager = CallbackManager.Factory.create();

        sharedPreferences = getSharedPreferences(USER_SHARED_PREF,MODE_PRIVATE);

        emailEditText =(EditText) findViewById(R.id.emailEditText);
        passwordEditText =(EditText) findViewById(R.id.passwordEditText);
        signInButton = (Button) findViewById(R.id.signInButton);
        addRoleButton = (Button) findViewById(R.id.addRoleButton);
        removeRoleButton = (Button) findViewById(R.id.removeRoleButton);
        facebookButton = (LoginButton) findViewById(R.id.facebookLoginButton);


        emailEditText.setText(sharedPreferences.getString(USER_EMAIL_TAG,""));
        passwordEditText.setText(sharedPreferences.getString(USER_PASSWD_TAG,""));


        signInButton.setOnClickListener(this);
        addRoleButton.setOnClickListener(this);
        removeRoleButton.setOnClickListener(this);



        // Callback registration
        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                LoginManager.getInstance().logOut();

                if(Synergykit.getLoggedUser()==null){

                    return;
                }

                SynergykitUser user = Synergykit.getLoggedUser();
                SynergykitFacebookAuthData facebookAuthData = new SynergykitFacebookAuthData(loginResult.getAccessToken().getUserId(),loginResult.getAccessToken().getToken());

                Synergykit.linkFacebook(user, facebookAuthData, new UserResponseListener() {
                    @Override
                    public void doneCallback(int statusCode, SynergykitUser user) {
                        SynergykitLog.print(Synergykit.getGson().toJson(user));
                    }

                    @Override
                    public void errorCallback(int statusCode, SynergykitError errorObject) {
                        SynergykitLog.print(Synergykit.getGson().toJson(errorObject));
                    }
                });









            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
    private void signIn(){


        if(emailEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Email is empty. Fill it first",Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordEditText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Password is empty. Fill it first",Toast.LENGTH_SHORT).show();
            return;
        }

        //show progress dialog

        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Signing in ...");

        //create user object
        DemoUser user = new DemoUser();
        user.setEmail(emailEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        // Sign up via SynergyKit
        Synergykit.loginUser(user, new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {
                DemoUser demoUser = (DemoUser) user;
                progressDialog.dismiss();

                sharedPreferences
                        .edit()
                        .putString(USER_EMAIL_TAG, user.getEmail())
                        .putString(USER_PASSWD_TAG, passwordEditText.getText()
                                .toString())
                        .commit();

                Toast.makeText(getApplicationContext(), demoUser.getName() + " is signed in!", Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), errorObject.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    /* Add role */
    private void addRole(){

        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(),"You're not signed in",Toast.LENGTH_SHORT).show();
            return;
        }

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Adding role...");

        Synergykit.addRole(Synergykit.getLoggedUser(), "test1", new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Role was added.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), errorObject.toString(), Toast.LENGTH_SHORT).show();
            }
        }, true);

    }

    /* Add role */
    private void removeRole(){

        if(Synergykit.getLoggedUser()==null){
            Toast.makeText(getApplicationContext(),"You're not signed in",Toast.LENGTH_SHORT).show();
            return;
        }

        //show progress dialog
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(this,"Adding role...");

        Synergykit.removeRole(Synergykit.getLoggedUser(), "test1", new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Role was removed.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), errorObject.toString(), Toast.LENGTH_SHORT).show();
            }
        }, true);

    }

    /* Remove role */

    /* On click */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signInButton:
                signIn();
                break;

            case R.id.addRoleButton:
                addRole();
                break;

            case R.id.removeRoleButton:
                removeRole();
                break;
        }
    }
}
