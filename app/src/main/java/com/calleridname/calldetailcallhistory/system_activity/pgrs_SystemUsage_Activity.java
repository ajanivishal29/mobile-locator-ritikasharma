package com.calleridname.calldetailcallhistory.system_activity;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.calleridname.calldetailcallhistory.device_activity.BackgroundToForegroundTransformer;
import com.facebook.ads.NativeAdLayout;
import com.google.android.material.tabs.TabLayout;

public class pgrs_SystemUsage_Activity extends AppCompatActivity {
    public String TAG = "System_Usage_Activity";
    public LinearLayout f120l1;
    public LinearLayout f121l2;
    ImageView ivback;
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
        setContentView((int) R.layout.pgrs_system_usage_layout);

        initview();
        ViewGroup viewGroup = null;
        View inflate = getLayoutInflater().inflate(R.layout.pgrs_system_tablayout1, viewGroup, false);
        this.f120l1 = (LinearLayout) inflate.findViewById(R.id.l1);
        this.text1 = (TextView) inflate.findViewById(R.id.text1);
        View inflate2 = getLayoutInflater().inflate(R.layout.pgrs_system_tablayout2, viewGroup, false);
        this.f121l2 = (LinearLayout) inflate2.findViewById(R.id.l2);
        this.text2 = (TextView) inflate2.findViewById(R.id.text2);
        TabLayout tabLayout = this.tablayout;
        this.ivback = (ImageView) findViewById(R.id.ivback);
        tabLayout.addTab(tabLayout.newTab().setCustomView(inflate));
        TabLayout tabLayout2 = this.tablayout;
        tabLayout2.addTab(tabLayout2.newTab().setCustomView(inflate2));
        this.tablayout.setTabGravity(0);
        this.tablayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.middle)));
        this.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tablayout));

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        context = pgrs_SystemUsage_Activity.this;
        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        this.tablayout.addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.BaseOnTabSelectedListener() {
            public void onTabReselected(TabLayout.Tab tab) {
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }

            public void onTabSelected(TabLayout.Tab tab) {
                String str = pgrs_SystemUsage_Activity.this.TAG;
                Log.e(str, "onTabSelected: " + tab.getPosition());
                tab.getPosition();
                tab.getPosition();
                pgrs_SystemUsage_Activity.this.viewpager.setCurrentItem(tab.getPosition());
            }
        });
        this.viewpager.setPageTransformer(true, new BackgroundToForegroundTransformer());
        this.viewpager.setAdapter(new SystemUsage_ViewpagerAdapter(this, getSupportFragmentManager(), this.tablayout.getTabCount()));
        this.ivback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_SystemUsage_Activity.this.onBackPressed();
            }
        });
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
