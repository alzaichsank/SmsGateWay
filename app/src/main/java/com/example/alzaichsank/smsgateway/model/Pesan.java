package com.example.alzaichsank.smsgateway.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response")
public class Pesan {


    @ElementList(inline = true,required=false)
    private List<MessageData> messageDataList;

    public  Pesan()
    { }



    public List<MessageData> getMessage()
    {
        return messageDataList;
    }

    public void setMessageData(List<MessageData> messageDataList)
    {
        this.messageDataList = messageDataList;
    }
}


