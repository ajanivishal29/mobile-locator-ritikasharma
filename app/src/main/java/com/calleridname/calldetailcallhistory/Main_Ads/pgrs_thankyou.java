package com.calleridname.calldetailcallhistory.Main_Ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.realocation_Privacy_PolicyActivity;
import com.calleridname.calldetailcallhistory.R;
import com.facebook.ads.NativeAdLayout;
import com.hsalf.smileyrating.SmileyRating;


public class pgrs_thankyou extends AppCompatActivity {
    public TextView later;
    public LinearLayout rating_view;
    public SmileyRating smile_rating;
    public TextView submit;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(8192);
        setContentView(R.layout.pgrs_activity_thankyou);

        context = pgrs_thankyou.this;

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {

            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("israteus", 0);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        this.rating_view = (LinearLayout) findViewById(R.id.rating);
        this.smile_rating = (SmileyRating) findViewById(R.id.smile_rating);
        if (sharedPreferences.getBoolean("rate", false)) {
            this.rating_view.setVisibility(8);
        }

        this.smile_rating.setSmileySelectedListener(new SmileyRating.OnSmileySelectedListener() {
            public void onSmileySelected(SmileyRating.Type type) {
            }
        });
        this.later = (TextView) findViewById(R.id.later);
        this.submit = (TextView) findViewById(R.id.submit);
        this.later.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_thankyou.this.rating_view.animate().alpha(0.0f);
            }
        });

        this.submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        });
        ((TextView) findViewById(R.id.exit_yes)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Adintermethod.getInstance(pgrs_thankyou.this).clearprefrence(pgrs_thankyou.this);
                finishAffinity();
            }
        });
        ((TextView) findViewById(R.id.exit_no)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(pgrs_thankyou.this, realocation_Privacy_PolicyActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
