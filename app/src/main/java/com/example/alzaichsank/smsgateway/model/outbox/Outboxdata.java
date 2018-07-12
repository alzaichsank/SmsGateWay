
package com.example.alzaichsank.smsgateway.model.outbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Outboxdata {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("no_tujuan")
    @Expose
    private String no_tujuan;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("date")
    @Expose
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo_tujuan() {
        return no_tujuan;
    }

    public void setNo_tujuan(String no_tujuan) {
        this.no_tujuan = no_tujuan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





}
