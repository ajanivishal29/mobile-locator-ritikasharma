package com.calleridname.calldetailcallhistory.device_activity;

import android.annotation.SuppressLint;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.calleridname.calldetailcallhistory.R;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Pattern;

public class Fragment_DeviceInfo2 extends Fragment {
    private String TAG = "sdfzsdfzsdf";
    public String phonestate;
    private TextView txt_country;
    private TextView txt_cpucore;
    private TextView txt_instructionset;
    private TextView txt_ipaddress;
    private TextView txt_macaddress;
    private TextView txt_maxrequency;
    private TextView txt_networktype;
    private TextView txt_operator;
    private TextView txt_roaming;
    public TextView txt_servicestate;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_deviceinfo2, viewGroup, false);
        initview(inflate);
        ((TelephonyManager) getActivity().getSystemService("phone")).listen(new PhoneStateListener() {
            public void onServiceStateChanged(ServiceState serviceState) {
                super.onServiceStateChanged(serviceState);
                serviceState.getRoaming();
                int state = serviceState.getState();
                if (state == 0) {
                    Fragment_DeviceInfo2 fragment_DeviceInfo2 = Fragment_DeviceInfo2.this;
                    fragment_DeviceInfo2.phonestate = "STATE_IN_SERVICE";
                    if (fragment_DeviceInfo2.txt_servicestate != null) {
                        Fragment_DeviceInfo2.this.txt_servicestate.setText(Fragment_DeviceInfo2.this.phonestate);
                    }
                } else if (state == 1) {
                    Fragment_DeviceInfo2 fragment_DeviceInfo22 = Fragment_DeviceInfo2.this;
                    fragment_DeviceInfo22.phonestate = "STATE_OUT_OF_SERVICE";
                    if (fragment_DeviceInfo22.txt_servicestate != null) {
                        Fragment_DeviceInfo2.this.txt_servicestate.setText(Fragment_DeviceInfo2.this.phonestate);
                    }
                } else if (state == 2) {
                    Fragment_DeviceInfo2 fragment_DeviceInfo23 = Fragment_DeviceInfo2.this;
                    fragment_DeviceInfo23.phonestate = "STATE_EMERGENCY_ONLY";
                    if (fragment_DeviceInfo23.txt_servicestate != null) {
                        Fragment_DeviceInfo2.this.txt_servicestate.setText(Fragment_DeviceInfo2.this.phonestate);
                    }
                } else if (state != 3) {
                    Fragment_DeviceInfo2 fragment_DeviceInfo24 = Fragment_DeviceInfo2.this;
                    fragment_DeviceInfo24.phonestate = "UNKNOWN";
                    if (fragment_DeviceInfo24.txt_servicestate != null) {
                        Fragment_DeviceInfo2.this.txt_servicestate.setText(Fragment_DeviceInfo2.this.phonestate);
                    }
                } else {
                    Fragment_DeviceInfo2 fragment_DeviceInfo25 = Fragment_DeviceInfo2.this;
                    fragment_DeviceInfo25.phonestate = "STATE_POWER_OFF";
                    if (fragment_DeviceInfo25.txt_servicestate != null) {
                        Fragment_DeviceInfo2.this.txt_servicestate.setText(Fragment_DeviceInfo2.this.phonestate);
                    }
                }
            }
        }, 1);
        return inflate;
    }

    private void initview(View view) {
        this.txt_cpucore = (TextView) view.findViewById(R.id.txt_cpucore);
        this.txt_maxrequency = (TextView) view.findViewById(R.id.txt_maxrequency);
        this.txt_instructionset = (TextView) view.findViewById(R.id.txt_instructionset);
        this.txt_networktype = (TextView) view.findViewById(R.id.txt_networktype);
        this.txt_ipaddress = (TextView) view.findViewById(R.id.txt_ipaddress);
        this.txt_macaddress = (TextView) view.findViewById(R.id.txt_macaddress);
        this.txt_operator = (TextView) view.findViewById(R.id.txt_operator);
        this.txt_country = (TextView) view.findViewById(R.id.txt_country);
        this.txt_roaming = (TextView) view.findViewById(R.id.txt_roaming);
        this.txt_servicestate = (TextView) view.findViewById(R.id.txt_servicestate);
        getdata();
        this.txt_country.setText(getContext().getResources().getConfiguration().locale.getDisplayCountry());
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq", "r");
            String readLine = randomAccessFile.readLine();
            randomAccessFile.close();
            TextView textView = this.txt_maxrequency;
            textView.setText(Integer.toString(Integer.parseInt(readLine) / 1000000) + " Ghz");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getdata() {
        this.txt_cpucore.setText(Integer.toString(getCPUCores()));
        this.txt_instructionset.setText(getInstructionSets());
        this.txt_networktype.setText(getPhoneType());
        this.txt_macaddress.setText(getMACAddress("wlan0"));
        this.txt_ipaddress.setText(Formatter.formatIpAddress(((WifiManager) getActivity().getApplicationContext().getSystemService("wifi")).getConnectionInfo().getIpAddress()));
        this.txt_operator.setText(getNetworkOperatorName());
        if (isDataRoamingEnabled().booleanValue()) {
            this.txt_roaming.setText("ON");
        } else {
            this.txt_roaming.setText("OFF");
        }
    }

    public Boolean isDataRoamingEnabled() {
        try {
            boolean z = true;
            if (Settings.Secure.getInt(getContext().getContentResolver(), "data_roaming") != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int getCPUCores() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return Pattern.matches("cpu[0-9]+", file.getName());
                }
            }).length;
        } catch (Exception unused) {
            return 1;
        }
    }

    private String getInstructionSets() {
        String str = Build.CPU_ABI;
        if (Build.VERSION.SDK_INT < 8 || Build.CPU_ABI2 == null || Build.CPU_ABI2.equals("unknown")) {
            return str;
        }
        return str + ", " + Build.CPU_ABI2;
    }

    public String getMACAddress(String str) {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            if (!it.hasNext()) {
                return "";
            }
            NetworkInterface networkInterface = (NetworkInterface) it.next();
            if (str != null) {
                networkInterface.getName().equalsIgnoreCase(str);
            }
            byte[] hardwareAddress = networkInterface.getHardwareAddress();
            if (hardwareAddress == null) {
                return "No H/W";
            }
            StringBuilder sb = new StringBuilder();
            int length = hardwareAddress.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    private String getPhoneType() {
        @SuppressLint("WrongConstant") int phoneType = ((TelephonyManager) getActivity().getSystemService("phone")).getPhoneType();
        if (phoneType == 0) {
            return "NONE";
        }
        if (phoneType != 1) {
            return phoneType != 2 ? "" : "CDMA";
        }
        return "GSM";
    }

    public String getNetworkOperatorName() {
        return ((TelephonyManager) getContext().getSystemService("phone")).getNetworkOperatorName();
    }
}
