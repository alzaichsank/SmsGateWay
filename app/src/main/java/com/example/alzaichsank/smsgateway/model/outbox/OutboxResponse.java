
package com.example.alzaichsank.smsgateway.model.outbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OutboxResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private List<Outboxdata> message = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Outboxdata> getContactListData() {
        return message;
    }

    public void setContactListData(List<Outboxdata> message) {
        this.message = message;
    }

}
