package com.calleridname.calldetailcallhistory.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

import java.text.SimpleDateFormat;

public class pgrs_GPSTimeActivityActivity extends pgrs_LocationBaseActivity implements OnMapReadyCallback {

    /* renamed from: p */
    public C6214f f6323p;

    /* renamed from: q */
    public GoogleApiClient f6324q;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;
    public pgrs_GPSTracker gpsTracker;

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

    public void onLocationChanged(Location location) {

        if (location != null || !location.equals("")) {
            Log.d("calldetailcallhistory", location.toString());
            Log.d("TAG", "Location IS" + location);
            mo7125r(location);
        }
        super.onLocationChanged(location);
    }


    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.GPSTimeActivityActivity$a */
    public class C1649a implements View.OnClickListener {
        public C1649a() {
        }

        public void onClick(View view) {
            pgrs_GPSTimeActivityActivity.this.onBackPressed();
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
        View inflate = getLayoutInflater().inflate(R.layout.pgrs_activity_gpstime_activity, (ViewGroup) null, false);
        gpsTracker = new pgrs_GPSTracker(pgrs_GPSTimeActivityActivity.this);

        admobsmallnative = inflate.findViewById(R.id.admobsmallnative);
        native_banner_ad_container = inflate.findViewById(R.id.native_banner_ad_container);
        q_native_banner = inflate.findViewById(R.id.q_native_banner);

        admobmediumnative = inflate.findViewById(R.id.admobmediumnative);
        native_ad_container = inflate.findViewById(R.id.native_ad_container);
        q_native = inflate.findViewById(R.id.q_native);

        context = pgrs_GPSTimeActivityActivity.this;
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
                i = R.id.imageViewCompass;
                ImageView imageView2 = (ImageView) inflate.findViewById(R.id.imageViewCompass);
                if (imageView2 != null) {
                    i = R.id.iv_gpsTime;
                    TextView textView = (TextView) inflate.findViewById(R.id.iv_gpsTime);
                    if (textView != null) {
                        i = R.id.iv_gpsWeek;
                        TextView textView2 = (TextView) inflate.findViewById(R.id.iv_gpsWeek);
                        if (textView2 != null) {
                            LinearLayout linearLayout2 = (LinearLayout) inflate;
                            this.f6323p = new C6214f(linearLayout, imageView, linearLayout2, imageView2, textView, textView2);
                            setContentView((View) linearLayout2);

                            this.f6323p.f27748b.setOnClickListener(new C1649a());

                            return;
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i)));
    }

    public final class C6214f {

        /* renamed from: a */
        public final RelativeLayout f27747a;

        /* renamed from: b */
        public final ImageView f27748b;

        /* renamed from: d */
        public final TextView f27750d;

        /* renamed from: e */
        public final TextView f27751e;

        public C6214f(RelativeLayout linearLayout, ImageView imageView, LinearLayout linearLayout2, ImageView imageView2, TextView textView, TextView textView2) {
            this.f27747a = linearLayout;
            this.f27748b = imageView;
            this.f27750d = textView;
            this.f27751e = textView2;
        }
    }

    @SuppressLint({"MissingSuperCall"})
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
        }
    }

    public void onRestart() {
        super.onRestart();
    }

    /* renamed from: r */
    public void mo7125r(Location location) {
        if (location != null) {
            String format = new SimpleDateFormat("HH:mm:ss").format(Long.valueOf(location.getTime()));
            TextView textView = this.f6323p.f27750d;
            textView.setText("" + format);
            String format2 = new SimpleDateFormat("SSSS").format(Long.valueOf(location.getTime()));
            TextView textView2 = this.f6323p.f27751e;
            textView2.setText("" + format2);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        createLocationRequest();
        if (ActivityCompat.checkSelfPermission(pgrs_GPSTimeActivityActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(pgrs_GPSTimeActivityActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracker.getLocation();

            if (!gpsTracker.canGetLocation()) {
                gpsTracker.showSettingsAlert();
            }
        }
    }
}
