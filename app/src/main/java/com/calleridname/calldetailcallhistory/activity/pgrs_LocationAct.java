package com.calleridname.calldetailcallhistory.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class pgrs_LocationAct extends pgrs_LocationBaseActivity implements OnMapReadyCallback {

    LinearLayout f27777a;
    ImageView f27778b;
    FrameLayout f27779c;
    TextView iv_latti;
    TextView iv_longi;
    TextView iv_CurrentCity;
    TextView iv_Current_State;
    TextView iv_Current_Country;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    public pgrs_GPSTracker gpsTracker;

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

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.LocationAct$a */
    public class C1661a implements View.OnClickListener {
        public C1661a() {
        }

        public void onClick(View view) {
            pgrs_LocationAct.this.onBackPressed();
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
        setContentView(R.layout.pgrs_activity_location);

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        iv_Current_Country = findViewById(R.id.iv_Current_Country);
        iv_Current_State = findViewById(R.id.iv_Current_State);
        iv_CurrentCity = findViewById(R.id.iv_CurrentCity);
        iv_longi = findViewById(R.id.iv_longi);
        iv_latti = findViewById(R.id.iv_latti);
        gpsTracker = new pgrs_GPSTracker(pgrs_LocationAct.this);

        context = pgrs_LocationAct.this;

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.back).setOnClickListener(new C1661a());
        return;
    }

    public void mo7125r(Location location) {
        if (location != null) {
            Double valueOf = Double.valueOf(location.getLatitude());
            Double valueOf2 = Double.valueOf(location.getLongitude());
            TextView textView = iv_latti;
            textView.setText("" + valueOf);
            TextView textView2 = iv_longi;
            textView2.setText("" + valueOf2);

            Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);

            try {
                /**
                 * Geocoder.getFromLocation - Returns an array of Addresses
                 * that are known to describe the area immediately surrounding the given latitude and longitude.
                 */
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                String city=addresses.get(0).getLocality();
                String state=addresses.get(0).getAdminArea();
                String country=addresses.get(0).getCountryName();
                iv_Current_Country.setText(country);
                iv_CurrentCity.setText(city);
                iv_Current_State.setText(state);

            } catch (IOException e) {
                //e.printStackTrace();
                Log.e("TAG", "Impossible to connect to Geocoder", e);
            }

        }
    }

    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onResume() {
        super.onResume();
        createLocationRequest();
        if (ActivityCompat.checkSelfPermission(pgrs_LocationAct.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(pgrs_LocationAct.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracker.getLocation();

            if (!gpsTracker.canGetLocation()) {
                gpsTracker.showSettingsAlert();
            }
        }
    }

}
