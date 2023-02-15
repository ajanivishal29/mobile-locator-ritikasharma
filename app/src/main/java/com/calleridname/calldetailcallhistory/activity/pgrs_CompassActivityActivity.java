package com.calleridname.calldetailcallhistory.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;

public class pgrs_CompassActivityActivity extends pgrs_LocationBaseActivity implements SensorEventListener, OnMapReadyCallback {

    /* renamed from: p */
    public float f6300p = 0.0f;

    /* renamed from: q */
    public SensorManager f6301q;

    /* renamed from: r */
    public C6211c f6302r;

    /* renamed from: s */
    public Sensor f6303s;

    /* renamed from: t */
    public Sensor f6304t;

    /* renamed from: u */
    public float[] f6305u;

    /* renamed from: v */
    public float[] f6306v;

    /* renamed from: w */
    public Location f6307w;

    /* renamed from: x */
    public GoogleApiClient f6308x;
    public double lattitude;
    public double longitude;
    public pgrs_GPSTracker gpsTracker;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

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
//        Log.d("calldetailcallhistory", googleMap.toString());
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.CompassActivityActivity$a */
    public class C1642a implements View.OnClickListener {
        public C1642a() {
        }

        public void onClick(View view) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            pgrs_CompassActivityActivity.this.onBackPressed();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onBackPressed() {
        finish();
    }


    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = getLayoutInflater().inflate(R.layout.pgrs_activity_compass_activity, (ViewGroup) null, false);

        gpsTracker = new pgrs_GPSTracker(pgrs_CompassActivityActivity.this);

        admobsmallnative = inflate.findViewById(R.id.admobsmallnative);
        native_banner_ad_container = inflate.findViewById(R.id.native_banner_ad_container);
        q_native_banner = inflate.findViewById(R.id.q_native_banner);

        admobmediumnative = inflate.findViewById(R.id.admobmediumnative);
        native_ad_container = inflate.findViewById(R.id.native_ad_container);
        q_native = inflate.findViewById(R.id.q_native);

        context = pgrs_CompassActivityActivity.this;
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
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.constraintLayout);
            if (linearLayout != null) {
                i = R.id.imageViewCompass;
                ImageView imageView2 = (ImageView) inflate.findViewById(R.id.imageViewCompass);
                if (imageView2 != null) {
                    i = R.id.iv_latitude;
                    TextView textView = (TextView) inflate.findViewById(R.id.iv_latitude);
                    if (textView != null) {
                        i = R.id.iv_longi;
                        TextView textView2 = (TextView) inflate.findViewById(R.id.iv_longi);
                        if (textView2 != null) {
                            i = R.id.relativeLayout;
                            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.relativeLayout);
                            if (relativeLayout != null) {
                                RelativeLayout relativeLayout2 = (RelativeLayout) inflate;
                                this.f6302r = new C6211c(relativeLayout2, imageView, linearLayout, imageView2, textView, textView2, relativeLayout);
                                setContentView((View) relativeLayout2);

                                SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                                this.f6301q = sensorManager;
                                this.f6303s = sensorManager.getDefaultSensor(1);
                                this.f6304t = this.f6301q.getDefaultSensor(2);
                                this.f6302r.f27728b.setOnClickListener(new C1642a());
                                return;
                            }
                        }
                    }
                }
            }

        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i)));
    }

    public final class C6211c {

        /* renamed from: a */
        public final RelativeLayout f27727a;

        /* renamed from: b */
        public final ImageView f27728b;

        /* renamed from: d */
        public final ImageView f27730d;

        /* renamed from: e */
        public final TextView f27731e;

        /* renamed from: f */
        public final TextView f27732f;


        public C6211c(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, ImageView imageView2, TextView textView, TextView textView2, RelativeLayout relativeLayout2) {
            this.f27727a = relativeLayout;
            this.f27728b = imageView;
            this.f27730d = imageView2;
            this.f27731e = textView;
            this.f27732f = textView2;
        }
    }

    public void onPause() {
        super.onPause();
        this.f6301q.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr;
        if (sensorEvent.sensor.getType() == 1) {
            this.f6305u = sensorEvent.values;
        }
        if (sensorEvent.sensor.getType() == 2) {
            this.f6306v = sensorEvent.values;
        }
        float[] fArr2 = this.f6305u;
        if (fArr2 != null && (fArr = this.f6306v) != null) {
            float[] fArr3 = new float[9];
            if (SensorManager.getRotationMatrix(fArr3, new float[9], fArr2, fArr)) {
                float[] fArr4 = new float[3];
                SensorManager.getOrientation(fArr3, fArr4);
                float f = -((float) Math.toDegrees((double) fArr4[0]));
                RotateAnimation rotateAnimation = new RotateAnimation(this.f6300p, f, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setDuration(210);
                rotateAnimation.setFillAfter(true);
                this.f6302r.f27730d.startAnimation(rotateAnimation);
                this.f6300p = f;
            }
        }
    }

    /* renamed from: r */
    public void mo7125r(Location location) {
        this.f6307w = location;
        if (location != null) {
            Double valueOf = Double.valueOf(location.getLatitude());
            Double valueOf2 = Double.valueOf(this.f6307w.getLongitude());
            TextView textView = this.f6302r.f27731e;
            textView.setText("" + valueOf);
            TextView textView2 = this.f6302r.f27732f;
            textView2.setText("" + valueOf2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            this.f6301q.registerListener(this, this.f6303s, 2);
            this.f6301q.registerListener(this, this.f6304t, 2);
        } catch (Exception e) {
            Log.e("TAG", "onResume:****** " + e);
        }
        createLocationRequest();
        if (ActivityCompat.checkSelfPermission(pgrs_CompassActivityActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(pgrs_CompassActivityActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracker.getLocation();

            if (!gpsTracker.canGetLocation()) {
                gpsTracker.showSettingsAlert();
            }
        }

    }

}
