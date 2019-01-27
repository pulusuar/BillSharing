package com.avinash.billsharingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by AVINASH on 23-07-2017.
 */

public class Login extends Activity {

    LoginDBAdapter db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        final EditText et1=(EditText)findViewById(R.id.userName);
        final EditText et2=(EditText) findViewById(R.id.password);

        final Button login=(Button) findViewById(R.id.loginBtn);
        final Button signup=(Button) findViewById(R.id.signupBtn);
        final Button forgot=(Button) findViewById(R.id.forgotBtn);

        db=new LoginDBAdapter(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Username given by user
                String uname=et1.getText().toString();
                //password entered
                String password=et2.getText().toString();
                //To check whether the username and password is present in the database
                if(db.checkValid(uname,password)) {
                    //If details are correct, navigate to home activity
                    Intent i = new Intent(Login.this, home_main.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Logged in!!", Toast.LENGTH_LONG).show();
                }
                //If details are invalid
                else{
                    et1.setText("");
                    et2.setText("");
                    Toast.makeText(getApplicationContext(),"Invalid Details!!",Toast.LENGTH_LONG).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to signup activity on button click
                Intent j=new Intent(Login.this,SignUp.class);
                startActivity(j);
                Toast.makeText(getApplicationContext(),"Signup",Toast.LENGTH_LONG).show();
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Forgot Password??",Toast.LENGTH_LONG).show();
            }
        });
    }
}
