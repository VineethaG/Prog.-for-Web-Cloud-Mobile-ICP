package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
    public void HomePage(View view){

        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
    public void Settings(View view){

        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
    public void Help(View view){

        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
