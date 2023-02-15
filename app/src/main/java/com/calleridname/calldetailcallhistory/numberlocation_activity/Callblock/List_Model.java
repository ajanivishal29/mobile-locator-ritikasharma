package com.calleridname.calldetailcallhistory.numberlocation_activity.Callblock;

public class List_Model {
    private boolean chkCall;
    private boolean chkMsg;
    private String number;

    public List_Model(String str, boolean z, boolean z2) {
        this.number = str;
        this.chkMsg = z;
        this.chkCall = z2;
    }

    public String getNumber() {
        return this.number;
    }

    public void setMsg(boolean z) {
        this.chkMsg = z;
    }

    public void setCall(boolean z) {
        this.chkCall = z;
    }

    public boolean getMsg() {
        return this.chkMsg;
    }

    public boolean getCall() {
        return this.chkCall;
    }
}
