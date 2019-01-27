package com.avinash.billsharingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //to navigate to nextpage after 2000 milliseconds
       new Timer().schedule(new TimerTask() {
           @Override
           public void run() {
               startActivity(new Intent(MainActivity.this,Login.class));
           }
       },2000);
    }
}
