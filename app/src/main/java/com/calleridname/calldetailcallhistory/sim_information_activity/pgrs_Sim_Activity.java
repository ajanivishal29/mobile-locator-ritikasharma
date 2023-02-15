package com.calleridname.calldetailcallhistory.sim_information_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;

public class pgrs_Sim_Activity extends AppCompatActivity {
    FrameLayout MainContainer;
    String[] data = new String[8];
    Button f111n;
    Intent intent;
    FrameLayout native_add;
    public int position;
    private RecyclerView rcDetailList;
    TextView tv_free;

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
        setContentView((int) R.layout.sim_layout);

        ((ImageView) findViewById(R.id.img_back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_Sim_Activity.this.onBackPressed();
            }
        });

        context = pgrs_Sim_Activity.this;

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

        this.f111n = (Button) findViewById(R.id.txt1);
        this.tv_free = (TextView) findViewById(R.id.tv_free);
        this.position = getIntent().getExtras().getInt("position");
        this.f111n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (pgrs_Sim_Activity.this.position == 0) {
                    pgrs_Sim_Activity.this.intent.putExtra("position", pgrs_Sim_Activity.this.position);
                    pgrs_Sim_Activity sim_Activity = pgrs_Sim_Activity.this;
                    sim_Activity.startActivity(sim_Activity.intent);
                } else if (pgrs_Sim_Activity.this.position == 1) {
                    pgrs_Sim_Activity.this.intent.putExtra("position", pgrs_Sim_Activity.this.position);
                    pgrs_Sim_Activity sim_Activity2 = pgrs_Sim_Activity.this;
                    sim_Activity2.startActivity(sim_Activity2.intent);
                } else if (pgrs_Sim_Activity.this.position == 2) {
                    pgrs_Sim_Activity.this.intent.putExtra("position", pgrs_Sim_Activity.this.position);
                    pgrs_Sim_Activity sim_Activity3 = pgrs_Sim_Activity.this;
                    sim_Activity3.startActivity(sim_Activity3.intent);
                } else if (pgrs_Sim_Activity.this.position == 3) {
                    pgrs_Sim_Activity.this.intent.putExtra("position", pgrs_Sim_Activity.this.position);
                    pgrs_Sim_Activity sim_Activity4 = pgrs_Sim_Activity.this;
                    sim_Activity4.startActivity(sim_Activity4.intent);
                } else if (pgrs_Sim_Activity.this.position == 4) {
                    pgrs_Sim_Activity.this.intent.putExtra("position", pgrs_Sim_Activity.this.position);
                    pgrs_Sim_Activity sim_Activity5 = pgrs_Sim_Activity.this;
                    sim_Activity5.startActivity(sim_Activity5.intent);
                } else if (pgrs_Sim_Activity.this.position == 5) {
                    pgrs_Sim_Activity.this.intent.putExtra("position", pgrs_Sim_Activity.this.position);
                    pgrs_Sim_Activity sim_Activity6 = pgrs_Sim_Activity.this;
                    sim_Activity6.startActivity(sim_Activity6.intent);
                } else if (pgrs_Sim_Activity.this.position == 6) {
                    pgrs_Sim_Activity.this.intent.putExtra("position", pgrs_Sim_Activity.this.position);
                    pgrs_Sim_Activity sim_Activity7 = pgrs_Sim_Activity.this;
                    sim_Activity7.startActivity(sim_Activity7.intent);
                } else if (pgrs_Sim_Activity.this.position == 7) {
                    pgrs_Sim_Activity.this.intent.putExtra("position", pgrs_Sim_Activity.this.position);
                    pgrs_Sim_Activity sim_Activity8 = pgrs_Sim_Activity.this;
                    sim_Activity8.startActivity(sim_Activity8.intent);
                } else if (pgrs_Sim_Activity.this.position == 8) {
                    pgrs_Sim_Activity.this.intent.putExtra("position", pgrs_Sim_Activity.this.position);
                    pgrs_Sim_Activity sim_Activity9 = pgrs_Sim_Activity.this;
                    sim_Activity9.startActivity(sim_Activity9.intent);
                }
            }
        });
        setView();
    }

    private void setView() {
        this.tv_free.setText(pgrs_SimInfo_Activity.dataModels.get(this.position).getName());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcDetailList);
        this.rcDetailList = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        String[] titles = pgrs_SimInfo_Activity.dataModels.get(this.position).getDatamodels1().getTitles();
        this.data = titles;
        this.rcDetailList.setAdapter(new Details_Adapter(this, titles));
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
