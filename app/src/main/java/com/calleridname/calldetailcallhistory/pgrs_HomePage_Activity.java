package com.calleridname.calldetailcallhistory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.activity.pgrs_GPSCheck_Activity;
import com.calleridname.calldetailcallhistory.activity.pgrs_LocationInfoAct;
import com.calleridname.calldetailcallhistory.activity.pgrs_NumberLocatorAct;
import com.calleridname.calldetailcallhistory.activity.pgrs_Tools_Activity;
import com.calleridname.calldetailcallhistory.bank_activity.pgrs_Banklist_Activity;
import com.calleridname.calldetailcallhistory.nearby_activity.pgrs_PlaceNotFound_Activity;
import com.calleridname.calldetailcallhistory.sim_information_activity.pgrs_SimInfo_Activity;
import com.calleridname.calldetailcallhistory.traffic_activity.pgrs_Traffic_Finder;

import java.util.ArrayList;
import java.util.List;

public class pgrs_HomePage_Activity extends AppCompatActivity {
    LinearLayout Btn_Bankinfo;
    LinearLayout Btn_Locationinfo;
    LinearLayout Btn_Mobiletools;
    LinearLayout Btn_Nearbyplace;
    LinearLayout Btn_NumberSearch;
    LinearLayout Btn_SimInfo;
    LinearLayout Btn_Trafficarea;

    private String[] PERMISSIONS = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.INTERNET", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_NETWORK_STATE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.READ_PHONE_STATE", "android.permission.WRITE_SETTINGS", "android.permission.CALL_PHONE", "android.permission.FOREGROUND_SERVICE", "android.permission.CAMERA"};
    String feature;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    TemplateView admobmediumnative1;
    NativeAdLayout native_ad_container1;
    CardView q_native1;

    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.pgrs_homepage_layout);

        context = pgrs_HomePage_Activity.this;

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        admobmediumnative1 = findViewById(R.id.admobmediumnative1);
        native_ad_container1 = findViewById(R.id.native_ad_container1);
        q_native1 = findViewById(R.id.q_native1);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative1, native_ad_container1, q_native1);
        }

        if (!hasPermissions(this, this.PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, this.PERMISSIONS, 1);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            PermissionCheck();
        }
        bindview();
    }

    public boolean hasPermissions(Context context2, String... strArr) {
        if (!(Build.VERSION.SDK_INT < 23 || context2 == null || strArr == null)) {
            for (String checkSelfPermission : strArr) {
                if (ActivityCompat.checkSelfPermission(context2, checkSelfPermission) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressLint("ResourceAsColor")
    private void bindview() {
        LinearLayout imageView = (LinearLayout) findViewById(R.id.btn_numberlocation);

        this.Btn_NumberSearch = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_HomePage_Activity homePage_Activity = pgrs_HomePage_Activity.this;
                homePage_Activity.startActivity(new Intent(homePage_Activity, pgrs_NumberLocatorAct.class));
            }
        });
        LinearLayout imageView2 = (LinearLayout) findViewById(R.id.btn_nearbyplace);
        this.Btn_Nearbyplace = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (pgrs_GPSCheck_Activity.isLocationEnabled(pgrs_HomePage_Activity.this.context)) {
                    pgrs_HomePage_Activity homePage_Activity = pgrs_HomePage_Activity.this;
                    homePage_Activity.startActivity(new Intent(homePage_Activity.context, pgrs_PlaceNotFound_Activity.class));
                } else if (!((Activity) pgrs_HomePage_Activity.this.context).isFinishing()) {
                    pgrs_HomePage_Activity.this.showGPSDisabledAlertToUser();
                }
            }
        });
        LinearLayout imageView3 = (LinearLayout) findViewById(R.id.btn_trafficarea);
        this.Btn_Trafficarea = imageView3;
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (pgrs_GPSCheck_Activity.isLocationEnabled(pgrs_HomePage_Activity.this.context)) {
                    pgrs_HomePage_Activity homePage_Activity = pgrs_HomePage_Activity.this;
                    homePage_Activity.startActivity(new Intent(homePage_Activity.context, pgrs_Traffic_Finder.class));
                } else if (!((Activity) pgrs_HomePage_Activity.this.context).isFinishing()) {
                    pgrs_HomePage_Activity.this.showGPSDisabledAlertToUser();
                }

            }
        });
        LinearLayout imageView4 = (LinearLayout) findViewById(R.id.btn_simcardinfo);
        this.Btn_SimInfo = imageView4;
        imageView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_HomePage_Activity homePage_Activity = pgrs_HomePage_Activity.this;
                homePage_Activity.startActivity(new Intent(homePage_Activity, pgrs_SimInfo_Activity.class));
            }
        });
        LinearLayout imageView5 = (LinearLayout) findViewById(R.id.btn_bankinfo);
        this.Btn_Bankinfo = imageView5;
        imageView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_HomePage_Activity homePage_Activity = pgrs_HomePage_Activity.this;
                homePage_Activity.startActivity(new Intent(homePage_Activity, pgrs_Banklist_Activity.class));
            }
        });
        LinearLayout imageView6 = (LinearLayout) findViewById(R.id.btn_mobiletools);
        this.Btn_Mobiletools = imageView6;
        imageView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_HomePage_Activity homePage_Activity = pgrs_HomePage_Activity.this;
                homePage_Activity.startActivity(new Intent(homePage_Activity, pgrs_Tools_Activity.class));
            }
        });

        LinearLayout imageView8 = (LinearLayout) findViewById(R.id.btn_locationinfo);
        this.Btn_Locationinfo = imageView8;
        imageView8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_HomePage_Activity homePage_Activity = pgrs_HomePage_Activity.this;
                homePage_Activity.startActivity(new Intent(homePage_Activity.context, pgrs_LocationInfoAct.class));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void PermissionCheck() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (!addPermission(arrayList2, "android.permission.ACCESS_FINE_LOCATION")) {
            arrayList.add("Content-Location");
        }
        if (!addPermission(arrayList2, "android.permission.ACCESS_COARSE_LOCATION")) {
            arrayList.add("Location Coarse");
        }
        if (!addPermission(arrayList2, "android.permission.READ_EXTERNAL_STORAGE")) {
            arrayList.add("READ_EXTERNAL_STORAGE");
        }
        if (!addPermission(arrayList2, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            arrayList.add("Write Exteranal");
        }
        if (!addPermission(arrayList2, "android.permission.CAMERA")) {
            arrayList.add("CAMERA");
        }
        if (!addPermission(arrayList2, "android.permission.WRITE_CONTACTS")) {
            arrayList.add("WContacts");
        }
        if (arrayList2.size() > 0) {
            requestPermissions((String[]) arrayList2.toArray(new String[arrayList2.size()]), 124);
        }
    }

    private boolean addPermission(List<String> list, String str) {
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission(str) == 0) {
            return true;
        }
        list.add(str);
        return shouldShowRequestPermissionRationale(str);
    }

    public void onBackPressed() {
        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInterBack(this);
            finish();
        } else {
            finish();
        }
    }

    public void showGPSDisabledAlertToUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage(getResources().getString(R.string.no_gps)).setCancelable(false).setPositiveButton(getResources().getString(R.string.enable_gps), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                pgrs_HomePage_Activity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.compass_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog create = builder.create();
        builder.setCancelable(false);
        create.show();
    }
}
