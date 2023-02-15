package com.calleridname.calldetailcallhistory.device_activity;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.google.android.material.tabs.TabLayout;

public class pgrs_DeviceInfo_Activity extends AppCompatActivity {
    public LinearLayout f107l1;
    public LinearLayout f108l2;
    private TabLayout tablayout;
    public TextView text1;
    public TextView text2;
    public ViewPager viewpager;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pgrs_device_info_layout);

        initview();
        ViewGroup viewGroup = null;
        View inflate = getLayoutInflater().inflate(R.layout.pgrs_device_tablayout1, viewGroup, false);
        this.f107l1 = (LinearLayout) inflate.findViewById(R.id.l1);
        this.text1 = (TextView) inflate.findViewById(R.id.text1);

        context = pgrs_DeviceInfo_Activity.this;

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        View inflate2 = getLayoutInflater().inflate(R.layout.pgrs_device_tablayout2, viewGroup, false);
        this.f108l2 = (LinearLayout) inflate2.findViewById(R.id.l2);
        this.text2 = (TextView) inflate2.findViewById(R.id.text2);
        TabLayout tabLayout = this.tablayout;
        tabLayout.addTab(tabLayout.newTab().setCustomView(inflate));
        TabLayout tabLayout2 = this.tablayout;
        tabLayout2.addTab(tabLayout2.newTab().setCustomView(inflate2));
        int i = Build.VERSION.SDK_INT;
        this.tablayout.setTabGravity(0);
        this.tablayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        this.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tablayout));
        this.tablayout.addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.BaseOnTabSelectedListener() {
            public void onTabReselected(TabLayout.Tab tab) {
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }

            public void onTabSelected(TabLayout.Tab tab) {
                tab.getPosition();
                tab.getPosition();
                pgrs_DeviceInfo_Activity.this.viewpager.setCurrentItem(tab.getPosition());
            }
        });
        this.viewpager.setAdapter(new ViewpagerAdapter_DeviceInfo(this, getSupportFragmentManager(), this.tablayout.getTabCount()));
        this.viewpager.setPageTransformer(true, new BackgroundToForegroundTransformer());
    }

    private void initview() {
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tablayout = (TabLayout) findViewById(R.id.tablayout);
    }

    public void onBackPressed() {
        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInterBack(this);
            finish();
        } else {
            finish();
        }
    }
}
