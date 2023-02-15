package com.calleridname.calldetailcallhistory.system_activity;

import android.app.ActivityManager;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.calleridname.calldetailcallhistory.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment_SystemInfo1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String TAG = "asdfgazdfsdafad";
    BufferedReader f122b;
    public long free = 0;
    private String mParam1;
    private String mParam2;
    Runtime runtime;
    public long total = 0;
    private TextView txt_extfreespace;
    private TextView txt_exttotalspace;
    private TextView txt_freeram;
    private TextView txt_intfreespace;
    private TextView txt_inttotalspace;
    private TextView txt_totalram;
    private TextView txt_usedram;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_systeminfo1, viewGroup, false);
        initview(inflate);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
        try {
            this.f122b = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/proc/meminfo"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }

    private void initview(View view) {
        this.txt_totalram = (TextView) view.findViewById(R.id.txt_totalram);
        this.txt_usedram = (TextView) view.findViewById(R.id.txt_usedram);
        this.txt_freeram = (TextView) view.findViewById(R.id.txt_freeram);
        this.txt_inttotalspace = (TextView) view.findViewById(R.id.txt_inttotalspace);
        this.txt_intfreespace = (TextView) view.findViewById(R.id.txt_intfreespace);
        this.txt_exttotalspace = (TextView) view.findViewById(R.id.txt_exttotalspace);
        this.txt_extfreespace = (TextView) view.findViewById(R.id.txt_extfreespace);
        getMemorySize();
        this.txt_exttotalspace.setText(getTotalExternalMemorySize());
        this.txt_extfreespace.setText(getAvailableExternalMemorySize());
        this.txt_inttotalspace.setText(getTotalInternalMemorySize());
        this.txt_intfreespace.setText(getAvailableInternalMemorySize());
        this.txt_totalram.setText(calSize((double) this.total));
        this.txt_freeram.setText(calSize((double) this.free));
        this.txt_usedram.setText(calSize((double) (this.total - this.free)));
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) getActivity().getSystemService("activity")).getMemoryInfo(memoryInfo);
        float f = (float) (memoryInfo.totalMem / 1073741824);
        String str = this.TAG;
        Log.e(str, "initview: " + f);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String calSize(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double d2 = d / 1048576.0d;
        double d3 = d / 1.073741824E9d;
        double d4 = d / 1.099511627776E12d;
        if (d4 > 1.0d) {
            return decimalFormat.format(d4).concat(" TB");
        }
        if (d3 > 1.0d) {
            return decimalFormat.format(d3).concat(" GB");
        }
        if (d2 > 1.0d) {
            return decimalFormat.format(d2).concat(" MB");
        }
        return decimalFormat.format(d).concat(" KB");
    }

    private void getMemorySize() {
        Pattern compile = Pattern.compile("([a-zA-Z]+):\\s*(\\d+)");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/meminfo", "r");
            while (true) {
                String readLine = randomAccessFile.readLine();
                if (readLine != null) {
                    Matcher matcher = compile.matcher(readLine);
                    if (matcher.find()) {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        if (group.equalsIgnoreCase("MemTotal")) {
                            this.total = Long.parseLong(group2);
                        } else if (group.equalsIgnoreCase("MemFree") || group.equalsIgnoreCase("SwapFree")) {
                            this.free = Long.parseLong(group2);
                        }
                    }
                } else {
                    randomAccessFile.close();
                    this.total *= 1024;
                    this.free *= 1024;
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getAvailableInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return formatSize((double) (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())));
    }

    public static String getTotalInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return formatSize((double) (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())));
    }

    public static String getAvailableExternalMemorySize() {
        if (!externalMemoryAvailable()) {
            return null;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return formatSize((double) (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())));
    }

    public static String getTotalExternalMemorySize() {
        if (!externalMemoryAvailable()) {
            return null;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return formatSize((double) (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String formatSize(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double d2 = d / 1048576.0d;
        double d3 = d / 1.073741824E9d;
        double d4 = d / 1.099511627776E12d;
        if (d4 > 1.0d) {
            return decimalFormat.format(d4).concat(" TB");
        }
        if (d3 > 1.0d) {
            return decimalFormat.format(d3).concat(" GB");
        }
        if (d2 > 1.0d) {
            return decimalFormat.format(d2).concat(" MB");
        }
        return decimalFormat.format(d).concat(" KB");
    }
}
