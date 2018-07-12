package com.example.alzaichsank.smsgateway.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alzaichsank.smsgateway.BuildConfig;
import com.example.alzaichsank.smsgateway.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");
        TextView about = findViewById(R.id.text_about);
        about.setText("Versi " + BuildConfig.VERSION_NAME);
    }
    public void onBackPressed() {
        finish();

    }
}
