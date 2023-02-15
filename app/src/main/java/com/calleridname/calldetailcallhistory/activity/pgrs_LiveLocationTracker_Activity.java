package com.calleridname.calldetailcallhistory.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.calleridname.calldetailcallhistory.service.GPS_Tracker;
import com.facebook.ads.NativeAdLayout;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class pgrs_LiveLocationTracker_Activity extends BaseActivity implements View.OnClickListener {
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    ImageView ivBack;
    LinearLayout ivCurrentLocation;
    double latitude = 0.0d;
    double longitude = 0.0d;
    TextView txtAddress;
    TextView txtCity;
    TextView txtCountry;
    TextView txtLatitude;
    TextView txtLongitude;
    TextView txtState;

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

    public int getContentView() {
        return R.layout.pgrs_tracker_live_location_layout;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        context = pgrs_LiveLocationTracker_Activity.this;

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        checkPermission();
    }

    public void initialization() {
        this.txtAddress = (TextView) findViewById(R.id.txtAddress);
        this.txtCity = (TextView) findViewById(R.id.txtCity);
        this.txtCountry = (TextView) findViewById(R.id.txtCountry);
        this.txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        this.txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        this.txtState = (TextView) findViewById(R.id.txtState);
        this.ivCurrentLocation = (LinearLayout) findViewById(R.id.ivCurrentLocation);
    }

    public void setViewListener() {
        this.ivCurrentLocation.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ivCurrentLocation) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.ENGLISH, "geo:%f,%f", new Object[]{Double.valueOf(this.latitude), Double.valueOf(this.longitude)}))));
        }
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") + ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            GetCurrent();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_COARSE_LOCATION")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Location permissions are required to do the task.");
            builder.setTitle("Please grant those permissions");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(pgrs_LiveLocationTracker_Activity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 123);
                }
            });
            builder.setNeutralButton("Cancel", (DialogInterface.OnClickListener) null);
            builder.create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 123);
        }
    }

    public void GetCurrent() {
        GPS_Tracker gPS_Tracker = new GPS_Tracker(this);
        if (gPS_Tracker.isgpsenabled() && gPS_Tracker.canGetLocation()) {
            this.latitude = gPS_Tracker.getLatitude();
            this.longitude = gPS_Tracker.getLongitude();
            Log.e("14/03 latitude ----> ", this.latitude + "");
            Log.e("14/03 longitude ----> ", this.longitude + "");
            if (this.latitude == 0.0d) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        pgrs_LiveLocationTracker_Activity.this.GetCurrent();
                    }
                }, 700);
            } else {
                GetCurrentAddress();
            }
        }
    }

    private void GetCurrentAddress() {
        try {
            List<Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(this.latitude, this.longitude, 1);
            if (fromLocation != null && fromLocation.size() > 0 && fromLocation.get(0).getAddressLine(0) != null && fromLocation.get(0).getAddressLine(0).length() > 0) {
                String addressLine = fromLocation.get(0).getAddressLine(0);
                String locality = fromLocation.get(0).getLocality();
                String adminArea = fromLocation.get(0).getAdminArea();
                String countryName = fromLocation.get(0).getCountryName();
                fromLocation.get(0).getPostalCode();
                fromLocation.get(0).getFeatureName();
                TextView textView = this.txtLatitude;
                textView.setText(this.latitude + "");
                TextView textView2 = this.txtLongitude;
                textView2.setText(this.longitude + "");
                if (addressLine != null) {
                    TextView textView3 = this.txtAddress;
                    textView3.setText(addressLine + "");
                }
                if (locality != null) {
                    TextView textView4 = this.txtCity;
                    textView4.setText(locality + "");
                }
                if (adminArea != null) {
                    TextView textView5 = this.txtState;
                    textView5.setText(adminArea + "");
                }
                if (adminArea != null) {
                    TextView textView6 = this.txtCountry;
                    textView6.setText(countryName + "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 123) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] + iArr[1] != 0) {
            Toast.makeText(this, "Permissions denied.", 0).show();
            return;
        }
        GetCurrent();
        Toast.makeText(this, "Permissions granted.", 0).show();
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
