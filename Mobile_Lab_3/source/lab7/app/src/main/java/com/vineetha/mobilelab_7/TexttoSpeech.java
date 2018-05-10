package com.vineetha.mobilelab_7;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.view.View.VISIBLE;


public class TexttoSpeech extends AppCompatActivity {
    EditText ed1;
    Button b1;
    TextToSpeech t1;
    private SQLiteDB sqLiteDB;
    private String type;
    private String emailId;
    private String existingInfo;
    private ImageButton speaker;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        ed1 = (EditText) findViewById(R.id.input);
        b1 = (Button) findViewById(R.id.saveText);
        speaker = (ImageButton) findViewById(R.id.btnSpeak);


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }

    public void saveText(View v) {
        if(getIntent() != null){
        type = getIntent().getStringExtra("type");
        emailId = getIntent().getStringExtra("emailId");
    }
        String toSpeak = ed1.getText().toString();
        sqLiteDB = new SQLiteDB(this);
        if(existingInfo != null){
            speaker.setVisibility(VISIBLE);
            boolean update = sqLiteDB.updateData(emailId,type,toSpeak);
        }else{
            boolean insert = sqLiteDB.insertData(emailId,type,toSpeak);
            speaker.setVisibility(VISIBLE);

        }
    }

    public void listen(View v) {
        String toSpeak = ed1.getText().toString();
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
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        }
    }
}