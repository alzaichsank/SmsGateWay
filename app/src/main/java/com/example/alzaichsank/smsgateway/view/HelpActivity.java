package com.example.alzaichsank.smsgateway.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.alzaichsank.smsgateway.R;

public class HelpActivity extends AppCompatActivity {
    LinearLayout help1, help2, answer1, answer2 ;
    Boolean h1 = false;
    Boolean h2 = false;
    ImageView h1Image, h2Image, h3Image, h4Image, h5Image, h6Image, h7Image, h8Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle(R.string.app_name);

        //h1
        help1 = (LinearLayout) findViewById(R.id.help1);
        answer1 = (LinearLayout) findViewById(R.id.answer1);
        h1Image = (ImageView) findViewById(R.id.a1);
        //h2
        help2 = (LinearLayout) findViewById(R.id.help2);
        answer2 = (LinearLayout) findViewById(R.id.answer2);
        h2Image = (ImageView) findViewById(R.id.a2);

        setPopularItems();
    }

    private void setPopularItems() {
        help1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (h1 == true) {
                    Answer1();
                } else {
                    answer1.setVisibility(View.VISIBLE);
                    h1Image.setImageResource(R.drawable.expand_down_w);
                    h1 = true;
                }
            }
        });
        help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (h2 == true) {
                    Answer2();
                } else {
                    answer2.setVisibility(View.VISIBLE);
                    h2Image.setImageResource(R.drawable.expand_down_w);
                    h2 = true;
                }
            }
        });

    }

    public void Answer1() {
        answer1.setVisibility(View.GONE);
        h1Image.setImageResource(R.drawable.expand_up_w);
        h1 = false;
    }
    public void Answer2() {
        answer2.setVisibility(View.GONE);
        h2Image.setImageResource(R.drawable.expand_up_w);
        h2 = false;
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
