package com.example.alzaichsank.smsgateway.interface_file;

import com.example.alzaichsank.smsgateway.model.BasicResponse;
import com.example.alzaichsank.smsgateway.model.Pesan;
import com.example.alzaichsank.smsgateway.model.contact_list.ContactListResponse;
import com.example.alzaichsank.smsgateway.model.outbox.OutboxResponse;
import com.example.alzaichsank.smsgateway.system.rest.rest_url;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @GET(rest_url.post_message)
    Call<Pesan> getPesan(@Query("userkey") String userkey,
                         @Query("passkey") String passkey,
                         @Query("nohp") String nohp,
                         @Query("pesan") String pesan );

    @GET(rest_url.listContact)
    Call<ContactListResponse> getListContact();


    @POST(rest_url.ContactCreate)
    Call<BasicResponse> postContact(@Query("name") String name,
                                     @Query("contact") String contact );


    @POST(rest_url.ContactUpdate)
    Call<BasicResponse> updateContact(@Query("id") String id,
                                        @Query("name") String name,
                                        @Query("contact") String contact );

    @POST(rest_url.outboxpost)
    Call<BasicResponse> postOutbox(@Query("notujuan") String notujuan,
                                    @Query("messagedata") String messagedata );

    @GET(rest_url.listoutbox)
    Call<OutboxResponse> getListOutbox();
}
