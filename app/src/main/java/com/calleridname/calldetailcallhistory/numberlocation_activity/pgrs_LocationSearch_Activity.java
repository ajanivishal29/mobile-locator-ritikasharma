package com.calleridname.calldetailcallhistory.numberlocation_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.calleridname.calldetailcallhistory.numberlocation_activity.Callblock.Call_Blocker;
import com.calleridname.calldetailcallhistory.numberlocation_activity.countrycodepicker.Country;
import com.calleridname.calldetailcallhistory.numberlocation_activity.countrycodepicker.CountryCode_Picker;
import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.slf4j.Marker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@SuppressLint("WrongConstant")

public class pgrs_LocationSearch_Activity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    static final boolean $assertionsDisabled = false;
    private static final String DB_NAME = "locatordatabase";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public static final String PREF_NAME = "MyPrefsFile";
    ImageView _block_button;
    ImageView _call_button;
    ImageView _save_button;
    ImageView _track_button;
    public ArrayList<NumberSearch_Constructor> arraylist;
    DataBaseHelper callLocatorDataBaseHelper;
    ImageView circleImageView;
    TextView comma;
    private String contactId;
    private String contact_name;
    public String country;
    public String countryCodewithPlus;
    public String countryName;
    TextView country_text;
    private String finalNumber;
    RelativeLayout icon_layout;
    double lat;
    double lng;
    private GoogleApiClient mGoogleApiClient;
    public SlidingUpPanelLayout mLayout;
    private ArrayList mList;
    private Location mLocation;
    private LocationRequest mLocationRequest;
    public GoogleMap mMap;
    SupportMapFragment mapFragment;
    TextView name;
    String number;
    public EditText numberEdit;
    TextView operator;
    private String photoUri;
    public Cursor rawQuery;
    RelativeLayout scroll_background;
    TextView state;
    private ImageView submit;
    private TextView textResult;
    public String threechar;
    RelativeLayout user_profile;
    RelativeLayout view_layout;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    private long mLastClickTime = 0;

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnected(Bundle bundle) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
            this.mLocation = lastLocation;
            if (lastLocation != null) {
                this.lat = lastLocation.getLatitude();
                this.lng = this.mLocation.getLatitude();
                this.mapFragment.getMapAsync(this);
            }
            if (this.mGoogleApiClient.isConnected()) {
                startLocationUpdates();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            LocationServices.FusedLocationApi.requestLocationUpdates(this.mGoogleApiClient, this.mLocationRequest, (LocationListener) this);
        }
    }

    private class DBAsyncTask extends AsyncTask<Void, String, String> {
        public ProgressDialog pgd;

        public DBAsyncTask(String str) {
        }

        public void onPreExecute() {
            super.onPreExecute();
            if (pgrs_LocationSearch_Activity.this.icon_layout.getVisibility() == 0) {
                pgrs_LocationSearch_Activity.this.operator.setText("");
                pgrs_LocationSearch_Activity.this.state.setText("");
                pgrs_LocationSearch_Activity.this.comma.setText("");
                pgrs_LocationSearch_Activity.this.country_text.setText("");
            }
            ProgressDialog progressDialog = new ProgressDialog(pgrs_LocationSearch_Activity.this);
            this.pgd = progressDialog;
            progressDialog.setTitle("Please Wait");
            this.pgd.setMessage("Fetching search information");
            this.pgd.show();
        }

        public String doInBackground(Void... voidArr) {
            pgrs_LocationSearch_Activity locationSearch_Activity = pgrs_LocationSearch_Activity.this;
            locationSearch_Activity.callDbNumberSearch(locationSearch_Activity.threechar);
            return null;
        }

        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (this.pgd.isShowing()) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (!pgrs_LocationSearch_Activity.this.isFinishing() && DBAsyncTask.this.pgd != null) {
                            DBAsyncTask.this.pgd.dismiss();
                            ((SupportMapFragment) pgrs_LocationSearch_Activity.this.getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(pgrs_LocationSearch_Activity.this);
                        }
                    }
                }, 1000);
            }
            try {
                if (pgrs_LocationSearch_Activity.this.rawQuery.getCount() == 0) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            try {
                                pgrs_LocationSearch_Activity.this.getcontactname(pgrs_LocationSearch_Activity.this.number);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 500);
                    Toast.makeText(pgrs_LocationSearch_Activity.this.getApplicationContext(), "Phone Number Not Found", 0).show();
                    pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                    pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                    pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    pgrs_LocationSearch_Activity.this.country_text.setText(pgrs_LocationSearch_Activity.this.countryName);
                    return;
                }
            } catch (Exception e) {
                Log.e("AAA : ", e.getMessage());
            }
            Iterator<NumberSearch_Constructor> it = pgrs_LocationSearch_Activity.this.arraylist.iterator();
            while (it.hasNext()) {
                NumberSearch_Constructor next = it.next();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        pgrs_LocationSearch_Activity.this.getcontactname(pgrs_LocationSearch_Activity.this.number);
                    }
                }, 320);
                pgrs_LocationSearch_Activity.this.icon_layout.setVisibility(0);
                pgrs_LocationSearch_Activity.this.view_layout.setVisibility(0);
                pgrs_LocationSearch_Activity.this.user_profile.setVisibility(0);
                if (pgrs_LocationSearch_Activity.this.countryCodewithPlus.equalsIgnoreCase("+1")) {
                    pgrs_LocationSearch_Activity.this.operator.setText(next.getOperator());
                    pgrs_LocationSearch_Activity.this.state.setText(next.getStateName());
                    pgrs_LocationSearch_Activity.this.CallMapMethod(next.getStateName());
                } else {
                    String stateName = next.getStateName();
                    if (stateName.equalsIgnoreCase("NewJersey")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("DistrictOfColumbia")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Connecticut")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Alabama")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Washington")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Maine")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("California")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Texas")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("NonGeographic")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("NewYork")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Pennsylvania")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Ohio")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Illinois")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Minnesota")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Louisiana")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Ontario")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Mississippi")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Georgia")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Michigan")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("BritishColumbia")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Florida")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Maryland")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("FreeportNassau")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Bridgetown")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Alabama")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Indiana")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Wisconsin")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("StJohns")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Kentucky")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Virginia")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Delaware")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Colorado")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("WestVirginia")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Saskathcewan")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Wyoming")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Nebraska")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Missouri")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Kansas")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Iowa")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("NorthCarolina")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Massachusetts")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("CharlotteAmalie")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("GeorgeTown")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Utah")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Alberta")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Oklahoma")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Montana")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Quebec")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Tennessee")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Pembroke")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Arkansas")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Arizona")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("NewMexico")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("NewBrunswick")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("Oregon")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("NewHampshire")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("SouthDakota")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else if (stateName.equalsIgnoreCase("BradesEstate")) {
                        pgrs_LocationSearch_Activity.this.operator.setText("UnKnown");
                        pgrs_LocationSearch_Activity.this.state.setVisibility(8);
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(8);
                    } else {
                        pgrs_LocationSearch_Activity.this.operator.setText(next.getOperator());
                        pgrs_LocationSearch_Activity.this.comma.setVisibility(0);
                        pgrs_LocationSearch_Activity.this.state.setVisibility(0);
                        pgrs_LocationSearch_Activity.this.state.setText(next.getStateName());
                        pgrs_LocationSearch_Activity.this.comma.setText(",");
                        pgrs_LocationSearch_Activity.this.CallMapMethod(next.getStateName());
                    }
                }
                pgrs_LocationSearch_Activity.this.country_text.setText(pgrs_LocationSearch_Activity.this.countryName);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.pgrs_search_number_layout);


        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
        }

        this.mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        createLocationRequest();
        if (this.mGoogleApiClient == null) {
            this.mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        }
        this.icon_layout = (RelativeLayout) findViewById(R.id.iconlay);
        this.view_layout = (RelativeLayout) findViewById(R.id.viewlay);
        this.user_profile = (RelativeLayout) findViewById(R.id.userprofile);
        this.scroll_background = (RelativeLayout) findViewById(R.id.scroll_background);
        this.circleImageView = (ImageView) findViewById(R.id.logoimage);
        this.name = (TextView) findViewById(R.id.name);
        this.operator = (TextView) findViewById(R.id.operator);
        this.state = (TextView) findViewById(R.id.state);
        this.comma = (TextView) findViewById(R.id.comma);
        this.country_text = (TextView) findViewById(R.id.callLocatorCountry);
        this._call_button = (ImageView) findViewById(R.id.pop_call);
        this._block_button = (ImageView) findViewById(R.id.pop_block);
        this._track_button = (ImageView) findViewById(R.id.pop_track);
        this._save_button = (ImageView) findViewById(R.id.pop_save);
        this._call_button.setOnClickListener(this);
        this._block_button.setOnClickListener(this);
        this._track_button.setOnClickListener(this);
        this._save_button.setOnClickListener(this);
        final SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        this.country = sharedPreferences.getString("COUNTRY_CODE_ISO", (String) null);
        this.countryName = sharedPreferences.getString("COUNTRY_NAME", (String) null);
        this.countryCodewithPlus = sharedPreferences.getString("COUNTRY_CODE", (String) null);
        this.callLocatorDataBaseHelper = DataBaseHelper.getInstance(this, DB_NAME);
        CountryCode_Picker countryCode_Picker = (CountryCode_Picker) findViewById(R.id.spincountry);
        String str = this.country;
        if (str != null) {
            countryCode_Picker.setCountryForNameCode(str.toUpperCase());
            countryCode_Picker.setOnCountryChangeListener(new CountryCode_Picker.OnCountryChangeListener() {
                public void onCountrySelected(Country country) {
                    edit.putString("COUNTRY_CODE_ISO", country.getIso());
                    edit.putString("COUNTRY_NAME", country.getName());
                    SharedPreferences.Editor editor = edit;
                    editor.putString("COUNTRY_CODE", Marker.ANY_NON_NULL_MARKER + country.getPhoneCode());
                    pgrs_LocationSearch_Activity.this.getApplicationContext();
                    edit.apply();
                    if (pgrs_LocationSearch_Activity.this.mMap != null) {
                        pgrs_LocationSearch_Activity.this.CallMapMethod(country.getName());
                    }
                }
            });
        }
        this.numberEdit = (EditText) findViewById(R.id.et1);
        ImageView imageView = (ImageView) findViewById(R.id.submitfinal);
        this.submit = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                pgrs_LocationSearch_Activity locationSearch_Activity = pgrs_LocationSearch_Activity.this;
                locationSearch_Activity.number = locationSearch_Activity.numberEdit.getText().toString();
                if (pgrs_LocationSearch_Activity.this.number == null || pgrs_LocationSearch_Activity.this.number.length() <= 0) {
                    Toast.makeText(pgrs_LocationSearch_Activity.this.getApplicationContext(), "Please enter mobile findlocation", 0).show();
                    return;
                }
                pgrs_LocationSearch_Activity.this.country = sharedPreferences.getString("COUNTRY_CODE_ISO", (String) null);
                pgrs_LocationSearch_Activity.this.countryName = sharedPreferences.getString("COUNTRY_NAME", (String) null);
                pgrs_LocationSearch_Activity.this.countryCodewithPlus = sharedPreferences.getString("COUNTRY_CODE", (String) null);
                if (pgrs_LocationSearch_Activity.this.countryCodewithPlus != null) {
                    if (pgrs_LocationSearch_Activity.this.countryCodewithPlus.equalsIgnoreCase("+91")) {
                        pgrs_LocationSearch_Activity locationSearch_Activity2 = pgrs_LocationSearch_Activity.this;
                        locationSearch_Activity2.threechar = locationSearch_Activity2.number.substring(0, 4);
                    } else {
                        pgrs_LocationSearch_Activity locationSearch_Activity3 = pgrs_LocationSearch_Activity.this;
                        locationSearch_Activity3.threechar = locationSearch_Activity3.number.substring(0, 3);
                    }
                }
                try {
                    ((InputMethodManager) pgrs_LocationSearch_Activity.this.getSystemService("input_method")).hideSoftInputFromWindow(pgrs_LocationSearch_Activity.this.getCurrentFocus().getWindowToken(), 0);
                } catch (Exception unused) {
                }
                new DBAsyncTask(pgrs_LocationSearch_Activity.this.threechar).execute(new Void[0]);
            }
        });
    }

    @SuppressLint("Range")
    public void callDbNumberSearch(String str) {
        ArrayList<NumberSearch_Constructor> arrayList = new ArrayList<>();
        this.arraylist = arrayList;
        arrayList.clear();
        Cursor rawQuery2 = DataBaseHelper.rawQuery("SELECT operatorname, statename,iconval,lat,lang FROM mobileNumberfinder WHERE findlocation =" + str);
        this.rawQuery = rawQuery2;
        try {
            if (rawQuery2.moveToFirst()) {
                do {
                    NumberSearch_Constructor numberSearch_Constructor = new NumberSearch_Constructor();
                    Cursor cursor = this.rawQuery;
                    numberSearch_Constructor.setOperator(cursor.getString(cursor.getColumnIndex("operatorname")));
                    Cursor cursor2 = this.rawQuery;
                    numberSearch_Constructor.stateName(cursor2.getString(cursor2.getColumnIndex("statename")));
                    Cursor cursor3 = this.rawQuery;
                    numberSearch_Constructor.iconValue(Integer.parseInt(cursor3.getString(cursor3.getColumnIndex("iconval"))));
                    Cursor cursor4 = this.rawQuery;
                    numberSearch_Constructor.setLatitude(cursor4.getString(cursor4.getColumnIndex("lat")));
                    Cursor cursor5 = this.rawQuery;
                    numberSearch_Constructor.setLongitude(cursor5.getString(cursor5.getColumnIndex("lang")));
                    this.arraylist.add(numberSearch_Constructor);
                } while (this.rawQuery.moveToNext());
            }
        } catch (Exception unused) {
        }
    }

    @SuppressLint("Range")
    public void getcontactname(String str) {
        if (str.length() > 0) {
            if (Build.VERSION.SDK_INT >= 21) {
                this.finalNumber = this.countryCodewithPlus + PhoneNumberUtils.formatNumber(str, Locale.getDefault().getCountry());
            } else {
                this.finalNumber = this.countryCodewithPlus + PhoneNumberUtils.formatNumber(str);
            }
            Cursor query = getContentResolver().query(Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, Uri.encode(this.finalNumber)), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query.moveToFirst()) {
                try {
                    this.contactId = query.getString(query.getColumnIndex("contact_id"));
                    this.contact_name = query.getString(query.getColumnIndex("display_name"));
                    Log.v("contact_name", "" + this.name);
                    this.photoUri = query.getString(query.getColumnIndex("photo_uri"));
                } catch (CursorIndexOutOfBoundsException unused) {
                }
                query.close();
                if (!TextUtils.isEmpty(this.contact_name)) {
                    this.name.setText(this.contact_name);
                    TextUtils.isEmpty(this.photoUri);
                    return;
                }
                String str2 = this.number;
                if (str2 == null) {
                    this.name.setText("");
                } else if (str2.equals("8888888888")) {
                    this.name.setText("");
                } else {
                    TextView textView = this.name;
                    textView.setText(this.countryCodewithPlus + " " + str);
                }
            } else {
                TextView textView2 = this.name;
                textView2.setText(this.countryCodewithPlus + " " + str);
            }
        }
    }

    public void CallMapMethod(String str) {
        if (Geocoder.isPresent()) {
            try {
                List<Address> fromLocationName = new Geocoder(this).getFromLocationName(str, 5);
                this.mList = new ArrayList(fromLocationName.size());
                for (Address next : fromLocationName) {
                    if (next.hasLatitude() && next.hasLongitude() && next.hasLatitude() && next.hasLongitude()) {
                        LatLng latLng = new LatLng(next.getLatitude(), next.getLongitude());
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        this.mMap.clear();
                        this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5.0f));
                        this.mMap.addMarker(markerOptions);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        LatLng latLng = new LatLng(28.7041d, 77.1025d);
        this.mMap.addMarker(new MarkerOptions().position(latLng).title(getAddress(this, this.lat, this.lng)));
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    /* access modifiers changed from: protected */
    public void createLocationRequest() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            LocationRequest locationRequest = new LocationRequest();
            this.mLocationRequest = locationRequest;
            locationRequest.setInterval(20000);
            this.mLocationRequest.setFastestInterval(10000);
            this.mLocationRequest.setPriority(100);
        }
    }

    public String getAddress(Context context2, double d, double d2) {
        try {
            List<Address> fromLocation = new Geocoder(context2, Locale.getDefault()).getFromLocation(d, d2, 1);
            if (fromLocation.size() > 0) {
                return fromLocation.get(0).getAddressLine(0);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        if (view.getId() == R.id.pop_call) {
            try {
                if (this.number != null) {
                    Intent intent = new Intent("android.intent.action.CALL");
                    intent.setData(Uri.parse("tel:" + this.number));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to Fetch Number", 1).show();
                }
            } catch (Exception unused) {
            }
        }
        if (view.getId() == R.id.pop_block) {
            startActivity(new Intent(this, Call_Blocker.class));
            finish();
        }
        if (view.getId() == R.id.pop_save) {
            Intent intent2 = new Intent("android.intent.action.INSERT");
            intent2.setType("vnd.android.cursor.dir/contact");
            intent2.putExtra("name", this.contact_name);
            intent2.putExtra("phone", this.number);
            startActivity(intent2);
            finish();
        }
        if (view.getId() == R.id.pop_track) {
            startActivity(new Intent(this, pgrs_LocationSearch_Activity.class));
            finish();
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
}
