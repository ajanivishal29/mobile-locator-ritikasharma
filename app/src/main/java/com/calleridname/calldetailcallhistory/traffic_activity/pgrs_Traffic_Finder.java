package com.calleridname.calldetailcallhistory.traffic_activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class pgrs_Traffic_Finder extends AppCompatActivity implements AdapterView.OnItemSelectedListener, OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {
    private CheckBox mBuildingsCheckbox;
    private CheckBox mIndoorCheckbox;
    public GoogleMap mMap;
    private CheckBox mMyLocationCheckbox;
    private boolean mShowPermissionDeniedDialog = false;
    public Spinner mSpinner;
    private CheckBox mTrafficCheckbox;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.pgrs_traffic_finder_layout);

        this.mSpinner = (Spinner) findViewById(R.id.layers_spinner);
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this, R.array.layers_array, 17367048);
        createFromResource.setDropDownViewResource(17367049);
        this.mSpinner.setAdapter(createFromResource);
        this.mSpinner.setOnItemSelectedListener(this);
        this.mTrafficCheckbox = (CheckBox) findViewById(R.id.traffic);
        this.mMyLocationCheckbox = (CheckBox) findViewById(R.id.my_location);
        this.mBuildingsCheckbox = (CheckBox) findViewById(R.id.buildings);
        this.mIndoorCheckbox = (CheckBox) findViewById(R.id.indoor);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        updateMapType();
        updateTraffic();
        updateMyLocation();
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mMap.setMyLocationEnabled(true);
            this.mMap.setOnMyLocationChangeListener(myLocationChangeListener());
        }
        String str = (String) this.mSpinner.getSelectedItem();
        if (str.equals(getString(R.string.normal))) {
            this.mMap.setMapType(1);
        } else if (str.equals(getString(R.string.hybrid))) {
            this.mMap.setMapType(4);
        } else if (str.equals(getString(R.string.satellite))) {
            this.mMap.setMapType(2);
        } else if (str.equals(getString(R.string.terrain))) {
            this.mMap.setMapType(3);
        } else {
            Log.i("LDA", "Error setting layer with name " + str);
        }
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener() {
        return new GoogleMap.OnMyLocationChangeListener() {
            public void onMyLocationChange(Location location) {
                pgrs_Traffic_Finder.this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16.0f));
            }
        };
    }

    private boolean checkReady() {
        if (this.mMap != null) {
            return true;
        }
        Toast.makeText(this, R.string.map_not_ready, 0).show();
        return false;
    }

    public void onTrafficToggled(View view) {
        updateTraffic();
    }

    private void updateTraffic() {
        if (checkReady()) {
            this.mMap.setTrafficEnabled(this.mTrafficCheckbox.isChecked());
        }
    }

    public void onMyLocationToggled(View view) {
        updateMyLocation();
    }

    private void updateMyLocation() {
        if (!checkReady()) {
            return;
        }
        if (this.mMyLocationCheckbox.isChecked()) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                this.mMap.setMyLocationEnabled(true);
                return;
            }
            this.mMyLocationCheckbox.setChecked(false);
            Permission_Utils.requestPermission(this, 1, "android.permission.ACCESS_FINE_LOCATION", false);
        } else if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mMap.setMyLocationEnabled(false);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 1) {
            return;
        }
        if (!Permission_Utils.isPermissionGranted(strArr, iArr, "android.permission.ACCESS_FINE_LOCATION")) {
            this.mShowPermissionDeniedDialog = true;
        } else if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mMap.setMyLocationEnabled(true);
            this.mMyLocationCheckbox.setChecked(true);
        } else {
            Network.isNetworkAvailable(getApplicationContext());
            startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
    }

    public void onResumeFragments() {
        super.onResumeFragments();
        if (this.mShowPermissionDeniedDialog) {
            Permission_Utils.PermissionDeniedDialog.newInstance(false).show(getSupportFragmentManager(), "dialog");
            this.mShowPermissionDeniedDialog = false;
        }
    }

    public void onBuildingsToggled(View view) {
        updateBuildings();
    }

    private void updateBuildings() {
        if (checkReady()) {
            this.mMap.setBuildingsEnabled(this.mBuildingsCheckbox.isChecked());
        }
    }

    public void onIndoorToggled(View view) {
        updateIndoor();
    }

    private void updateIndoor() {
        if (checkReady()) {
            this.mMap.setIndoorEnabled(this.mIndoorCheckbox.isChecked());
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        updateMapType();
    }

    private void updateMapType() {
        if (this.mMap != null) {
            String str = (String) this.mSpinner.getSelectedItem();
            if (str.equals(getString(R.string.normal))) {
                this.mMap.setMapType(1);
            } else if (str.equals(getString(R.string.none_map))) {
                this.mMap.setMapType(0);
            } else if (str.equals(getString(R.string.satellite))) {
                this.mMap.setMapType(2);
            } else if (str.equals(getString(R.string.hybrid))) {
                this.mMap.setMapType(4);
            } else if (str.equals(getString(R.string.terrain))) {
                this.mMap.setMapType(3);
            } else {
                Log.i("LDA", "Error setting layer with name " + str);
            }
        }
    }

    public void onBackPressed() {
        finish();
    }
}
