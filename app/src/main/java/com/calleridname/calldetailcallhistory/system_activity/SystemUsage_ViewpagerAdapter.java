package com.calleridname.calldetailcallhistory.system_activity;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SystemUsage_ViewpagerAdapter extends FragmentPagerAdapter {
    Activity activity;
    int tabcount;

    public SystemUsage_ViewpagerAdapter(pgrs_SystemUsage_Activity systemUsage_Activity, FragmentManager fragmentManager, int i) {
        super(fragmentManager);
        this.activity = systemUsage_Activity;
        this.tabcount = i;
    }

    public Fragment getItem(int i) {
        if (i == 0) {
            return new Fragment_SystemInfo1();
        }
        if (i != 1) {
            return null;
        }
        return new Fragment_SystemInfo2();
    }

    public int getCount() {
        return this.tabcount;
    }
}
