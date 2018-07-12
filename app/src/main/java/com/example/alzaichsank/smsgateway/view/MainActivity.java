package com.example.alzaichsank.smsgateway.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alzaichsank.smsgateway.R;
import com.example.alzaichsank.smsgateway.model.BasicResponse;
import com.example.alzaichsank.smsgateway.model.MessageData;
import com.example.alzaichsank.smsgateway.model.Pesan;
import com.example.alzaichsank.smsgateway.model.contact_list.ContactListData;
import com.example.alzaichsank.smsgateway.model.contact_list.ContactListResponse;
import com.example.alzaichsank.smsgateway.system.rest.rest_url;
import com.example.alzaichsank.smsgateway.system.retrofit.APIClient;
import com.example.alzaichsank.smsgateway.interface_file.ApiService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {
    //progress dialog
    private ProgressDialog loading;
    private EditText editTextTo, editTextMessage;
    private ListView listcontact;
    private TextView sendToWhere;
    RelativeLayout activity_main;
    private Button btnSend;

    private Button btnAdd,btnAddAll,btnClear;
    //caching

    SharedPreferences sharedpreferences;
    String userkey = "";
    String passkey = "";

    // xml parser
    private List<Pesan> responeseMessage;

    //retrofit
    ApiService mApiInterface;

    //list contact
    /**
     * Items entered by the user is stored in this ArrayList variable
     */
    ArrayList<String> list = new ArrayList<String>();

    /**
     * Declaring an ArrayAdapter to set items to ListView
     */
    ArrayAdapter<String> adapter;
    // spinner db
    Spinner SpinneContact;
    private List<ContactListData> contactListData;
    private String[] idContact,nameContact,phoneContact;
    Boolean getFirst = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// declaration
        setContentView(R.layout.activity_main);
        activity_main = findViewById(R.id.fromMessage);
        editTextTo = findViewById(R.id.nohpTxt);
        editTextMessage = findViewById(R.id.messageTxt);
        sendToWhere = findViewById(R.id.sendTo);

        listcontact = findViewById(R.id.listContact);
        btnSend = findViewById(R.id.btnAdd);
        btnAdd = findViewById(R.id.btnAddSendto);
        btnAddAll = findViewById(R.id.btnAddAllFromDb);
        btnClear = findViewById(R.id.btnAddClearAll);
        SpinneContact = findViewById(R.id.spinner);


        //get cahcing data
        sharedpreferences = getSharedPreferences("mypref",
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains("userkey")) {
            userkey = (sharedpreferences.getString("userkey", ""));

        }
        if (sharedpreferences.contains("passkey")) {

            passkey = (sharedpreferences.getString("passkey", ""));

        }
        //end--

        // create list
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, list);
        listcontact.setAdapter(adapter);


        getList();
        // add list all from db
        btnAddAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int numData = contactListData.size();
                idContact = new String[numData];
                nameContact = new String[numData];
                phoneContact = new String[numData];
                for (int i = 0; i < numData; i++) {

                    String notlp = contactListData.get(i).getContact();
                    list.add(notlp);
                    adapter.notifyDataSetChanged();
                }
                editTextTo.setText("");
                showData();


            }
        });
        //add list contact to send
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String notlp = editTextTo.getText().toString();
                if(notlp.equals("")||notlp.isEmpty()||notlp.equals(" ")){
                    Snackbar.make(activity_main, "Contact is empty", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{

                    list.add(notlp);
                    editTextTo.setText("");
                    adapter.notifyDataSetChanged();
                    showData();
                }


            }
        });

        // clear send to
        //add list contact to send
        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                list.clear();

                adapter.notifyDataSetChanged();
                showData();

            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (sendToWhere.getText().toString().isEmpty()) {
                    Snackbar.make(activity_main, "Number is empty", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (editTextTo.getText().length() > 14) {
                    Snackbar.make(activity_main, "Check the number", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (editTextMessage.getText().toString().isEmpty()) {
                    Snackbar.make(activity_main, "Message is empty", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if(list.size() > 1){
                    sendMoreOne(editTextMessage.getText().toString());
                }
                else {
                    sendMoreOne(editTextMessage.getText().toString());
                }
            }
        });


    }
    public void getList(){

        ApiService api2 = APIClient.getClient2().create(ApiService.class);
        Call<ContactListResponse> getListData = api2.getListContact();
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

                        int numData = contactListData.size();
                        idContact = new String[numData];
                        nameContact = new String[numData];
                        phoneContact = new String[numData];
                        for (int i = 0; i < numData; i++) {

                            idContact[i] = contactListData.get(i).getId();
                            nameContact[i] = contactListData.get(i).getName();
                            phoneContact[i] = contactListData.get(i).getContact();

                        }
                        Log.d("DEBUG_", "Set to spinner");
                        SpinneContact.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, nameContact));
                        SpinneContact.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(getFirst){
                                    editTextTo.setText(phoneContact[position]);
                                }else{
                                    getFirst=true;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<ContactListResponse> call, Throwable t)
            {
                //Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendMoreOne(String messageContent) {
        int size = list.size();

        String MessageData = messageContent;
        loading = ProgressDialog.show(this, "Please wait", "Sending sms...", false, false);

        for (int i = 0; i < size; i++) {

            try {
                SendSms(list.get(i),MessageData);
                send_to_outbox(list.get(i),MessageData);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public void SendSms(final String to, String messageContent) {
        String user = userkey;
        String pass = passkey;

        ApiService api = APIClient.getClient().create(ApiService.class);
        Call<Pesan> postMessageData = api.getPesan(user, pass, to, messageContent);

        postMessageData.enqueue(new Callback<Pesan>() {

            @Override
            public void onResponse(Call<Pesan> call, Response<Pesan> response) {
                try {


                    if (response.isSuccessful()) {
                        hideDialog();
                        Pesan Pesandata = response.body();

                        String textSucces;
                        String toRepsonse;
                        for (MessageData messageData : Pesandata.getMessage()) {

                            textSucces = messageData.getText();
                            toRepsonse = messageData.getTo();

                            if (textSucces.toLowerCase().equals("success")) {
                                Snackbar.make(activity_main, "Pesan berhasil dikirim ke : " + toRepsonse, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                sendToWhere.setText("");
                                editTextMessage.setText("");
                                hideDialog();
                            } else {
                                Snackbar.make(activity_main, textSucces, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                hideDialog();
                            }
                        }

                    } else {
                        hideDialog();
                       // Snackbar.make(activity_main, "Maaf pesan gagal dikirim..", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Snackbar.make(activity_main, response.toString(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    hideDialog();
                }

            }

            @Override
            public void onFailure(Call<Pesan> call, Throwable t) {
                hideDialog();
                Log.e(rest_url.TAG, "Opsss something error...");
                Snackbar.make(activity_main, "Koneksi Bermasalah...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Snackbar.make(activity_main, t.toString(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }


        });
    }

    public void send_to_outbox(final String nohp, final  String message){

        ApiService api2 = APIClient.getClient2().create(ApiService.class);
        Call<BasicResponse> postData = api2.postOutbox(nohp,message);

        postData.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call,
                                   Response<BasicResponse> response) {
                try {
                    Log.e(rest_url.TAG, response.body().toString());
                    if (response.isSuccessful()) {
                        Log.e(rest_url.TAG, "masuk" );

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                //Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
    public void showData() {
        if(!list.isEmpty()) {
            sendToWhere.setText(list.toString());
        }else{
            sendToWhere.setText("");
        }

    }


    public void onBackPressed() {
        finish();

    }
    private void hideDialog() {
        if (loading.isShowing()) {
            loading.dismiss();
        }
    }
}

