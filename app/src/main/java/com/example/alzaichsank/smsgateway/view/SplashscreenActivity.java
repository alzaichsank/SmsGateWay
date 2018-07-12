package com.example.alzaichsank.smsgateway.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alzaichsank.smsgateway.R;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally
                {

                        Intent intent = new Intent(SplashscreenActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                }
            }
        };
        thread.start();
    }
}
