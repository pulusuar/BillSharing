package com.avinash.billsharingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.avinash.billsharingapp.R.string.signup;

/**
 * Created by AVINASH on 25-07-2017.
 */

public class SignUp extends Activity {


    LoginDBAdapter db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        final Button signup=(Button) findViewById(R.id.signUpButton);
        final EditText name=(EditText) findViewById(R.id.name);
        final EditText username=(EditText) findViewById(R.id.user);
        final EditText password=(EditText) findViewById(R.id.pwd);
        final EditText conpwd=(EditText) findViewById(R.id.conpwd);
        final EditText mobile=(EditText) findViewById(R.id.mobileNo);

        // Opens Database
        db=new LoginDBAdapter(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Name of user
                String s1=name.getText().toString();
                //Username
                String s2=username.getText().toString();
                //password
                String s3=password.getText().toString();
                //Confirm Password
                String s4=conpwd.getText().toString();
                //Mobile number
                String s5=mobile.getText().toString();

                //Check whether all fields are filled or not
                if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
                    Toast.makeText(getApplicationContext(),"Fields are vacant",Toast.LENGTH_LONG).show();
                    return;
                }

                //Checks Whether the passwords match or not
                else if(!s3.equals(s4)) {
                    Toast.makeText(getApplicationContext(),"Passwords doesn't match",Toast.LENGTH_LONG).show();
                    password.setText("");
                    conpwd.setText("");
                    return;
                }

                //Checks Whether the user already exists or not
                else {
                    //If the user does not exist
                    if(!db.checkUser(s2)) {
                        db.open();
                        db.insert_details(s1, s2, s3, s5);
                        db.close();
                        Toast.makeText(getApplicationContext(), "Signed Up", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(SignUp.this,Login.class);
                        startActivity(i);
                    }

                    //If the user already exist
                    else{
                        Toast.makeText(getApplicationContext(),"Username already exists",Toast.LENGTH_LONG).show();
                        username.setText("");
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        db.close();
        Intent i=new Intent(SignUp.this,Login.class);
        startActivity(i);
    }
}
