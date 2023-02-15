package com.calleridname.calldetailcallhistory.sim_information_activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;

import java.util.ArrayList;

public class pgrs_SimInfo_Activity extends AppCompatActivity {
    public static int abc;
    public static String[] arr = {"How To Recharge", "Main Balance Enquiry", "Message Balance Enquiry", "Net Balance Enquiry", "How to Know your findlocation", "Customer care findlocation"};
    public static ArrayList<Sim_Model> dataModels;
    public static int[] images = {R.drawable.realocation_sim_airtel128, R.drawable.realocation_sim_jio128, R.drawable.realocation_sim_aircel128, R.drawable.realocation_sim_idea128, R.drawable.realocation_sim_vodafone128, R.drawable.realocation_sim_uninor128, R.drawable.realocation_sim_docomo128, R.drawable.realocation_sim_banl128};
    public static String[] names = {"Airtel", "Jio", "Aircel", "Idea", "Vodafone", "Telenor/Uninor", "Tata Docomo", "Bsnl"};
    private static RecyclerView recyclerView;
    //    FrameLayout MainContainer;
    String[] f112n = {"*120*(16 digits code)#", "*123#", "*555#", "*123*10#/*123*11#", "*121*9#", "121 or 198"};
    String[] f113o = {"*124*(16 digits code)#", "*125#", "*111*5#and*111*12#", "*123*1#", "*1#", "121 or 198"};
    String[] f114p = {"*124*(16 digits code)#", "*111#", "*167*3#", "*125#", "*1#", "12345"};
    String[] f115q = {"*140*(16 digits code)#", "*145# or *146#", "*142#", "*111*6# or *111*6*2#", "*777*0#", "198 or 9825098250"};
    String[] f116r = {"*222*3*(16 digits code)#", "*222*2#", "*222*2#", "*123#", "*1#", "121 or 9059090590"};
    String[] f117t = {"*135*2*(16 digits code)#", "*111#", "*111*1#", "*123*1#", "*580#", "121"};
    String[] f118u = {"*123*(16 digits code)#", "*333#", "*112# then press 3", "*333# then press 2", "*1#", "1800 889 9999"};
    String[] f119v = {"*124*2*(16 digits code)#", "*123#", "*123*10# ", "*124#", "*1#", "1503 or 1800-345-1500"};
    FrameLayout native_add;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

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
        setContentView((int) R.layout.pgrs_sim_info_layout);

        ((ImageView) findViewById(R.id.img_back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_SimInfo_Activity.this.onBackPressed();
            }
        });

        context = pgrs_SimInfo_Activity.this;

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        admobmediumnative1 = findViewById(R.id.admobmediumnative1);
        native_ad_container1 = findViewById(R.id.native_ad_container1);
        q_native1 = findViewById(R.id.q_native1);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative1, native_ad_container1, q_native1);
        }

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        dataModels = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Sim_Model sim_Model = new Sim_Model();
            sim_Model.setName(names[i]);
            sim_Model.setImage(images[i]);
            Sim_Model sim_Model2 = new Sim_Model();
            if (i == 0) {
                abc = 0;
                sim_Model2.setTitles(this.f112n);
            } else if (i == 1) {
                abc = 1;
                sim_Model2.setTitles(this.f118u);
            } else if (i == 2) {
                abc = 2;
                sim_Model2.setTitles(this.f113o);
            } else if (i == 3) {
                abc = 3;
                sim_Model2.setTitles(this.f114p);
            } else if (i == 4) {
                abc = 4;
                sim_Model2.setTitles(this.f115q);
            } else if (i == 5) {
                abc = 5;
                sim_Model2.setTitles(this.f116r);
            } else if (i == 6) {
                abc = 6;
                sim_Model2.setTitles(this.f117t);
            } else if (i == 7) {
                abc = 7;
                sim_Model2.setTitles(this.f119v);
            } else if (i == 8) {
                abc = 8;
                sim_Model2.setTitles(this.f119v);
            }
            sim_Model.setDatamodels1(sim_Model2);
            dataModels.add(sim_Model);
            setView();
        }
    }

    private void setView() {
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView = recyclerView2;
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);

        recyclerView2.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new Item_Adapter(this, dataModels));
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
