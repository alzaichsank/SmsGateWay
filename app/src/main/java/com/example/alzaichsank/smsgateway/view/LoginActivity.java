package com.example.alzaichsank.smsgateway.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alzaichsank.smsgateway.R;

public class LoginActivity extends AppCompatActivity {
    RelativeLayout activity_main;
    SharedPreferences sharedpreferences;
    EditText userpass;
    EditText passkey;
    private Button btnSend;
    public static final String mypreference = "mypref";
    public static final String userZenziva = "userkey";
    public static final String passZenziva = "passkey";
    //exit

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activity_main= findViewById(R.id.loginlayout);
        userpass = findViewById(R.id.editTextUser);
        passkey = findViewById(R.id.editTextPassword);
        btnSend = findViewById(R.id.btLogin);
        sharedpreferences = getSharedPreferences(mypreference,Context.MODE_PRIVATE);
//        if (sharedpreferences.contains(userZenziva)) {
//            userpass.setText(sharedpreferences.getString(userpass.getText().toString(), ""));
//        }
//        if (sharedpreferences.contains(passZenziva)) {
//            passkey.setText(sharedpreferences.getString(passkey.getText().toString(), ""));
//
//        }


        //lets go
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(userpass.getText().toString().isEmpty()){
                    Snackbar.make(activity_main, "user is empty", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else if(passkey.getText().toString().isEmpty()){
                    Snackbar.make(activity_main, "pass is empty", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    Save(userpass.getText().toString(),passkey.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, DrawerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    //save preference
    public void Save(String userpass,String passkey) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(userZenziva,userpass);
        editor.putString(passZenziva,passkey);
        editor.commit();
    }


    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan tombol 'kembali' untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }
}
