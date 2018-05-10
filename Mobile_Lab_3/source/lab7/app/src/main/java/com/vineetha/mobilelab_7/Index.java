package com.vineetha.mobilelab_7;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Index extends AppCompatActivity {

    private String emailId;
    DatabaseReference databaseReference ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        if(getIntent() != null){
            emailId = getIntent().getStringExtra("emailId");
        }
    }

    public void rating(View v) {
        // get values and then displayed in a toast
        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        String rating = "Rating :: " + simpleRatingBar.getRating();
        String totalStars = "Total Stars:: " + simpleRatingBar.getNumStars();
        Toast.makeText(getApplicationContext(), totalStars + "\n" + rating, Toast.LENGTH_LONG).show();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Rating");
        databaseReference.setValue(rating);

    }
    public void TexttoSpeech(View v){
        Intent ttsRedirect = new Intent(this, TexttoSpeech.class);
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
