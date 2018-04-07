package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.concurrent.Callable;


public class MainActivity extends AppCompatActivity {
    TextView loginMessage;
    public CallbackManager mFacebookCallbackManager;
     LoginButton mFacebookSignInButton;
    String fbUsername = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mFacebookCallbackManager = CallbackManager.Factory.create();
        mFacebookSignInButton = (LoginButton)findViewById(R.id.facebook_sign_in_button);
        loginMessage = findViewById(R.id.textView);

        mFacebookSignInButton.registerCallback(mFacebookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        getFbUserName(loginResult.getAccessToken());

                    }

                    @Override
                    public void onCancel() {
                        loginMessage.setText("Facebook Login Cancelled, Try again");
                        loginMessage.setVisibility(View.VISIBLE);                    }

                    @Override
                    public void onError(FacebookException error) {
                        loginMessage.setText("Error occured while signing in");
                        loginMessage.setVisibility(View.VISIBLE);
                    }
                }
        );
    }
    private void getFbUserName(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                        Log.v("Main", response.toString());
                        if(jsonObject != null){
                            try {
                                fbUsername = jsonObject.getString("name");
                                // Redirecting to Home Activity
                                Intent homeActivityRedirect = new Intent(MainActivity.this,HomeActivity.class);
                                // Passing Username to Homeactivity
                                homeActivityRedirect.putExtra("emailId",fbUsername);
                                startActivity(homeActivityRedirect);

                            }catch (Exception e){
                                // App code
                                loginMessage.setText("Error occured while signing in and getting profile name : "+e.getMessage());
                                loginMessage.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                            }
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }
    public void checkCredentials(View v) {
        EditText usernameCtrl = (EditText) findViewById(R.id.loginusername);
        EditText passwordCtrl = (EditText)findViewById(R.id.loginpassword);
        TextView errorText = (TextView)findViewById(R.id.textView);
        String userName = usernameCtrl.getText().toString();
        String password = passwordCtrl.getText().toString();

        boolean validationFlag = false;
        if (!userName.isEmpty() && !password.isEmpty()) {
            if (userName.equals("manual@gmail.com") && password.equals("admin")) {
                validationFlag = true;
            }
        }
        if (!validationFlag) {
            errorText.setVisibility(View.VISIBLE);
        } else {

            Intent redirect = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(redirect);
        }
    }
    public void email(View v) {

      //
// Write the relevant code for making the buttons work(i.e impelement the implicit and explicit intents
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"vineetha.gummadi@gmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Password");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your Password: 'admin");
        startActivity(Intent.createChooser(emailIntent, "Send your email in:"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}
