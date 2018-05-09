package com.example.mobile_lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Index extends AppCompatActivity {

    private String emailId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        if(getIntent() != null){
            emailId = getIntent().getStringExtra("emailId");
        }
            }
    public void TexttoSpeech(View v){
        Intent ttsRedirect = new Intent(this, SpeechToText.class);
        ttsRedirect.putExtra("type","tts");
        ttsRedirect.putExtra("emailId",emailId);
        startActivity(ttsRedirect);
    }

    public void SpeechtoText(View v){
        Intent SttRedirect = new Intent(this, SpeechToText.class);
        SttRedirect.putExtra("type","stt");
        SttRedirect.putExtra("emailId",emailId);
        startActivity(SttRedirect);
    }
}
