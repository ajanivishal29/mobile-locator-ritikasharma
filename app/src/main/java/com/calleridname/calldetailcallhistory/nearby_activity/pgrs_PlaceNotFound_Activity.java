package com.calleridname.calldetailcallhistory.nearby_activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.calleridname.calldetailcallhistory.service.GPS_Tracker;

import java.util.ArrayList;

public class pgrs_PlaceNotFound_Activity extends AppCompatActivity implements View.OnClickListener {
    public Activity activity = null;
    private ArrayList<Places> callLocatorPlacesListDataModelArrayList;
    private EditText editSearches;
    public ImageView imgeGifts;
    public Double latitude;
    public Double longitude;
    public PlaceNumberList_Adapter placesNumbersListAdapter;
    public RelativeLayout relAdview;
    private RecyclerView revPlacesList;
    private TextView txtNoPlacesAvailable;

    /* renamed from: A */
    public LinearLayout f10748A;

    /* renamed from: B */
    public LinearLayout f10749B;

    /* renamed from: C */
    public LinearLayout f10750C;

    /* renamed from: D */
    public LinearLayout f10751D;

    /* renamed from: E */
    public LinearLayout f10752E;

    /* renamed from: F */
    public LinearLayout f10753F;

    /* renamed from: G */
    public LinearLayout f10754G;

    /* renamed from: H */
    public LinearLayout f10755H;

    /* renamed from: I */
    public LinearLayout f10756I;

    /* renamed from: J */
    public LinearLayout f10757J;

    /* renamed from: K */
    public LinearLayout f10758K;

    /* renamed from: L */
    public LinearLayout f10759L;

    /* renamed from: M */
    public LinearLayout f10760M;

    /* renamed from: N */
    public LinearLayout f10761N;

    /* renamed from: O */
    public LinearLayout f10762O;

    /* renamed from: P */
    public LinearLayout f10763P;

    /* renamed from: Q */
    public LinearLayout f10764Q;

    /* renamed from: R */
    public LinearLayout f10765R;

    /* renamed from: S */
    public LinearLayout f10766S;

    /* renamed from: T */
    public LinearLayout f10767T;

    /* renamed from: U */
    public LinearLayout f10768U;

    /* renamed from: V */
    public LinearLayout f10769V;

    /* renamed from: W */
    public LinearLayout f10770W;

    /* renamed from: X */
    public LinearLayout f10771X;

    /* renamed from: Y */
    public LinearLayout f10772Y;

    /* renamed from: Z */
    public LinearLayout f10773Z;

    /* renamed from: a0 */
    public LinearLayout f10774a0;

    /* renamed from: b0 */
    public LinearLayout f10775b0;

    /* renamed from: c0 */
    public LinearLayout f10776c0;

    /* renamed from: d0 */
    public LinearLayout f10777d0;

    /* renamed from: e0 */
    public LinearLayout f10778e0;

    public String[] f10779f0 = {"delivery", "bus station", "train station", "airport", "restaurant", "bank", "atm", "hospital", "police", "lodging", "car repair", "gas station", "mosque", "hindu temple", "church", "jewelry store", "shopping mall", "bar", "spa", "beauty salon", "amusement park", "aquarium", "park", "zoo", "cafe", "dry cleaning", "pharmacy", "bakery", "doctor", "veterinary care", "dentist", "gym", "book store", "bowling alley", "car rental", "car wash", "taxi stand", "parking", "art gallery", "electronics store", "casino", "convenience store", "school", "fire station", "lawyer", "department store", "library", "liquor store", "movie theater", "museum", "night club", "pet store", "stadium", "local government office", "groceries", "car dealers", "home garden store", "clothing stores"};

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public pgrs_PlaceNotFound_Activity() {
        Double valueOf = Double.valueOf(0.0d);
        this.latitude = valueOf;
        this.longitude = valueOf;
        this.callLocatorPlacesListDataModelArrayList = new ArrayList<>();
        this.placesNumbersListAdapter = null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pgrs_place_no_list_fou_layout);
        this.activity = this;

        context = pgrs_PlaceNotFound_Activity.this;

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        this.f10748A = (LinearLayout) findViewById(R.id.layoutRestaurant);
        this.f10749B = (LinearLayout) findViewById(R.id.layoutBar);
        this.f10750C = (LinearLayout) findViewById(R.id.layoutCoffee);
        this.f10751D = (LinearLayout) findViewById(R.id.layoutDelivery);
        this.f10752E = (LinearLayout) findViewById(R.id.layoutHotel);
        this.f10753F = (LinearLayout) findViewById(R.id.layoutAtm);
        this.f10754G = (LinearLayout) findViewById(R.id.layoutBeautySalon);
        this.f10755H = (LinearLayout) findViewById(R.id.layoutBank);
        this.f10756I = (LinearLayout) findViewById(R.id.layoutDryClean);
        this.f10757J = (LinearLayout) findViewById(R.id.layoutHospital);
        this.f10758K = (LinearLayout) findViewById(R.id.layoutGas);
        this.f10759L = (LinearLayout) findViewById(R.id.layoutPharmacy);
        this.f10760M = (LinearLayout) findViewById(R.id.layoutParking);
        this.f10761N = (LinearLayout) findViewById(R.id.layoutPark);
        this.f10762O = (LinearLayout) findViewById(R.id.layoutGym);
        this.f10763P = (LinearLayout) findViewById(R.id.layoutArt);
        this.f10764Q = (LinearLayout) findViewById(R.id.layoutStadium);
        this.f10765R = (LinearLayout) findViewById(R.id.layoutNightlife);
        this.f10766S = (LinearLayout) findViewById(R.id.layoutAmusementPark);
        this.f10767T = (LinearLayout) findViewById(R.id.layoutMovie);
        this.f10768U = (LinearLayout) findViewById(R.id.layoutMuseum);
        this.f10769V = (LinearLayout) findViewById(R.id.layoutLibrary);
        this.f10770W = (LinearLayout) findViewById(R.id.layoutGroceries);
        this.f10771X = (LinearLayout) findViewById(R.id.layoutBook);
        this.f10772Y = (LinearLayout) findViewById(R.id.layoutCarDealer);
        this.f10773Z = (LinearLayout) findViewById(R.id.layoutHomeGarden);
        this.f10774a0 = (LinearLayout) findViewById(R.id.layoutClothing);
        this.f10775b0 = (LinearLayout) findViewById(R.id.layoutShoppingCenter);
        this.f10776c0 = (LinearLayout) findViewById(R.id.layoutElectronics);
        this.f10777d0 = (LinearLayout) findViewById(R.id.layoutJelwery);
        this.f10778e0 = (LinearLayout) findViewById(R.id.layoutConvenienceStore);

        this.f10748A.setOnClickListener(this);
        this.f10749B.setOnClickListener(this);
        this.f10750C.setOnClickListener(this);
        this.f10751D.setOnClickListener(this);
        this.f10752E.setOnClickListener(this);
        this.f10753F.setOnClickListener(this);
        this.f10754G.setOnClickListener(this);
        this.f10755H.setOnClickListener(this);
        this.f10756I.setOnClickListener(this);
        this.f10757J.setOnClickListener(this);
        this.f10758K.setOnClickListener(this);
        this.f10759L.setOnClickListener(this);
        this.f10760M.setOnClickListener(this);
        this.f10761N.setOnClickListener(this);
        this.f10762O.setOnClickListener(this);
        this.f10763P.setOnClickListener(this);
        this.f10764Q.setOnClickListener(this);
        this.f10765R.setOnClickListener(this);
        this.f10766S.setOnClickListener(this);
        this.f10767T.setOnClickListener(this);
        this.f10768U.setOnClickListener(this);
        this.f10769V.setOnClickListener(this);
        this.f10770W.setOnClickListener(this);
        this.f10771X.setOnClickListener(this);
        this.f10772Y.setOnClickListener(this);
        this.f10773Z.setOnClickListener(this);
        this.f10774a0.setOnClickListener(this);
        this.f10775b0.setOnClickListener(this);
        this.f10776c0.setOnClickListener(this);
        this.f10777d0.setOnClickListener(this);
        this.f10778e0.setOnClickListener(this);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        GPS_Tracker gPS_Tracker = new GPS_Tracker(this.activity);
        if (gPS_Tracker.isgpsenabled() && gPS_Tracker.canGetLocation()) {
            this.latitude = Double.valueOf(gPS_Tracker.getLatitude());
            this.longitude = Double.valueOf(gPS_Tracker.getLongitude());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutAmusementPark /*2131296700*/:
                click(20);
                return;
            case R.id.layoutArt /*2131296701*/:
                click(38);
                return;
            case R.id.layoutAtm /*2131296702*/:
                click(6);
                return;
            case R.id.layoutBank /*2131296703*/:
                click(5);
                return;
            case R.id.layoutBar /*2131296704*/:
                click(17);
                return;
            case R.id.layoutBeautySalon /*2131296705*/:
                click(19);
                return;
            case R.id.layoutBook /*2131296706*/:
                click(32);
                return;
            case R.id.layoutCarDealer /*2131296707*/:
                click(55);
                return;
            case R.id.layoutClothing /*2131296708*/:
                click(57);
                return;
            case R.id.layoutCoffee /*2131296709*/:
                click(24);
                return;
            case R.id.layoutConvenienceStore /*2131296710*/:
                click(41);
                return;
            case R.id.layoutDelivery /*2131296711*/:
                click(0);
                return;
            case R.id.layoutDryClean /*2131296712*/:
                click(25);
                return;
            case R.id.layoutElectronics /*2131296713*/:
                click(39);
                return;
            case R.id.layoutGas /*2131296715*/:
                click(11);
                return;
            case R.id.layoutGroceries /*2131296716*/:
                click(54);
                return;
            case R.id.layoutGym /*2131296717*/:
                click(31);
                return;
            case R.id.layoutHomeGarden /*2131296718*/:
                click(56);
                return;
            case R.id.layoutHospital /*2131296719*/:
                click(7);
                return;
            case R.id.layoutHotel /*2131296720*/:
                click(9);
                return;
            case R.id.layoutJelwery /*2131296721*/:
                click(15);
                return;
            case R.id.layoutLibrary /*2131296722*/:
                click(46);
                return;
            case R.id.layoutMovie /*2131296723*/:
                click(48);
                return;
            case R.id.layoutMuseum /*2131296724*/:
                click(49);
                return;
            case R.id.layoutNightlife /*2131296725*/:
                click(50);
                return;
            case R.id.layoutPark /*2131296726*/:
                click(22);
                return;
            case R.id.layoutParking /*2131296727*/:
                click(37);
                return;
            case R.id.layoutPharmacy /*2131296728*/:
                click(26);
                return;
            case R.id.layoutRestaurant /*2131296729*/:
                click(4);
                return;
            case R.id.layoutShoppingCenter /*2131296730*/:
                click(16);
                return;
            case R.id.layoutStadium /*2131296731*/:
                click(52);
                return;
            default:
                return;
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

    public void click(int i) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:%f,%f" + this.latitude + "," + this.longitude + "?q=" + f10779f0[i]));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            try {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
                Toast.makeText(this.activity, R.string.please_install_google_map, 0).show();
            }
        }
    }
}
