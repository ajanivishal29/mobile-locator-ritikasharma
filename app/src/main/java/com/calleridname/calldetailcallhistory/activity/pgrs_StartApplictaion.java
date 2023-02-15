package com.calleridname.calldetailcallhistory.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.Main_Ads.pgrs_thankyou;
import com.calleridname.calldetailcallhistory.R;

public class pgrs_StartApplictaion extends AppCompatActivity {

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    LinearLayout Continue;

    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pgrs_activity_start_applictaion);

        context = pgrs_StartApplictaion.this;

        Continue = (LinearLayout) findViewById(R.id.continue_btn);

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).checkstaticNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
            Adintermethod.getInstance(this).checkstaticNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).admob_nativebanner_load(this, convertedObject.getAdmobNativeid());
            Adintermethod.getInstance(this).admob_native_load(this, convertedObject.getAdmobNativeid());
        }

        Continue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (convertedObject != null) {
                    Adintermethod.getInstance(pgrs_StartApplictaion.this).adintercheck(convertedObject, context, convertedObject.getFbinter2(), new Adintermethod.onAdIntent() {
                        @Override
                        public void onintentscreen() {
                            pgrs_StartApplictaion.this.startActivity(new Intent(pgrs_StartApplictaion.this, pgrs_First_intro.class));
                        }
                    });
                } else {
                    pgrs_StartApplictaion.this.startActivity(new Intent(pgrs_StartApplictaion.this, pgrs_First_intro.class));
                }
            }
        });

    }

    public void onBackPressed() {
        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInterBack(this);
            startActivity(new Intent(pgrs_StartApplictaion.this, pgrs_thankyou.class));
        } else {
            startActivity(new Intent(pgrs_StartApplictaion.this, pgrs_thankyou.class));
        }
    }
}
