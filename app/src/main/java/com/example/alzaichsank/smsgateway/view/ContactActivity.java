package com.example.alzaichsank.smsgateway.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.alzaichsank.smsgateway.R;

public class ContactActivity extends AppCompatActivity {
    LinearLayout tambah,listContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatc);


        listContact= findViewById(R.id.categorychat1);
        tambah= findViewById(R.id.categorychat2);

        listContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    Intent intent = new Intent(ContactActivity.this, ContactListActivity.class);
                    startActivity(intent);
                    finish();
                }

        });

        tambah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this, FromActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("mode", "tambah");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }

        });
    }
}
