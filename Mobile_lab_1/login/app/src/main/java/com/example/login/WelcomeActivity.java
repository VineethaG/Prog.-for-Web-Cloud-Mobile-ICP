package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
