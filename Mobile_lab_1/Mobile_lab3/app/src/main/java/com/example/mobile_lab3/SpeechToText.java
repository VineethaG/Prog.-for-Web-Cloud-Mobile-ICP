package com.example.mobile_lab3;



import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToText extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    TextToSpeech mTts;
    private SQLiteDB sqLiteDB;

    private String type;
    private String emailId;
    private String existingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        sqLiteDB = new SQLiteDB(this);

        if(getIntent() != null){
            type = getIntent().getStringExtra("type");
            emailId = getIntent().getStringExtra("emailId");
        }
        // Check if emailId,type already exist
        Cursor cursor = sqLiteDB.checkIfTypeExistForUser(emailId,type);
        if(cursor.getCount() == 0){
            existingInfo = null;
        }else{
            while(cursor.moveToNext()) {
                existingInfo = cursor.getString(3);
                String speechText = "<b>Your Existing Response : </b>"+cursor.getString(3);
                mVoiceInputTv.setText(Html.fromHtml(speechText));
                mVoiceInputTv.setVisibility(View.VISIBLE);
            }
        }
    }
    public void speak(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
                a.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // Save result.get(0) into Sqlite database using EmailId, Type, Text
                    System.out.println("existingInfo :: "+existingInfo);
                    if(existingInfo != null){
                        boolean update = sqLiteDB.updateData(emailId,type,result.get(0));
                    }else{
                        boolean insert = sqLiteDB.insertData(emailId,type,result.get(0));
                    }
                    String speechText = "<b>Your Response : </b>"+result.get(0);
                    mVoiceInputTv.setText(Html.fromHtml(speechText));
                    mVoiceInputTv.setVisibility(View.VISIBLE);
                }
                break;
            }
        }


    }
    }







