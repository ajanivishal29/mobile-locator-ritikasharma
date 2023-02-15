package com.calleridname.calldetailcallhistory.Main_Ads.admob_ads;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private SharedPreferences sharedPrefs;
    private static final String APP_PREFS = "adcount";
    private static final String APP_PREFS1 = "adcountbackpress";
    private static final String APP_PREFS2 = "nativeadcount";
    private static final String APP_PREFS3 = "banneradcount";
    private static SharedPreferencesManager instance;

    private SharedPreferencesManager(Context context) {
        sharedPrefs = context.getApplicationContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        sharedPrefs = context.getApplicationContext().getSharedPreferences(APP_PREFS1, Context.MODE_PRIVATE);
        sharedPrefs = context.getApplicationContext().getSharedPreferences(APP_PREFS2, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null)
            instance = new SharedPreferencesManager(context);

        return instance;
    }

    public void storeClicks(int count) {

        SharedPreferences.Editor meditor = sharedPrefs.edit();
        meditor.putInt(APP_PREFS, count);
        meditor.apply();
    }

    public void backpressstoreClicks(int count) {

        SharedPreferences.Editor meditor = sharedPrefs.edit();
        meditor.putInt(APP_PREFS1, count);
        meditor.apply();
    }

    public void nativestoreClicks(int count) {

        SharedPreferences.Editor meditor = sharedPrefs.edit();
        meditor.putInt(APP_PREFS2, count);
        meditor.apply();
    }

    public int getNumberOfClicks() {
        int clicksNumber = sharedPrefs.getInt(APP_PREFS, 1);
        return clicksNumber;
    }

    public int getbackpressNumberOfClicks() {
        int backpressclicksNumber = sharedPrefs.getInt(APP_PREFS1, 1);
        return backpressclicksNumber;
    }

    public int getnativeNumberOfClicks() {
        int clicksNumber = sharedPrefs.getInt(APP_PREFS2, 1);
        return clicksNumber;
    }
}
