package com.example.alzaichsank.smsgateway.system.rest;

public class rest_url {

    public static String END_POINT = "http://reguler.zenziva.net/";
    public static String END_POINT_SELF_SERVER = "http://codehopedevloper.com/sms_gateway/";

    //Account
    public static String LOGIN = "login";


    //zenziva sms xml
    public final static String cek_credit ="apps/smsapibalance";
    public final static String post_message ="apps/smsapi.php";

    //self

    public final static String listContact ="ContactData";
    public final static String ContactCreate ="ContactData/create";
    public final static String ContactUpdate ="ContactData/update";

    //outbox

    public final static String listoutbox ="Message_data";
    public final static String outboxpost ="Message_data/create";

    //TAG Log
    public static String  TAG = "LOG TAG HASIL";


}



