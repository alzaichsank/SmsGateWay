package com.example.alzaichsank.smsgateway.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="message")
public class MessageData {


    @Element(name = "to",required=false)
    String to;

    @Element(name = "status",required=false)
    String status;

    @Element(name = "text",required=false)
    String text;


    @Element(name = "balance",required=false)
    String balance;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }




    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



}
