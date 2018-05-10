package com.vineetha.mobilelab_7;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
    public void Register(View v) {
        Intent redirect = new Intent(WelcomeActivity.this, Register.class);
        startActivity(redirect);

    }
    public void signIn(View v) {
        Intent redirect = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(redirect);

    }

}

