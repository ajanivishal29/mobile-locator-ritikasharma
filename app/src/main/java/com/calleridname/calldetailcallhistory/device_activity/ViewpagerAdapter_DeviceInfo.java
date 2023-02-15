package com.calleridname.calldetailcallhistory.device_activity;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewpagerAdapter_DeviceInfo extends FragmentPagerAdapter {
    Activity activity;
    int tabcount;

    public ViewpagerAdapter_DeviceInfo(pgrs_DeviceInfo_Activity deviceInfo_Activity, FragmentManager fragmentManager, int i) {
        super(fragmentManager);
        this.activity = deviceInfo_Activity;
        this.tabcount = i;
    }

    public Fragment getItem(int i) {
        if (i == 0) {
            return new Fragment_DeviceInfo1();
        }
        if (i != 1) {
            return null;
        }
        return new Fragment_DeviceInfo2();
    }

    public int getCount() {
        return this.tabcount;
    }
}
