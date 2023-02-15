package com.calleridname.calldetailcallhistory.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.Locale;

public class pgrs_FindAddressActivity extends pgrs_LocationBaseActivity implements OnMapReadyCallback {

    /* renamed from: p */
    public Geocoder f6316p;

    /* renamed from: q */
    public String f6317q = "";

    /* renamed from: r */
    public C6212d f6318r;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    private int adcountnativead;

    public pgrs_GPSTracker gpsTracker;

    private long mLastClickTime = 0;

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.FindAddressActivity$a */
    public class C1646a implements View.OnClickListener {
        public C1646a() {
        }

        public void onClick(View view) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            pgrs_FindAddressActivity.this.onBackPressed();
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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = getLayoutInflater().inflate(R.layout.pgrs_activity_find_address, (ViewGroup) null, false);
        gpsTracker = new pgrs_GPSTracker(pgrs_FindAddressActivity.this);
        admobsmallnative = inflate.findViewById(R.id.admobsmallnative);
        native_banner_ad_container = inflate.findViewById(R.id.native_banner_ad_container);
        q_native_banner = inflate.findViewById(R.id.q_native_banner);

        admobmediumnative = inflate.findViewById(R.id.admobmediumnative);
        native_ad_container = inflate.findViewById(R.id.native_ad_container);
        q_native = inflate.findViewById(R.id.q_native);

        context = pgrs_FindAddressActivity.this;
        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }


        int i = R.id.back;
        ImageView imageView = (ImageView) inflate.findViewById(R.id.back);
        if (imageView != null) {
            i = R.id.constraintLayout2;
            ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.constraintLayout2);
            if (constraintLayout != null) {
                i = R.id.iv_address;
                TextView textView = (TextView) inflate.findViewById(R.id.iv_address);
                if (textView != null) {
                    i = R.id.iv_getAdd_btn;
                    ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_getAdd_btn);
                    if (imageView2 != null) {
                        i = R.id.iv_latitude;
                        EditText editText = (EditText) inflate.findViewById(R.id.iv_latitude);
                        if (editText != null) {
                            i = R.id.iv_longi;
                            EditText editText2 = (EditText) inflate.findViewById(R.id.iv_longi);
                            if (editText2 != null) {
                                i = R.id.mainLin;
                                LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.mainLin);
                                if (linearLayout != null) {
                                    i = R.id.textView;
                                    TextView textView2 = (TextView) inflate.findViewById(R.id.textView);
                                    if (textView2 != null) {
                                        i = R.id.textView1;
                                        TextView textView3 = (TextView) inflate.findViewById(R.id.textView1);
                                        if (textView3 != null) {
                                            LinearLayout linearLayout2 = (LinearLayout) inflate;
                                            this.f6318r = new C6212d(linearLayout2, imageView, constraintLayout, textView, imageView2, editText, editText2, linearLayout, textView2, textView3);
                                            setContentView((View) linearLayout2);

                                            this.f6316p = new Geocoder(this, Locale.getDefault());
                                            this.f6318r.f27735b.setOnClickListener(new C1646a());
                                            this.f6318r.f27738e.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                                                        return;
                                                    }
                                                    if (!editText.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()) {
                                                        if (gpsTracker.isGPSEnabled) {
                                                            String address = gpsTracker.getaddtessLatlng(pgrs_FindAddressActivity.this, Double.parseDouble(editText.getText().toString()), Double.parseDouble(editText2.getText().toString()));

                                                            linearLayout.setVisibility(View.VISIBLE);
                                                            if (address != null) {
                                                                textView.setText(address);
                                                            } else {
                                                                textView.setText("Unknown");
                                                            }
                                                        }

                                                    }
                                                }
                                            });
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i)));
    }

    public final class C6212d {

        /* renamed from: a */
        public final LinearLayout f27734a;

        /* renamed from: b */
        public final ImageView f27735b;

        /* renamed from: d */
        public final TextView f27737d;

        /* renamed from: e */
        public final ImageView f27738e;

        /* renamed from: f */
        public final EditText f27739f;

        /* renamed from: g */
        public final EditText f27740g;

        /* renamed from: h */
        public final LinearLayout f27741h;


        public C6212d(LinearLayout linearLayout, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, ImageView imageView2, EditText editText, EditText editText2, LinearLayout linearLayout2, TextView textView2, TextView textView3) {
            this.f27734a = linearLayout;
            this.f27735b = imageView;
            this.f27737d = textView;
            this.f27738e = imageView2;
            this.f27739f = editText;
            this.f27740g = editText2;
            this.f27741h = linearLayout2;
        }
    }

    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onResume() {
        super.onResume();
        createLocationRequest();
        if (ActivityCompat.checkSelfPermission(pgrs_FindAddressActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(pgrs_FindAddressActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracker.getLocation();

            if (!gpsTracker.canGetLocation()) {
                gpsTracker.showSettingsAlert();
            }
        }
    }
}
