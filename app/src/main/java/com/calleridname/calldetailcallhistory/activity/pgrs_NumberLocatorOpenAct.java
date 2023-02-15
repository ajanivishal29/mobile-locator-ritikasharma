package com.calleridname.calldetailcallhistory.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.facebook.ads.NativeAdLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class pgrs_NumberLocatorOpenAct extends AppCompatActivity {

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    /* renamed from: A */
    public int f6400A;

    /* renamed from: B */
    public int f6401B;

    /* renamed from: C */
    public ImageView f6402C;

    /* renamed from: D */
    public int f6403D;

    /* renamed from: E */
    public int f6404E;

    /* renamed from: F */
    public int f6405F;

    /* renamed from: G */
    public int f6406G;

    /* renamed from: H */
    public int f6407H;

    /* renamed from: I */
    public int f6408I;

    /* renamed from: p */
    public int f6409p = 8;

    /* renamed from: q */
    public int f6410q = 9;

    /* renamed from: r */
    public int f6411r = 4;

    /* renamed from: s */
    public ImageView f6412s;

    /* renamed from: t */
    public TextView f6413t;

    /* renamed from: u */
    public TextView f6414u;

    /* renamed from: v */
    public TextView f6415v;

    /* renamed from: w */
    public EditText f6416w;

    /* renamed from: x */
    public String f6417x;

    /* renamed from: y */
    public TelephonyManager f6418y;

    /* renamed from: z */
    public int f6419z;

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.NumberLocatorOpenAct$a */
    public class C1682a implements View.OnClickListener {
        public C1682a() {
        }

        public void onClick(View view) {
            pgrs_NumberLocatorOpenAct.this.onBackPressed();
        }
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.NumberLocatorOpenAct$b */
    public class C1683b implements View.OnClickListener {
        public C1683b() {
        }

        @SuppressLint({"WrongConstant"})
        public void onClick(View view) {
            if (pgrs_NumberLocatorOpenAct.this.f6416w.getText().toString().equals("")) {
                Toast.makeText(pgrs_NumberLocatorOpenAct.this, "Please enter Number", 0).show();
                return;
            }
            pgrs_NumberLocatorOpenAct numberLocatorOpenAct = pgrs_NumberLocatorOpenAct.this;
            StringBuilder w = m6209w("tel:");
            w.append(pgrs_NumberLocatorOpenAct.this.f6416w.getText().toString().trim());
            numberLocatorOpenAct.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(w.toString())));
        }
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.NumberLocatorOpenAct$c */
    public class C1684c implements View.OnClickListener {

        /* renamed from: com.loctracker.findlocation.calldetailcallhistory.NumberLocatorOpenAct$c$a */
        public class C1685a implements Runnable {

            /* renamed from: c */
            public final ProgressDialog f6423c;

            public C1685a(ProgressDialog progressDialog) {
                this.f6423c = progressDialog;
            }

            public void run() {
                String str;
                String str2;
                this.f6423c.dismiss();
                pgrs_NumberLocatorOpenAct numberLocatorOpenAct = pgrs_NumberLocatorOpenAct.this;
                String obj = numberLocatorOpenAct.f6416w.getText().toString();
                pgrs_NumberLocatorOpenAct numberLocatorOpenAct2 = pgrs_NumberLocatorOpenAct.this;
                TextView textView = numberLocatorOpenAct2.f6414u;
                TextView textView2 = numberLocatorOpenAct2.f6413t;
                StringBuilder w = m6209w("here is the findlocation length ");
                w.append(obj.length());
                Log.d("StaticFragment", w.toString());
                if (obj.length() == 11) {
                    obj = obj.substring(1, 11);
                }
                if (obj.length() == 12) {
                    obj = obj.substring(2, 12);
                }
                if (obj.length() == 13) {
                    obj = obj.substring(3, 13);
                }
                Log.d("StaticFragment", "here is the findlocation  " + obj);
                C6179b bVar = new C6179b();
                Log.d("StaticFragment", "Caller User Input " + obj);
                if (obj == null || obj.length() <= 5) {
                    str = "printing 2017 if_3";
                } else {
                    String substring = obj.substring(0, 5);
                    String substring2 = obj.substring(0, 4);
                    String K = numberLocatorOpenAct.mo7182K(numberLocatorOpenAct2, "data5.txt");
                    String K2 = numberLocatorOpenAct.mo7182K(numberLocatorOpenAct2, "data4.txt");
                    int indexOf = K.indexOf(substring);
                    if (indexOf == -1) {
                        int indexOf2 = K2.indexOf(substring2);
                        StringBuilder y = m6211y("getting all index ", indexOf2, " ");
                        y.append(K2.length());
                        y.append(" ");
                        int i = indexOf2 + 5;
                        y.append(i);
                        y.append(" ");
                        int i2 = indexOf2 + 100;
                        y.append(i2);
                        Log.d("StaticFragment", y.toString());
                        str2 = K2.substring(i, i2);
                    } else {
                        str2 = K.substring(indexOf + 6, indexOf + 100);
                    }
                    bVar.mo16773a(str2);
                    String str3 = bVar.mo16773a(str2).f27676b;
                    String str4 = bVar.mo16773a(str2).f27675a;
                    StringBuilder w2 = m6209w("printing 2017 ");
                    w2.append(bVar.mo16773a(str2).f27675a);
                    Log.d("StaticFragment", w2.toString());
                    Log.d("StaticFragment", "chek status i operator" + str4);
                    if (str3.length() < 2 || str4.length() < 2) {
                        str = "printing 2017 if_1";
                    } else {
                        Log.d("StaticFragment", "printing 2017 if_2");
                        Log.d("StaticFragment", "cheack calldetailcallhistory " + bVar.mo16773a(str2).f27675a);
                        textView2.setText("\t\t" + bVar.mo16773a(str2).f27675a);
                        TextView textView3 = numberLocatorOpenAct.f6415v;
                        StringBuilder w3 = m6209w("\t\t");
                        w3.append(numberLocatorOpenAct.f6416w.getText().toString());
                        textView3.setText(w3.toString());
                        textView2.setText("\t\t" + bVar.mo16773a(str2).f27675a);
                        textView.setText("\t\t" + bVar.mo16773a(str2).f27676b);
                        return;
                    }
                }
                Log.d("StaticFragment", str);
                Toast.makeText(numberLocatorOpenAct, "Unknown Location", 0).show();
            }
        }

        public C1684c() {
        }

        @SuppressLint({"WrongConstant"})
        public void onClick(View view) {
            try {
                if (pgrs_NumberLocatorOpenAct.this.f6416w.getText().toString().length() == 10) {
                    ((InputMethodManager) pgrs_NumberLocatorOpenAct.this.getSystemService("input_method")).hideSoftInputFromWindow(pgrs_NumberLocatorOpenAct.this.getCurrentFocus().getWindowToken(), 0);
                    ProgressDialog progressDialog = new ProgressDialog(pgrs_NumberLocatorOpenAct.this);
                    progressDialog.show();
                    progressDialog.setTitle("Please Wait...");
                    progressDialog.setProgressStyle(0);
                    progressDialog.setMessage("Tracking Number...");
                    new Handler().postDelayed(new C1685a(progressDialog), 2000);
                    return;
                }
                Toast.makeText(pgrs_NumberLocatorOpenAct.this, R.string.Toast_on_enter_MobileNumber, 0).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public pgrs_NumberLocatorOpenAct() {
        new ArrayList();
        this.f6419z = 5;
        this.f6400A = 1;
        this.f6401B = 7;
        this.f6403D = 1;
        this.f6404E = 2;
        this.f6405F = 10;
        this.f6406G = 11;
        this.f6407H = 3;
        this.f6408I = 6;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgrs_activity_number_locator_open);

        String str;
        String str2;
        int i = 0;
        Log.d("StaticFragment", "print log");
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        this.f6418y = telephonyManager;
        telephonyManager.getSimOperatorName();
        String simOperatorName = this.f6418y.getSimOperatorName();
        Log.d("StaticFragment", " CURRENT_PROVIDER " + simOperatorName);
        String lowerCase = simOperatorName.toLowerCase();
        if (lowerCase.contains("airtel")) {
            this.f6403D = this.f6400A;
            str = "AIRTEL";
        } else if (lowerCase.contains("idea")) {
            this.f6403D = this.f6404E;
            str = "IDEA";
        } else if (lowerCase.contains("relia")) {
            this.f6403D = this.f6407H;
            str = "Relience";
        } else if (lowerCase.contains("vodaf")) {
            this.f6403D = this.f6411r;
            str = "Vodafone";
        } else if (lowerCase.contains("aircel")) {
            this.f6403D = this.f6419z;
            str = "Aircel";
        } else if (lowerCase.contains("tata")) {
            this.f6403D = this.f6408I;
            str = "TATA";
        } else if (lowerCase.contains("bsnl")) {
            this.f6403D = this.f6401B;
            str = "BSNL";
        } else if (lowerCase.contains("telenor")) {
            this.f6403D = this.f6409p;
            str = "TELENOR";
        } else if (lowerCase.contains("videocon")) {
            this.f6403D = this.f6410q;
            str = "VIDEOCON";
        } else if (lowerCase.contains("mtnl")) {
            this.f6403D = this.f6405F;
            str = "MTNL";
        } else if (lowerCase.contains("mts")) {
            this.f6403D = this.f6406G;
            str = "MTS";
        } else {
            StringBuilder w = m6209w(" CURRENT_PROVIDER ");
            w.append(this.f6403D);
            Log.d("StaticFragment", w.toString());
            str = "JIO";
        }
        Log.d("StaticFragment", "<<<network  " + str);
        ImageView imageView = (ImageView) findViewById(R.id.back);
        this.f6402C = imageView;
        imageView.setOnClickListener(new C1682a());

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        context = pgrs_NumberLocatorOpenAct.this;

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        this.f6412s = (ImageView) findViewById(R.id.btn_launch);
        this.f6416w = (EditText) findViewById(R.id.et_phone);
        this.f6415v = (TextView) findViewById(R.id.mobileNumber);
        this.f6414u = (TextView) findViewById(R.id.network);
        this.f6413t = (TextView) findViewById(R.id.location);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("arraynumber");
        Log.d("StaticFragment", "getvalue findlocation " + stringExtra);

        if (stringExtra != null) {
            if (stringExtra.trim().length() == 13) {
                str2 = stringExtra.trim();
                i = 3;
            } else {
                int length = stringExtra.trim().length();
                str2 = stringExtra.trim();
                if (length == 11) {
                    i = 1;
                }
                this.f6417x = str2;
            }
            str2 = str2.substring(i);
            this.f6417x = str2;
        }
        String stringExtra2 = intent.getStringExtra("Location : calldetailcallhistory");
        intent.getStringExtra("Mobile Number : arraynumber");
        intent.getStringExtra("state : state");
        ((EditText) findViewById(R.id.et_phone)).setText(this.f6417x);
        Log.d("StaticFragment", "getlocation in 2nd aCTIVITY" + stringExtra2);
        LocationManager locationManager = (LocationManager) getSystemService("calldetailcallhistory");
        this.f6412s.setOnClickListener(new C1684c());
        Log.d("StaticFragment", "show on map issue 1");
        findViewById(R.id.tvCallNow).setOnClickListener(new C1683b());
    }

    /* renamed from: K */
    public final String mo7182K(Context context, String str) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
            boolean z = true;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                if (z) {
                    z = false;
                } else {
                    sb.append(10);
                }
                sb.append(readLine);
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
            } catch (IOException e) {
                Log.d("StaticFragment", "sub ex 2 " + e);
            }
            return sb2;
        } catch (IOException e2) {
            StringBuilder w = m6209w("m25a: ");
            w.append(e2.getMessage());
            Log.e("TAG", w.toString());
            return null;
        }
    }

    public static StringBuilder m6209w(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder m6211y(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        return sb;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInterBack(this);
            finish();
        } else {
            finish();
        }
    }

    public void onRestart() {
        super.onRestart();
    }
}
