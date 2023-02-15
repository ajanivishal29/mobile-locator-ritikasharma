package com.calleridname.calldetailcallhistory.numberlocation_activity.countrycodepicker;

public class Country {
    private final String iso;
    private final String name;
    private final String phoneCode;

    public Country(String str, String str2, String str3) {
        this.iso = str;
        this.phoneCode = str2;
        this.name = str3;
    }

    public String getIso() {
        return this.iso;
    }

    public String getPhoneCode() {
        return this.phoneCode;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEligibleForQuery(String str) {
        String lowerCase = str.toLowerCase();
        return getName().toLowerCase().contains(lowerCase) || getIso().toLowerCase().contains(lowerCase) || getPhoneCode().toLowerCase().contains(lowerCase);
    }
}
