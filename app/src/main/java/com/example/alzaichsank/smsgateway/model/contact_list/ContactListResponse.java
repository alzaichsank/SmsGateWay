
package com.example.alzaichsank.smsgateway.model.contact_list;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactListResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private List<ContactListData> message = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ContactListData> getContactListData() {
        return message;
    }

    public void setContactListData(List<ContactListData> message) {
        this.message = message;
    }

}
