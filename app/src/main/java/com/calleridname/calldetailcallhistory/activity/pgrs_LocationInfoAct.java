package com.calleridname.calldetailcallhistory.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;

public class pgrs_LocationInfoAct extends AppCompatActivity {

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    public void onBackPressed() {
        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInterBack(this);
            finish();
        } else {
            finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pgrs_activity_location_info);

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        context = pgrs_LocationInfoAct.this;
        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgrs_LocationInfoAct.this.onBackPressed();
            }
        });

        findViewById(R.id.iv_liveLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgrs_LocationInfoAct.this.startActivity(new Intent(pgrs_LocationInfoAct.this, pgrs_LiveLocationActivity.class));
            }
        });

        findViewById(R.id.iv_nl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgrs_LocationInfoAct.this.startActivity(new Intent(pgrs_LocationInfoAct.this, pgrs_NumberLocatorOpenAct.class));
            }
        });

        findViewById(R.id.iv_findAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgrs_LocationInfoAct.this.startActivity(new Intent(pgrs_LocationInfoAct.this, pgrs_FindAddressActivity.class));
            }
        });

        findViewById(R.id.iv_liveweather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgrs_LocationInfoAct.this.startActivity(new Intent(pgrs_LocationInfoAct.this, pgrs_LiveWeatherActivity.class));
            }
        });

        findViewById(R.id.iv_Gpstime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgrs_LocationInfoAct.this.startActivity(new Intent(pgrs_LocationInfoAct.this, pgrs_GPSTimeActivityActivity.class));
            }
        });

        findViewById(R.id.iv_compass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgrs_LocationInfoAct.this.startActivity(new Intent(pgrs_LocationInfoAct.this, pgrs_CompassActivityActivity.class));
            }
        });
    }

    public void onRestart() {
        super.onRestart();
    }

    public void onResume() {
        super.onResume();
    }
}
