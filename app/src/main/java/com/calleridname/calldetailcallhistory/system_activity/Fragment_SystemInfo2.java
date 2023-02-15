package com.calleridname.calldetailcallhistory.system_activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.calleridname.calldetailcallhistory.R;

public class Fragment_SystemInfo2 extends Fragment {
    private String TAG = "fasdfasdfasdf";
    private TextView txt_batteryhealth;
    private TextView txt_batterylevel;
    private TextView txt_batterystatus;
    private TextView txt_batterytech;
    private TextView txt_batterytemp;
    private TextView txt_batteryuptime;
    private TextView txt_batteryvoltage;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_systeminfo2, viewGroup, false);
        initview(inflate);
        return inflate;
    }

    private void initview(View view) {
        this.txt_batterylevel = (TextView) view.findViewById(R.id.txt_batterylevel);
        this.txt_batterystatus = (TextView) view.findViewById(R.id.txt_batterystatus);
        this.txt_batteryhealth = (TextView) view.findViewById(R.id.txt_batteryhealth);
        this.txt_batterytemp = (TextView) view.findViewById(R.id.txt_batterytemp);
        this.txt_batterytech = (TextView) view.findViewById(R.id.txt_batterytech);
        this.txt_batteryvoltage = (TextView) view.findViewById(R.id.txt_batteryvoltage);
        this.txt_batteryuptime = (TextView) view.findViewById(R.id.txt_batteryuptime);
        Battery();
    }

    private void Battery() {
        Intent registerReceiver = getActivity().registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        TextView textView = this.txt_batterylevel;
        textView.setText(Integer.toString((int) ((((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1))) * 100.0f)) + "%");
        int intExtra = registerReceiver.getIntExtra("status", -1);
        String str = this.TAG;
        Log.e(str, "getBattery_percentage: " + Integer.toString(intExtra));
        if (intExtra == 2) {
            this.txt_batterystatus.setText(" USB Charging");
        }
        if (intExtra == 3) {
            this.txt_batterystatus.setText("Discharging");
        }
        if (intExtra == 5) {
            this.txt_batterystatus.setText("Full");
        }
        if (intExtra == 4) {
            this.txt_batterystatus.setText("Not Charging");
        }
        if (intExtra == 1) {
            this.txt_batterystatus.setText("Unknown");
        }
        int intExtra2 = registerReceiver.getIntExtra("health", -1);
        if (intExtra2 == 7) {
            this.txt_batteryhealth.setText("Cold");
        }
        if (intExtra2 == 4) {
            this.txt_batteryhealth.setText("Dead");
        }
        if (intExtra2 == 2) {
            this.txt_batteryhealth.setText("Good");
        }
        if (intExtra2 == 5) {
            this.txt_batteryhealth.setText("Over-Voltage");
        }
        if (intExtra2 == 3) {
            this.txt_batteryhealth.setText("Overheat");
        }
        if (intExtra2 == 1) {
            this.txt_batteryhealth.setText("Unknown");
        }
        if (intExtra2 == 6) {
            this.txt_batteryhealth.setText("Unspecified Failure");
        }
        TextView textView2 = this.txt_batterytemp;
        textView2.setText(String.valueOf(((float) registerReceiver.getIntExtra("temperature", -1)) / 10.0f) + " *C");
        this.txt_batterytech.setText(registerReceiver.getExtras().getString("technology"));
        this.txt_batteryvoltage.setText(String.valueOf(registerReceiver.getIntExtra("voltage", -1)));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        StringBuffer stringBuffer = new StringBuffer();
        if (elapsedRealtime > 86400000) {
            stringBuffer.append(elapsedRealtime / 86400000);
            stringBuffer.append(" days ");
            elapsedRealtime %= 86400000;
        }
        if (elapsedRealtime > 3600000) {
            stringBuffer.append(elapsedRealtime / 3600000);
            stringBuffer.append(" hours ");
            elapsedRealtime %= 3600000;
        }
        if (elapsedRealtime > 60000) {
            stringBuffer.append(elapsedRealtime / 60000);
            stringBuffer.append(" min. ");
            elapsedRealtime %= 60000;
        }
        if (elapsedRealtime > 1000) {
            stringBuffer.append(elapsedRealtime / 1000);
            stringBuffer.append(" sec.");
            long j = elapsedRealtime % 1000;
        }
        this.txt_batteryuptime.setText(stringBuffer.toString());
    }
}
