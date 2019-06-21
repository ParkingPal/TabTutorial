package com.example.tabtutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tabtutorial.ui.main.BLActivity;

import java.util.Timer;
import java.util.TimerTask;

public class CustomerDisplay extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_display);

        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                Intent intent = new Intent(CustomerDisplay.this, BLActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
