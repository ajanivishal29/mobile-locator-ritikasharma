package com.calleridname.calldetailcallhistory.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.facebook.ads.NativeAdLayout;

import java.util.HashMap;
import java.util.Objects;

public class pgrs_IsdCodesAct extends AppCompatActivity {

    /* renamed from: G */
    public static String[] f6327G = {"7 840", "93", "358 18", "355", "213", "1 684", "376", "244", "1 264", "672", "1 268", "54", "374", "297", "247", "61", "672", "43", "994", "1 242", "973", "880", "1 246", "1 268", "375", "32", "501", "229", "1 441", "975", "591", "599 7", "387", "267", "55", "246", "1 284", "673", "359", "226", "95", "257", "855", "237", "1", "238", "599 3", "1 345", "236", "235", "64", "56", "86", "61", "61", "57", "269", "242", "243", "682", "506", "225", "385", "53", "53 99", "599 9", "357", "420", "243", "45", "246", "253", "1 767", "1 809", "670", "56", "593", "20", "503", "881 2", "882 13", "240", "291", "372", "251", "500", "298", "679", "358", "33", "596", "594", "689", "241", "220", "970", "995", "49", "233", "350", "30", "299", "1 473", "590", "1 671", "502", "44", "224", "245", "592", "509", "39", "504", "852", "36", "354", "91", "62", "98", "964", "353", "44", "972", "39", "225", "1 876", "81", "44", "962", "77", "254", "686", "381", "965", "996", "856", "371", "961", "266", "231", "218", "423", "370", "352", "853", "389", "261", "265", "60", "960", "223", "356", "692", "596", "222", "230", "262", "52", "691", "1 808", "373", "377", "976", "382", "1 664", "212", "258", "264", "674", "977", "31", "1 869", "687", "64", "505", "227", "234", "683", "672", "850", "1 670", "47", "968", "92", "680", "970", "507", "675", "595", "51", "63", "64", "48", "351", "1 787", "974", "242", "40", "7", "250", "599 4", "590", "290", "1 869", "1 758", "590", "508", "1 784", "685", "378", "239", "966", "221", "381", "248", "232", "65", "421", "386", "677", "252", "27", "82", "995 34", "211", "34", "94", "249", "597", "47 79", "268", "46", "41", "963", "886", "992", "255", "66", "670", "228", "690", "676", "1 868", "290 8", "216", "90", "993", "1 649", "688", "256", "380", "971", "44", "1", "598", "1 340", "998", "678", "58", "84", "1 808", "681", "970", "967", "260", "255", "263"};

    /* renamed from: H */
    public static String[] f6328H = {"Abkhazia", "Afghanistan", "Aland Islands", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Ascension", "Australia", "Australian External Territories", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Barbuda", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bonaire", "Bosinia and Herzegovina", "Botswana", "Brazil", "British indian ocean Trritory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Caribbean Netherlands", "Cayman Islands", "Central African Republic", "Chad", "Chatham Island, New Zealand", "Chile", "China", "Christmas Island", "Cocos(Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, Democratic Republic of the (Zaire)", "Cook Islands", "Costa Rica", "Cote tvbycountry'Ivoire", "Croatia", "Cuba", "Guantanamo Bay", "Curacao", "Cyprus", "Czech Republic", "Democratic Republic of the congo", "Denmark", "Diego Garcia", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Easter Island", "Ecuador", "Egypt", "El salvador", "Ellipso", "EMSAT", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland islands", "Faroe Islands", "Fiji", "Finland", "France", "French Antilles", "French Guiana", "French Polynesia", "Gabon", "Gambia", "Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy See(Vatican City)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Midway Island, USA", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "Nevis", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk island", "North Korea", "Nothan Mariana Islands", "Norway", "Oman", "Pakisthan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paruguay", "Peru", "Philippines", "Pitcairn islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Republic of the congo", "Romania", "Russia", "Rwanda", "Saba", "Saint Barthelemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Sauidi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon islands", "Somalia", "South Africa", "South Korea", "South Ossetia", "South Sudan", "Spain", "SriLanka", "Sudan", "Suriname", "Svalbard", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tristan da Cunha", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "US Virgin Islands", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Wake Island, USA", "Wallis and Futuna", "West Bank", "Yemen", "Zambia", "Zanzibar", "Zimbabwe"};

    /* renamed from: A */
    public TextView f6329A;

    /* renamed from: B */
    public TextView f6330B;

    /* renamed from: C */
    public boolean f6331C = true;

    /* renamed from: D */
    public HashMap<String, String> f6332D;

    /* renamed from: E */
    public String f6333E;

    /* renamed from: F */
    public String f6334F;

    /* renamed from: p */
    public boolean f6335p;

    /* renamed from: q */
    public View f6336q;

    /* renamed from: r */
    public View f6337r;

    /* renamed from: s */
    public TextView f6338s;

    /* renamed from: t */
    public TextView f6339t;

    /* renamed from: u */
    public AutoCompleteTextView f6340u;

    /* renamed from: v */
    public ImageView f6341v;

    /* renamed from: w */
    public ConstraintLayout f6342w;

    /* renamed from: x */
    public ImageView f6343x;

    /* renamed from: y */
    public TextView f6344y;

    /* renamed from: z */
    public TextView f6345z;


    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    private int adcountnativead;

    private long mLastClickTime = 0;

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.IsdCodesAct$a */
    public class C1650a implements View.OnClickListener {
        public C1650a() {
        }

        public void onClick(View view) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            pgrs_IsdCodesAct.this.onBackPressed();
        }
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.IsdCodesAct$b */
    public class C1651b implements View.OnClickListener {

        /* renamed from: c */
        public pgrs_IsdCodesAct f6347c;

        public C1651b(pgrs_IsdCodesAct isdCodesAct) {
            this.f6347c = isdCodesAct;
        }

        @SuppressLint({"WrongConstant"})
        public void onClick(View view) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            pgrs_IsdCodesAct isdCodesAct = pgrs_IsdCodesAct.this;
            isdCodesAct.f6331C = true;
            isdCodesAct.f6336q.setVisibility(0);
            pgrs_IsdCodesAct.this.f6337r.setVisibility(8);
            pgrs_IsdCodesAct.this.f6342w.setVisibility(8);
            pgrs_IsdCodesAct.this.f6340u.setText("");
            pgrs_IsdCodesAct.this.f6340u.setHint("Enter Country Name");
            pgrs_IsdCodesAct.this.f6340u.setInputType(1);
            this.f6347c.mo7136I();
        }
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.IsdCodesAct$c */
    public class C1652c implements View.OnClickListener {

        /* renamed from: c */
        public pgrs_IsdCodesAct f6349c;

        public C1652c(pgrs_IsdCodesAct isdCodesAct) {
            this.f6349c = isdCodesAct;
        }

        @SuppressLint({"WrongConstant"})
        public void onClick(View view) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            pgrs_IsdCodesAct isdCodesAct = pgrs_IsdCodesAct.this;
            int i = 0;
            isdCodesAct.f6331C = false;
            isdCodesAct.f6337r.setVisibility(0);
            pgrs_IsdCodesAct.this.f6336q.setVisibility(8);
            pgrs_IsdCodesAct.this.f6342w.setVisibility(8);
            pgrs_IsdCodesAct.this.f6340u.setText("");
            pgrs_IsdCodesAct.this.f6340u.setInputType(2);
            pgrs_IsdCodesAct.this.f6340u.setHint("Enter ISD Code");
            pgrs_IsdCodesAct isdCodesAct2 = this.f6349c;
            Objects.requireNonNull(isdCodesAct2);
            while (true) {
                String[] strArr = pgrs_IsdCodesAct.f6327G;
                if (i < strArr.length) {
                    isdCodesAct2.f6332D.put(strArr[i], pgrs_IsdCodesAct.f6328H[i]);
                    isdCodesAct2.f6332D.size();
                    ArrayAdapter arrayAdapter = new ArrayAdapter(isdCodesAct2, R.layout.text, pgrs_IsdCodesAct.f6327G);
                    arrayAdapter.setDropDownViewResource(R.layout.text);
                    isdCodesAct2.f6340u.setThreshold(1);
                    isdCodesAct2.f6340u.setAdapter(arrayAdapter);
                    isdCodesAct2.f6340u.setOnItemClickListener(new C1654e(isdCodesAct2, isdCodesAct2));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.IsdCodesAct$d */
    public class C1653d implements View.OnClickListener {

        /* renamed from: c */
        public pgrs_IsdCodesAct f6351c;

        public C1653d(pgrs_IsdCodesAct isdCodesAct) {
            this.f6351c = isdCodesAct;
        }

        @SuppressLint({"WrongConstant"})
        public void onClick(View view) {
            pgrs_IsdCodesAct isdCodesAct = pgrs_IsdCodesAct.this;
            String[] strArr = pgrs_IsdCodesAct.f6327G;
            InputMethodManager inputMethodManager = (InputMethodManager) isdCodesAct.getSystemService("input_method");
            View currentFocus = isdCodesAct.getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(isdCodesAct);
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            pgrs_IsdCodesAct isdCodesAct2 = this.f6351c;
            boolean z = isdCodesAct2.f6331C;
            TextView textView = isdCodesAct2.f6344y;
            if (z) {
                textView.setText("Country Name : ");
                this.f6351c.f6345z.setText("ISD Code : ");
                try {
                    pgrs_IsdCodesAct isdCodesAct3 = this.f6351c;
                    isdCodesAct3.f6329A.setText(isdCodesAct3.f6334F.toString());
                    pgrs_IsdCodesAct isdCodesAct4 = this.f6351c;
                    isdCodesAct4.f6330B.setText(isdCodesAct4.f6332D.get(isdCodesAct4.f6329A.getText().toString()));
                    this.f6351c.f6342w.setVisibility(0);
                    pgrs_IsdCodesAct.this.f6335p = false;
                } catch (Exception unused) {
                    this.f6351c.f6342w.setVisibility(8);
                    this.f6351c.f6329A.setText("Unknown");
                    this.f6351c.f6330B.setText("Unknown");
                    Toast.makeText(this.f6351c, "No Data Found", 0).show();
                }
            } else {
                textView.setText("ISD Code : ");
                this.f6351c.f6345z.setText("Country Name : ");
                pgrs_IsdCodesAct isdCodesAct5 = this.f6351c;
                isdCodesAct5.f6329A.setText(isdCodesAct5.f6333E.toString());
                pgrs_IsdCodesAct isdCodesAct6 = this.f6351c;
                isdCodesAct6.f6330B.setText(isdCodesAct6.f6332D.get(isdCodesAct6.f6329A.getText().toString()));
                this.f6351c.f6342w.setVisibility(0);
                pgrs_IsdCodesAct.this.f6335p = true;
            }
            boolean z2 = pgrs_IsdCodesAct.this.f6335p;
        }
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.IsdCodesAct$e */
    public class C1654e implements AdapterView.OnItemClickListener {

        /* renamed from: c */
        public pgrs_IsdCodesAct f6353c;

        public C1654e(pgrs_IsdCodesAct isdCodesAct, pgrs_IsdCodesAct isdCodesAct2) {
            this.f6353c = isdCodesAct2;
        }

        @SuppressLint({"WrongConstant"})
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f6353c.f6342w.setVisibility(8);
            this.f6353c.f6334F = (String) adapterView.getItemAtPosition(i);
            this.f6353c.f6333E = (String) adapterView.getItemAtPosition(i);
        }
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.IsdCodesAct$f */
    public class C1655f implements AdapterView.OnItemClickListener {

        /* renamed from: c */
        public pgrs_IsdCodesAct f6354c;

        public C1655f(pgrs_IsdCodesAct isdCodesAct, pgrs_IsdCodesAct isdCodesAct2) {
            this.f6354c = isdCodesAct2;
        }

        @SuppressLint({"WrongConstant"})
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f6354c.f6342w.setVisibility(8);
            this.f6354c.f6334F = (String) adapterView.getItemAtPosition(i);
            this.f6354c.f6333E = (String) adapterView.getItemAtPosition(i);
        }
    }

    /* renamed from: I */
    public void mo7136I() {
        int i = 0;
        while (true) {
            String[] strArr = f6328H;
            if (i < strArr.length) {
                this.f6332D.put(strArr[i], f6327G[i]);
                this.f6332D.size();
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.text, f6328H);
                arrayAdapter.setDropDownViewResource(R.layout.text);
                this.f6340u.setThreshold(1);
                this.f6340u.setAdapter(arrayAdapter);
                this.f6340u.setOnItemClickListener(new C1655f(this, this));
                i++;
            } else {
                return;
            }
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
        setContentView((int) R.layout.pgrs_activity_isd_codes);
        ImageView imageView = (ImageView) findViewById(R.id.back);
        this.f6343x = imageView;
        imageView.setOnClickListener(new C1650a());

        this.f6338s = (TextView) findViewById(R.id.txttvbycountry);
        this.f6339t = (TextView) findViewById(R.id.txttvbyisd);
        this.f6336q = findViewById(R.id.tvbycountry);
        this.f6337r = findViewById(R.id.tvbyisd);
        this.f6340u = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        this.f6341v = (ImageView) findViewById(R.id.tvsearch);
        this.f6342w = (ConstraintLayout) findViewById(R.id.rel2);
        this.f6344y = (TextView) findViewById(R.id.tvhead1);
        this.f6345z = (TextView) findViewById(R.id.tvhead2);
        this.f6329A = (TextView) findViewById(R.id.tvcountryname);
        this.f6330B = (TextView) findViewById(R.id.tvisdcode);

        admobsmallnative = findViewById(R.id.admobsmallnative);
        native_banner_ad_container = findViewById(R.id.native_banner_ad_container);
        q_native_banner = findViewById(R.id.q_native_banner);

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        context = pgrs_IsdCodesAct.this;

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        this.f6332D = new HashMap<>();
        mo7136I();
        this.f6338s.setOnClickListener(new C1651b(this));
        this.f6339t.setOnClickListener(new C1652c(this));
        this.f6341v.setOnClickListener(new C1653d(this));
    }

    public void onRestart() {
        super.onRestart();
    }

    public void onResume() {
        super.onResume();
    }
}
