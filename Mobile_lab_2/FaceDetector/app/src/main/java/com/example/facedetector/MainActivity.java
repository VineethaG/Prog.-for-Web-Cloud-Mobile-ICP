package com.example.facedetector;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.android.library.camera.CameraHelper;

import org.json.JSONObject;

import java.io.File;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // To take Pictures we gonna use CameraHelper
    CameraHelper cameraHelper;

    TextView ageView;
    TextView emotionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraHelper = new CameraHelper(this);
        ageView = findViewById(R.id.age);
        emotionView = findViewById(R.id.emotion);
    }

    // On Click of Take Picture...
    public void takePicture(View view) {
        // To take Picture..
        cameraHelper.dispatchTakePictureIntent();
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CameraHelper.REQUEST_IMAGE_CAPTURE) {
            // Extracting Picture in form of BitMap Object
            final Bitmap bitmap = cameraHelper.getBitmap(resultCode);

            // Setting the Preview
            ImageView imageView = findViewById(R.id.imagepreview);
            imageView.setImageBitmap(bitmap);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL("http://api.kairos.com/detect");
                        HttpURLConnection client = (HttpURLConnection) url.openConnection();

                        Map<String,String> headers = new HashMap<>();
                        headers.put("Content-Type","application/json");
                        headers.put("app_id","f6e6154f");
                        headers.put("app_key","9190b138edea6dec064c7d53481769f0");

                        Map<String,Object> body = new HashMap<>();
                        body.put("image",bitmap);
                        body.put("selector","ROLL");

                        OutputStreamWriter wr = new OutputStreamWriter(client.getOutputStream());
                        wr.write(body.toString());
                        // Flushing the data
                        wr.flush();

                        // Fetching Data
                        JSONObject jsonObject = new JSONObject();
                        ageView.setText("Age : "+jsonObject.getString("age"));
                        emotionView.setText("Emotion : "+jsonObject.getString("emotion"));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
