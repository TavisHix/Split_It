package com.example.split_it;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome extends AppCompatActivity {

    private static int waitTime = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //
        // Opening a new instance(page) after a set amount of time
        //
        new Handler().postDelayed(new Runnable(){

            public void run(){
                Intent i = new Intent(Welcome.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }, waitTime);

    }
}
