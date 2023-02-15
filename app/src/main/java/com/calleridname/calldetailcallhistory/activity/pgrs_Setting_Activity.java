package com.calleridname.calldetailcallhistory.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.calleridname.calldetailcallhistory.device_activity.pgrs_DeviceInfo_Activity;
import com.calleridname.calldetailcallhistory.system_activity.pgrs_SystemUsage_Activity;
import com.facebook.ads.NativeAdLayout;

public class pgrs_Setting_Activity extends AppCompatActivity {
    RelativeLayout audio;
    RelativeLayout device_info;
    RelativeLayout system;

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
        setContentView((int) R.layout.pgrs_setting_layout);

        this.device_info = (RelativeLayout) findViewById(R.id.device_info);
        this.system = (RelativeLayout) findViewById(R.id.system);
        this.audio = (RelativeLayout) findViewById(R.id.audio);

        context = pgrs_Setting_Activity.this;

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        this.device_info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_Setting_Activity setting_Activity = pgrs_Setting_Activity.this;
                setting_Activity.startActivity(new Intent(setting_Activity.getApplicationContext(), pgrs_DeviceInfo_Activity.class));
            }
        });
        this.system.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_Setting_Activity setting_Activity = pgrs_Setting_Activity.this;
                setting_Activity.startActivity(new Intent(setting_Activity.getApplicationContext(), pgrs_SystemUsage_Activity.class));
            }
        });
        this.audio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT < 23) {
                    return;
                }
                if (!Settings.System.canWrite(pgrs_Setting_Activity.this.getApplicationContext())) {
                    pgrs_Setting_Activity setting_Activity = pgrs_Setting_Activity.this;
                    setting_Activity.startActivityForResult(new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", Uri.parse("package:" + pgrs_Setting_Activity.this.getPackageName())), 200);
                    return;
                }
                pgrs_Setting_Activity setting_Activity2 = pgrs_Setting_Activity.this;
                setting_Activity2.startActivity(new Intent(setting_Activity2, pgrs_ManageAudio_Activity.class));
            }
        });
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
