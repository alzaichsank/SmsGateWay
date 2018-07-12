package com.example.alzaichsank.smsgateway.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.alzaichsank.smsgateway.R;
import com.example.alzaichsank.smsgateway.adapter.ContactListAdapter;
import com.example.alzaichsank.smsgateway.interface_file.ApiService;
import com.example.alzaichsank.smsgateway.model.contact_list.ContactListData;
import com.example.alzaichsank.smsgateway.model.contact_list.ContactListResponse;
import com.example.alzaichsank.smsgateway.system.rest.rest_url;
import com.example.alzaichsank.smsgateway.system.retrofit.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListActivity extends AppCompatActivity {

    //Init variabel
    private RecyclerView recyclerView;
    private ContactListAdapter adapter;
    ApiService apiService;
    private List<ContactListData> contactListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_contact_list);

        //Binding recycler view
        recyclerView = this.findViewById(R.id.rv_contact_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getListContact();
    }

    public void getListContact(){
        ApiService api = APIClient.getClient2().create(ApiService.class);
        Call<ContactListResponse> getListData = api.getListContact();
        getListData.enqueue(new Callback<ContactListResponse>()
        {
            @Override
            public void onResponse(Call<ContactListResponse> call,
                                   Response<ContactListResponse> response)
            {
                try {
                Log.e(rest_url.TAG, response.body().toString());
                if(response.isSuccessful())
                {

                    assert response.body() != null;
                    contactListData = response.body().getContactListData();

                    adapter = new ContactListAdapter(ContactListActivity.this, contactListData);
                    recyclerView.setAdapter(adapter);
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<ContactListResponse> call, Throwable t)
            {
                Toast.makeText(ContactListActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(ContactListActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {

            Intent intent = new Intent(ContactListActivity.this, ContactActivity.class);
            startActivity(intent);
            finish();

    }
}
