package com.calleridname.calldetailcallhistory.ussd_activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.facebook.ads.NativeAdLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class pgrs_USSD_Activity extends AppCompatActivity {
    String[] Operator = {"Airtel", "Idea", "Vodafone", "Reliance Jio", "BSNL", "Tata Docomo", "Telenor"};
    ArrayList<String> list = new ArrayList<>();
    ListView listView;

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
        setContentView((int) R.layout.pgrs_ussd1_layout);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle((CharSequence) "USSD Codes");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        context = pgrs_USSD_Activity.this;

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        this.listView = (ListView) findViewById(R.id.listview);
        getPlans();
    }

    private void getPlans() {
        Volley.newRequestQueue(this).add(new StringRequest(0, getResources().getString(R.string.urlussd), new Response.Listener<String>() {
            public void onResponse(String str) {
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("plan");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        ArrayList<String> arrayList = pgrs_USSD_Activity.this.list;
                        arrayList.add(pgrs_USSD_Activity.this.Operator[i] + ": ");
                        int length = jSONArray.getJSONObject(i).getJSONArray("col").length();
                        JSONArray jSONArray2 = jSONArray.getJSONObject(i).getJSONArray("col");
                        JSONArray jSONArray3 = jSONArray.getJSONObject(i).getJSONArray("value");
                        for (int i2 = 0; i2 < length; i2++) {
                            ArrayList<String> arrayList2 = pgrs_USSD_Activity.this.list;
                            arrayList2.add(jSONArray2.getString(i2) + ":" + jSONArray3.getString(i2));
                        }
                    }
                    ListView listView = pgrs_USSD_Activity.this.listView;
                    pgrs_USSD_Activity uSSD_Activity = pgrs_USSD_Activity.this;
                    listView.setAdapter(new USSD_Adapter(uSSD_Activity, uSSD_Activity.list));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
            }
        }) {
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
