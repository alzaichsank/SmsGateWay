package com.example.alzaichsank.smsgateway.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.alzaichsank.smsgateway.R;
import com.example.alzaichsank.smsgateway.adapter.OutboxListAdapter;
import com.example.alzaichsank.smsgateway.interface_file.ApiService;
import com.example.alzaichsank.smsgateway.model.outbox.OutboxResponse;
import com.example.alzaichsank.smsgateway.model.outbox.OutboxResponse;
import com.example.alzaichsank.smsgateway.model.outbox.Outboxdata;
import com.example.alzaichsank.smsgateway.system.rest.rest_url;
import com.example.alzaichsank.smsgateway.system.retrofit.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutboxActivity extends AppCompatActivity {

    //Init variabel
    private RecyclerView recyclerView;
    private OutboxListAdapter adapter;
    ApiService apiService;
    private List<Outboxdata> outboxListData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outbox);
        //Binding recycler view
        recyclerView = this.findViewById(R.id.rv_outbox);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getListContact();
    }
    public void getListContact(){
        ApiService api = APIClient.getClient2().create(ApiService.class);
        Call<OutboxResponse> getListData = api.getListOutbox();
        getListData.enqueue(new Callback<OutboxResponse>()
        {
            @Override
            public void onResponse(Call<OutboxResponse> call,
                                   Response<OutboxResponse> response)
            {
                try {
                    Log.e(rest_url.TAG, response.body().toString());
                    if(response.isSuccessful())
                    {

                        assert response.body() != null;
                        outboxListData = response.body().getContactListData();

                        adapter = new OutboxListAdapter(OutboxActivity.this, outboxListData);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<OutboxResponse> call, Throwable t)
            {
                Toast.makeText(OutboxActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(ContactListActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
