package com.example.alzaichsank.smsgateway.system.retrofit;

import com.example.alzaichsank.smsgateway.interface_file.ApiService;
import com.example.alzaichsank.smsgateway.system.rest.rest_url;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class APIClient {
    /**
     * config Retrofit in initialization
     */

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit=null;
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(rest_url.END_POINT)
                    .client(new OkHttpClient())
                    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                            new Persister(new AnnotationStrategy())))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClient2() {
        retrofit=null;
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(rest_url.END_POINT_SELF_SERVER)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
