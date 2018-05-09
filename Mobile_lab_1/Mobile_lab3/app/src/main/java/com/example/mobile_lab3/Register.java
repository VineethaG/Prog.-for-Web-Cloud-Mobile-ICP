package com.example.mobile_lab3;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth= FirebaseAuth.getInstance();
    }
    public void Register(View v) {

        EditText  UserName_ctrl= (EditText)findViewById(R.id.username);
        EditText  FirstName_ctrl= (EditText)findViewById(R.id.firstName);
        EditText  LastName_ctrl= (EditText)findViewById(R.id.lastName);
        EditText passwordCtrl = (EditText) findViewById(R.id.password);
        EditText confirm_password_ctrl = (EditText)findViewById(R.id.confirm_password);
        final TextView msg = (TextView)findViewById(R.id.error);
        String UserName =UserName_ctrl.getText().toString();
        final String FirstName = FirstName_ctrl.getText().toString();
        String LastName = LastName_ctrl.getText().toString();
        String password = passwordCtrl.getText().toString();
        String confirm_password = confirm_password_ctrl.getText().toString();
        if (!(isFieldNotNull(UserName_ctrl)
                && isFieldNotNull(FirstName_ctrl)
                && isFieldNotNull(LastName_ctrl)
                && isFieldNotNull(passwordCtrl))) {
            msg.setText("Please fill all the fields,Fields cannot be empty");
            msg.setVisibility(View.VISIBLE);

        }else if (!password.equals(confirm_password)) {
            msg.setText("Password & Confirm passwords should match");
            msg.setVisibility(View.VISIBLE);
        }else if(password.length() < 6){
            msg.setText("Password Length should be more than 5");
            msg.setVisibility(View.VISIBLE);
        } else {

            // Inserting into Firebase Database
            firebaseAuth.createUserWithEmailAndPassword(UserName,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_SHORT).show();

                        // Setting Successful Message
                        msg.setText("You '" + FirstName + "' have successfully Registered, LOGIN to continue");
                        msg.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(Register.this,"User already exists",Toast.LENGTH_SHORT).show();

                        // Setting UnSuccessful Message
                        msg.setText("User already exists, Please Login to continue");
                        msg.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    private boolean isFieldNotNull(EditText fieldValue) {
        if (fieldValue != null && StringUtils.isNotBlank(fieldValue.getText().toString())) {
            return true;
        }
        return false;
    }
    public void resetfields(View view) {
        EditText  UserName_ctrl= (EditText)findViewById(R.id.username);
        EditText  FirstName_ctrl= (EditText)findViewById(R.id.firstName);
        EditText  LastName_ctrl= (EditText)findViewById(R.id.lastName);
        EditText passwordCtrl = (EditText) findViewById(R.id.password);
        EditText confirm_password_ctrl = (EditText)findViewById(R.id.confirm_password);
        TextView msg = (TextView)findViewById(R.id.error);

        UserName_ctrl.setText("");
        FirstName_ctrl.setText("");
        LastName_ctrl.setText("");
        passwordCtrl.setText("");
        confirm_password_ctrl.setText("");
        msg.setVisibility(View.INVISIBLE); }
}
