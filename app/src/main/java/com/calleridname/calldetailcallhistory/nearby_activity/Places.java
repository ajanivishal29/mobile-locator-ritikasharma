package com.calleridname.calldetailcallhistory.nearby_activity;

public class Places {
    String place_id = "";
    String place_name = "";
    String place_type = "";

    public Places(String str, String str2, String str3) {
        this.place_id = str;
        this.place_name = str2;
        this.place_type = str3;
    }

    public String getPlace_id() {
        return this.place_id;
    }

    public void setPlace_id(String str) {
        this.place_id = str;
    }

    public String getPlace_name() {
        return this.place_name;
    }

    public void setPlace_name(String str) {
        this.place_name = str;
    }

    public String getPlace_type() {
        return this.place_type;
    }

    public void setPlace_type(String str) {
        this.place_type = str;
    }
}
