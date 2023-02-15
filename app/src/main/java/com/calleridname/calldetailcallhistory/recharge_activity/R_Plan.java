package com.calleridname.calldetailcallhistory.recharge_activity;

public class R_Plan {
    private String category;
    private String detail;
    private String keywords;
    private String price;
    private String talktime;
    private String updated;
    private String validity;

    R_Plan(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.category = str;
        this.detail = str2;
        this.price = str3;
        this.updated = str4;
        this.validity = str5;
        this.talktime = str6;
        this.keywords = str7;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDetail() {
        return this.detail;
    }

    public String getPrice() {
        return this.price;
    }

    public String getUpdated() {
        return this.updated;
    }

    public String getValidity() {
        return this.validity;
    }

    public String getTalktime() {
        return this.talktime;
    }

    public String getKeywords() {
        return this.keywords;
    }
}
