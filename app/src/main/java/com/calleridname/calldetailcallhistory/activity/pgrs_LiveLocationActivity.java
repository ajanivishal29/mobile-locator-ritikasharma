package com.calleridname.calldetailcallhistory.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.common.api.GoogleApiClient;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class pgrs_LiveLocationActivity extends pgrs_LocationBaseActivity implements OnMapReadyCallback {

    /* renamed from: p */
    public C6215g f6355p;

    public GoogleApiClient f6356q;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    public pgrs_GPSTracker gpsTracker;

    private long mLastClickTime = 0;

    public void onLocationChanged(Location location) {

        if (location != null || !location.equals("")) {
            Log.d("calldetailcallhistory", location.toString());
            Log.d("TAG", "Location IS" + location);
            mo7125r(location);
        }
        super.onLocationChanged(location);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }


    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.LiveLocationActivity$a */
    public class C1656a implements View.OnClickListener {
        public C1656a() {
        }

        public void onClick(View view) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            pgrs_LiveLocationActivity.this.onBackPressed();
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
        View inflate = getLayoutInflater().inflate(R.layout.pgrs_activity_live_location, (ViewGroup) null, false);

        gpsTracker = new pgrs_GPSTracker(pgrs_LiveLocationActivity.this);

        admobsmallnative = inflate.findViewById(R.id.admobsmallnative);
        native_banner_ad_container = inflate.findViewById(R.id.native_banner_ad_container);
        q_native_banner = inflate.findViewById(R.id.q_native_banner);

        admobmediumnative = inflate.findViewById(R.id.admobmediumnative);
        native_ad_container = inflate.findViewById(R.id.native_ad_container);
        q_native = inflate.findViewById(R.id.q_native);

        context = pgrs_LiveLocationActivity.this;
        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        int i = R.id.back;
        ImageView imageView = (ImageView) inflate.findViewById(R.id.back);
        if (imageView != null) {
                i = R.id.constraintLayout;
            RelativeLayout linearLayout = (RelativeLayout) inflate.findViewById(R.id.constraintLayout);
                if (linearLayout != null) {
                    i = R.id.iv_address;
                    TextView textView = (TextView) inflate.findViewById(R.id.iv_address);
                    if (textView != null) {
                        i = R.id.iv_latitude;
                        TextView textView2 = (TextView) inflate.findViewById(R.id.iv_latitude);
                        if (textView2 != null) {
                            i = R.id.iv_longi;
                            TextView textView3 = (TextView) inflate.findViewById(R.id.iv_longi);
                            if (textView3 != null) {
                                    ConstraintLayout constraintLayout = (ConstraintLayout) inflate;
                                    this.f6355p = new C6215g(constraintLayout, imageView, linearLayout, textView, textView2, textView3);
                                    setContentView((View) constraintLayout);

                                    this.f6355p.f27754b.setOnClickListener(new C1656a());
                                    return;
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i)));
    }

    @SuppressLint({"MissingSuperCall"})
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
//            this.f6357r.mo15193f().mo15246b(new C6175f(this));
        }
    }

    public void onRestart() {
        super.onRestart();

    }

    public final class C6215g {

        /* renamed from: a */
        public final ConstraintLayout f27753a;

        /* renamed from: b */
        public final ImageView f27754b;


        /* renamed from: d */
        public final TextView f27756d;

        /* renamed from: e */
        public final TextView f27757e;

        /* renamed from: f */
        public final TextView f27758f;


        public C6215g(ConstraintLayout constraintLayout, ImageView imageView, RelativeLayout linearLayout, TextView textView, TextView textView2, TextView textView3) {
            this.f27753a = constraintLayout;
            this.f27754b = imageView;
            this.f27756d = textView;
            this.f27757e = textView2;
            this.f27758f = textView3;
        }
    }
    /* renamed from: r */
    public void mo7125r(Location location) {
        if (location != null) {
            Double valueOf = Double.valueOf(location.getLatitude());
            Double valueOf2 = Double.valueOf(location.getLongitude());
            TextView textView = this.f6355p.f27757e;
            textView.setText("" + valueOf);
            TextView textView2 = this.f6355p.f27758f;
            textView2.setText("" + valueOf2);

            TextView address = this.f6355p.f27756d;
            if(gpsTracker.isGPSEnabled)
            {
                address.setText(gpsTracker.getAddressLine(this));
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        createLocationRequest();
        if (ActivityCompat.checkSelfPermission(pgrs_LiveLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(pgrs_LiveLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracker.getLocation();

            if (!gpsTracker.canGetLocation()) {
                gpsTracker.showSettingsAlert();
            }
        }
    }
}
