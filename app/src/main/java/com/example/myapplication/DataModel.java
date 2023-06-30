package com.example.myapplication;

public class DataModel {
    private String datetime;
    private String data;
    private String extra;

    public DataModel()
    {

    }

    public DataModel(String datetime, String data, String extra)
    {
        this.datetime = datetime;
        this.data = data;
        this.extra = extra;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }




}
