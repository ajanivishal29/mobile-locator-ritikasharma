package com.calleridname.calldetailcallhistory.numberlocation_activity;

public class NumberSearch_Constructor {
    private String StateName;
    private int iconValue;
    private String latitude;
    private String longitude;
    private String operator;

    public void setOperator(String str) {
        this.operator = str;
    }

    public String getOperator() {
        return this.operator;
    }

    public void stateName(String str) {
        this.StateName = str;
    }

    public void iconValue(int i) {
        this.iconValue = i;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getStateName() {
        return this.StateName;
    }

    public int getIconValue() {
        return this.iconValue;
    }
}
