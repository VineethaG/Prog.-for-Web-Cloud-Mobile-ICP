package com.example.mobile_lab3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.commons.lang3.StringUtils;


public class MainActivity extends AppCompatActivity {
    public static Object emailId;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void Register(View v) {
        Intent RegisterRedirect = new Intent(MainActivity.this, Register.class);
        startActivity(RegisterRedirect);
    }

        public void checkCredentials(View v) {
        final EditText usernameCtrl = (EditText) findViewById(R.id.loginusername);
        EditText passwordCtrl = (EditText) findViewById(R.id.loginpassword);
        final TextView errorText = (TextView) findViewById(R.id.textView);
        final String userName = usernameCtrl.getText().toString();
        String password = passwordCtrl.getText().toString();
        if(!(isFieldNotNull(usernameCtrl) && isFieldNotNull(passwordCtrl))){
            // Throwing Error if any of the field is empty
            errorText.setText("Email Address or Password fields cannot be Empty !!");
            errorText.setVisibility(View.VISIBLE);
        }else{

            firebaseAuth.signInWithEmailAndPassword(usernameCtrl.getText().toString(),passwordCtrl.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                Intent homeActivityRedirect = new Intent(MainActivity.this, Index.class);
                                // Adding Email Id to Intent to use that in HomeActivity as a Greeting...
                                homeActivityRedirect.putExtra("emailId",userName);
                                startActivity(homeActivityRedirect);
                            }else{
                                Toast.makeText(MainActivity.this,"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                                errorText.setText("Invalid Login,Please Register if you haven't registered yet !!");
                                errorText.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
    }

    // Common Method to check for NULL
    private boolean isFieldNotNull(EditText fieldValue){
        if (fieldValue != null && StringUtils.isNotBlank(fieldValue.getText().toString())){
            return true;
        }
        return false;
    }
}







