package com.example.alzaichsank.smsgateway.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alzaichsank.smsgateway.R;
import com.example.alzaichsank.smsgateway.interface_file.ApiService;
import com.example.alzaichsank.smsgateway.model.BasicResponse;
import com.example.alzaichsank.smsgateway.system.rest.rest_url;
import com.example.alzaichsank.smsgateway.system.retrofit.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FromActivity extends AppCompatActivity {

    public String mId,mName, mContact, mKeyword;
    EditText nameEdit,phoneEdit;
    Button addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);
        nameEdit=findViewById(R.id.nameTxt);
        phoneEdit=findViewById(R.id.nohpTxt);
        addContact=findViewById(R.id.btnAddContact);


        addContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String contactData = phoneEdit.getText().toString();
                String id = "";
                String nameData = nameEdit.getText().toString();
                if(mKeyword.equals("edit")){
                    id = mId;
                }else{
                    id = "";
                }

                insertContact(id,nameData,contactData);


            }
        });
        mKeyword =getIntent().getStringExtra("mode");
        if(mKeyword.equals("edit")){
            mId = getIntent().getStringExtra("extaraid");
            mName = getIntent().getStringExtra("extaraname");
            mContact = getIntent().getStringExtra("extaratelepone");

            nameEdit.setText(mName);
            phoneEdit.setText(mContact);
        }

    }
    public void insertContact(final String id, final String name,final String nohp){

        ApiService api2 = APIClient.getClient2().create(ApiService.class);
        if(!mKeyword.equals("edit")) {
            Call<BasicResponse> getListData = api2.postContact(name, nohp);

            getListData.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call,
                                       Response<BasicResponse> response) {
                    try {
                        Log.e(rest_url.TAG, response.body().toString());
                        if (response.isSuccessful()) {
                            String responseMessage = response.body().getMessage();
                            Toast.makeText(FromActivity.this, responseMessage, Toast.LENGTH_SHORT).show();
                            nameEdit.setText("");
                            phoneEdit.setText("");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    //Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(FromActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Call<BasicResponse> getListData = api2.updateContact(id,name, nohp);

            getListData.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call,
                                       Response<BasicResponse> response) {
                    try {
                        Log.e(rest_url.TAG, response.body().toString());
                        if (response.isSuccessful()) {
                            String responseMessage = response.body().getMessage();
                            Toast.makeText(FromActivity.this, responseMessage, Toast.LENGTH_SHORT).show();
                            nameEdit.setText("");
                            phoneEdit.setText("");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    //Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(FromActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void onBackPressed() {
        if (mKeyword.equals("edit")){
            Intent intent = new Intent(FromActivity.this, ContactListActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("mode", "edit");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(FromActivity.this, ContactActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
