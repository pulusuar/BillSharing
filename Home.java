package com.avinash.billsharingapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by AVINASH on 25-07-2017.
 */

public class Home extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Toast.makeText(getApplicationContext(),"Home Page",Toast.LENGTH_LONG).show();
    }
}
