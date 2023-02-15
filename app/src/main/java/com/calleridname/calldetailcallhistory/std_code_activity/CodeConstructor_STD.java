package com.calleridname.calldetailcallhistory.std_code_activity;

public class CodeConstructor_STD {
    private String Areacode;
    private String Areaname;

    public CodeConstructor_STD() {
    }

    public String getAreacode() {
        return this.Areacode;
    }

    public void setAreacode(String str) {
        this.Areacode = str;
    }

    public String getAreaname() {
        return this.Areaname;
    }

    public void setAreaname(String str) {
        this.Areaname = str;
    }

    public CodeConstructor_STD(String str, String str2) {
        this.Areacode = str;
        this.Areaname = str2;
    }
}
