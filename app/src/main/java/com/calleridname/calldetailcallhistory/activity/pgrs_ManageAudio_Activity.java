package com.calleridname.calldetailcallhistory.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.PointerIconCompat;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.facebook.ads.NativeAdLayout;

public class pgrs_ManageAudio_Activity extends AppCompatActivity {
    ImageView ivback;
    /* access modifiers changed from: private */
//    public UnifiedNativeAd nativeAd;
    Uri uri;

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
        setContentView((int) R.layout.pgrs_audiomanager_layout);

        context = pgrs_ManageAudio_Activity.this;

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        findViewById(R.id.img_ringtone).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri actualDefaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(pgrs_ManageAudio_Activity.this, 1);
                Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
                intent.putExtra("android.intent.extra.ringtone.TYPE", 1);
                intent.putExtra("android.intent.extra.ringtone.TITLE", "Select Ringtone");
                intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", actualDefaultRingtoneUri);
                intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", true);
                intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", false);
                pgrs_ManageAudio_Activity.this.startActivityForResult(intent, 1001);

            }
        });
        findViewById(R.id.img_notification).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
                intent.putExtra("android.intent.extra.ringtone.TYPE", 2);
                intent.putExtra("android.intent.extra.ringtone.TITLE", "Select Notification Tone");
                intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", RingtoneManager.getActualDefaultRingtoneUri(pgrs_ManageAudio_Activity.this, 2));
                pgrs_ManageAudio_Activity.this.startActivityForResult(intent, 1002);
            }
        });
        findViewById(R.id.img_alarm).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
                intent.putExtra("android.intent.extra.ringtone.TYPE", 4);
                intent.putExtra("android.intent.extra.ringtone.TITLE", "Select Alarm Tone");
                intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", RingtoneManager.getActualDefaultRingtoneUri(pgrs_ManageAudio_Activity.this, 4));
                pgrs_ManageAudio_Activity.this.startActivityForResult(intent, PointerIconCompat.TYPE_HELP);
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            Uri uri2 = (Uri) intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
            this.uri = uri2;
            switch (i) {
                case 1001:
                    RingtoneManager.setActualDefaultRingtoneUri(this, 1, uri2);
                    return;
                case 1002:
                    RingtoneManager.setActualDefaultRingtoneUri(this, 2, uri2);
                    return;
                case PointerIconCompat.TYPE_HELP:
                    RingtoneManager.setActualDefaultRingtoneUri(this, 4, uri2);
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
