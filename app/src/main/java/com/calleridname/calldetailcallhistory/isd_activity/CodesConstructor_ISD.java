package com.calleridname.calldetailcallhistory.isd_activity;

public class CodesConstructor_ISD {
    private String EmpId;
    private String FirstName;
    private String LastName;

    public CodesConstructor_ISD() {
    }

    public String getEmpId() {
        return this.EmpId;
    }

    public void setEmpId(String str) {
        this.EmpId = str;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String str) {
        this.FirstName = str;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String str) {
        this.LastName = str;
    }

    public CodesConstructor_ISD(String str, String str2, String str3) {
        this.EmpId = str;
        this.FirstName = str2;
        this.LastName = str3;
    }
}
