package com.letsgood.sampleapp;


import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.plus.Plus;
import com.letsgood.sampleapp.model.DemoUser;
import com.letsgood.sampleapp.widgets.CustomProgressDialog;
import com.letsgood.synergykitsdkandroid.Synergykit;
import com.letsgood.synergykitsdkandroid.listeners.UserResponseListener;
import com.letsgood.synergykitsdkandroid.log.SynergykitLog;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitFacebookAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitGoogleAuthData;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUser;


import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import io.fabric.sdk.android.Fabric;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


/**
 * Created by Marek on 1/13/15.
 */
public class SignInActivity extends ActionBarActivity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /* Constants */
    private static final String USER_SHARED_PREF = "user-shared-pref";
    private static final String USER_PASSWD_TAG = "user-password-tag";
    private static final String USER_EMAIL_TAG = "user-email-tag";
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "RKLonAjPNotCVJHznnw6Pfy5c";
    private static final String TWITTER_SECRET = "EdbqjTzNhxhlYaJ6QL3JDVXWBTHDQw9ayZMmgeOaG9UT1ve7ji";

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;


    /* Attributes */
    private SharedPreferences sharedPreferences = null;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private LoginButton facebookButton;
    private SignInButton googleButton;
    private TwitterLoginButton twitterButton;
    private CallbackManager callbackManager; //Facebook callback manager

    /* Client used to interact with Google APIs. */
    private GoogleApiClient googleApiClient;

    /* A flag indicating that a PendingIntent is in progress and prevents
     * us from starting further intents.
     */
    private boolean googleIntentInProgress = false;

    private boolean googleButtonClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext()); //init facebook sdk
        this.initGoogleApiClient(); //init google sdk
        TwitterAuthConfig authConfig =  new TwitterAuthConfig(TWITTER_KEY,TWITTER_SECRET);

        setContentView(R.layout.activity_signin);

        Fabric.with(getApplicationContext(), new Twitter(authConfig));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);



        sharedPreferences = getSharedPreferences(USER_SHARED_PREF,MODE_PRIVATE); //get shared preferences for storing signed user

        //find views
        emailEditText =(EditText) findViewById(R.id.emailEditText);
        passwordEditText =(EditText) findViewById(R.id.passwordEditText);
        signInButton = (Button) findViewById(R.id.signInButton);
        facebookButton = (LoginButton) findViewById(R.id.facebookLoginButton);
        googleButton = (SignInButton) findViewById(R.id.googleLoginButton);
        twitterButton = (TwitterLoginButton) findViewById(R.id.twitterLoginButton);

        //set last signed user details if exists
        emailEditText.setText(sharedPreferences.getString(USER_EMAIL_TAG,""));
        passwordEditText.setText(sharedPreferences.getString(USER_PASSWD_TAG,""));

        this.printFacebookKeyHash(); //print facebook key hash
        this.registerFacebookCallback(); //register facebook callback

        //set listeners
        signInButton.setOnClickListener(this);
        facebookButton.setOnClickListener(this);
        googleButton.setOnClickListener(this);
        initTwitterCallback();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data); // facebook callback


        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                googleButtonClicked = false;
            }

            googleIntentInProgress = false;

            if (!googleApiClient.isConnected()) {
                googleApiClient.reconnect();
            }
        }

        twitterButton.onActivityResult(requestCode, resultCode,
                data);
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



    /* On click */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signInButton:
                signIn();
                break;

            case R.id.googleLoginButton:
                if(!googleApiClient.isConnecting()){
                   googleButtonClicked = true;
                    googleApiClient.connect();
                }
                break;
            case R.id.facebookLoginButton:
                if(AccessToken.getCurrentAccessToken()!=null){
                    signInViaFacebook(AccessToken.getCurrentAccessToken());
                }else{
                    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                }
                break;

            case R.id.twitterLoginButton:
                initTwitterCallback();
                break;
        }
    }




    /* Print Facebook key hash */
    private void printFacebookKeyHash(){
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
    }

    /* Sign in via Facebook */
    private void signInViaFacebook(final AccessToken accessToken) {
        // App code
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                        DemoUser demoUser = null;

                        //show loading progress bar
                        final CustomProgressDialog progressDialog = new CustomProgressDialog(SignInActivity.this, "Signing in ...");

                        SynergykitFacebookAuthData synergykitFacebookAuthData = new SynergykitFacebookAuthData(accessToken.getUserId(), accessToken.getToken());

                        if (Synergykit.getLoggedUser() == null) {
                            demoUser = new DemoUser();
                            try {
                                demoUser.setEmail(object.getString("email"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            demoUser = (DemoUser) Synergykit.getLoggedUser();
                        }

                        //set user name if not exists
                        if(demoUser.getName()==null || demoUser.getName().isEmpty()){
                            if(Profile.getCurrentProfile()!=null && Profile.getCurrentProfile().getFirstName()!=null)
                                demoUser.setName(Profile.getCurrentProfile().getFirstName());

                            if(Profile.getCurrentProfile()!=null && Profile.getCurrentProfile().getLastName()!=null){
                                if(demoUser.getName()!=null && !demoUser.getName().isEmpty()){
                                    demoUser.setName(demoUser.getName() + " " + Profile.getCurrentProfile().getLastName());
                                }else{
                                    demoUser.setName(Profile.getCurrentProfile().getLastName());
                                }
                            }
                        }



                        LoginManager.getInstance().logOut();
                        // Sign up via SynergyKit
                        Synergykit.linkFacebook(demoUser, synergykitFacebookAuthData, new UserResponseListener() {
                            @Override
                            public void doneCallback(int statusCode, SynergykitUser user) {
                                DemoUser demoUser = (DemoUser) user;
                                String toastText = new String();
                                progressDialog.dismiss();

                                Toast.makeText(getApplicationContext(), "You are signed in via Facebook!", Toast.LENGTH_SHORT).show();
                                finish();

                            }

                            @Override
                            public void errorCallback(int statusCode, SynergykitError errorObject) {
                                LoginManager.getInstance().logOut();
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), errorObject.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields","email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    /* Register Facebook callback */
    private void registerFacebookCallback(){
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                signInViaFacebook(loginResult.getAccessToken());

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
    /* Google API client initialization */
    private void initGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    /* On stop */
    protected void onStop() {
        super.onStop();

        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    /* Sign in via Facebook */
    private void signInViaGoogle(){

        DemoUser demoUser = null;

        //show loading progress bar
        final CustomProgressDialog progressDialog =  new CustomProgressDialog(SignInActivity.this,"Signing in ...");

        SynergykitGoogleAuthData synergykitGoogleAuthData = new SynergykitGoogleAuthData();
        synergykitGoogleAuthData.setId(Plus.PeopleApi.getCurrentPerson(googleApiClient).getId());

        if(Synergykit.getLoggedUser()==null) {
            demoUser = new DemoUser();
        }else {
            demoUser = (DemoUser) Synergykit.getLoggedUser();
        }

        demoUser.setName(Plus.PeopleApi.getCurrentPerson(googleApiClient).getName().getFormatted());


        // Sign up via SynergyKit
        Synergykit.linkGoogle(demoUser, synergykitGoogleAuthData, new UserResponseListener() {
            @Override
            public void doneCallback(int statusCode, SynergykitUser user) {
                DemoUser demoUser = (DemoUser) user;
                String toastText = new String();
                progressDialog.dismiss();

                Toast.makeText(getApplicationContext(), "You are signed in via Facebook!", Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void errorCallback(int statusCode, SynergykitError errorObject) {
                LoginManager.getInstance().logOut();
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), errorObject.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Google onConnected state */
    @Override
    public void onConnected(Bundle bundle) {
       googleButtonClicked = false;

    }

    /* Google onConnectionSuspended state */
    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    /* Google onConnection failed state */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!googleIntentInProgress) {
            if (googleButtonClicked && connectionResult.hasResolution()) {
                // The user has already clicked 'sign-in' so we attempt to resolve all
                // errors until the user is signed in, or they cancel.
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    googleIntentInProgress = true;
                } catch (IntentSender.SendIntentException e) {
                    // The intent was canceled before it was sent.  Return to the default
                    // state and attempt to connect to get an updated ConnectionResult.
                    googleIntentInProgress = false;
                    googleApiClient.connect();
                }
            }
        }
    }



    private void initTwitterCallback(){
        twitterButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                SynergykitLog.print("Logged");
            }

            @Override
            public void failure(TwitterException e) {
                SynergykitLog.print(e.toString());
            }
        });
    }
}
