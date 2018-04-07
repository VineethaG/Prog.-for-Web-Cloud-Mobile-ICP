package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void Register(View v) {

        EditText  UserName_ctrl= (EditText)findViewById(R.id.username);
        EditText  FirstName_ctrl= (EditText)findViewById(R.id.firstName);
        EditText  LastName_ctrl= (EditText)findViewById(R.id.lastName);
        EditText passwordCtrl = (EditText) findViewById(R.id.password);
        EditText confirm_password_ctrl = (EditText)findViewById(R.id.confirm_password);
        TextView errorText = (TextView)findViewById(R.id.error);
        String UserName =UserName_ctrl.getText().toString();
        String FirstName = FirstName_ctrl.getText().toString();
        String LastName = LastName_ctrl.getText().toString();
        String password = passwordCtrl.getText().toString();
        String confirm_password = confirm_password_ctrl.getText().toString();
        String error = errorText.getText().toString();
        boolean validationFlag = false;
        if (!UserName.isEmpty() && !FirstName.isEmpty() && !LastName.isEmpty()&& !password.isEmpty()&& !confirm_password.isEmpty() ) {
            if (password.equals(confirm_password)) {
                validationFlag = true;
            }
        }
        if (!validationFlag) {
            errorText.setVisibility(View.VISIBLE);
        } else {

            Intent redirect = new Intent(Register.this, Index.class);
            startActivity(redirect);
        }
    }
    public void resetfields(View view) {
        EditText  UserName_ctrl= (EditText)findViewById(R.id.username);
        EditText  FirstName_ctrl= (EditText)findViewById(R.id.firstName);
        EditText  LastName_ctrl= (EditText)findViewById(R.id.lastName);
        EditText passwordCtrl = (EditText) findViewById(R.id.password);
        EditText confirm_password_ctrl = (EditText)findViewById(R.id.confirm_password);
        TextView errorText = (TextView)findViewById(R.id.error);

        UserName_ctrl.setText("");
       FirstName_ctrl.setText("");
        LastName_ctrl.setText("");
        passwordCtrl.setText("");
        confirm_password_ctrl.setText("");
        errorText.setVisibility(View.INVISIBLE); }

}
