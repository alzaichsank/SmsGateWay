package com.example.alzaichsank.smsgateway.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alzaichsank.smsgateway.R;

public class DetailOutboxActivity extends AppCompatActivity {
    public String mId,mNo, mMessage, mTime;
    TextView no,message,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_outbox);
        no=findViewById(R.id.nama_report);
        message=findViewById(R.id.news_detail);
        time=findViewById(R.id.name_time);

        mId = getIntent().getStringExtra("extaraid");
        mNo = getIntent().getStringExtra("extaraname");
        mMessage = getIntent().getStringExtra("extaratelepone");
        mTime = getIntent().getStringExtra("extaradate");
        no.setText("No : "+mNo);
        message.setText(mMessage);
        time.setText("Time : "+mTime);
    }
}
