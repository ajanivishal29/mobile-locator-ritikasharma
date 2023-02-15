package com.calleridname.calldetailcallhistory.bank_activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.facebook.ads.NativeAdLayout;

public class pgrs_BankInfoDetails_Activity extends AppCompatActivity {
    String balance;
    CardView balanceCard;
    TextView balanceNumber;
    CardView balancephonecall;
    TextView bankname;
    ImageView banksym;
    String customer;
    CardView customerCard;
    TextView customerNumber;
    CardView customerphonecall;
    int img;
    CardView ministatemenphonecall;
    CardView ministatementCard;
    TextView ministatementNumber;
    RelativeLayout sharebalancenumber;
    RelativeLayout sharecustomernumber;
    RelativeLayout sharestatementnumber;
    String statement;
    String title;

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

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pgrs_bankinfodetails_layout);

        this.title = getIntent().getStringExtra("title");
        this.balance = getIntent().getStringExtra("balance");
        this.customer = getIntent().getStringExtra("customer");
        this.statement = getIntent().getStringExtra("statement");
        this.img = getIntent().getIntExtra("img", 0);
        ImageView imageView = (ImageView) findViewById(R.id.bankname_view_img);
        this.banksym = imageView;
        imageView.setImageResource(this.img);
        TextView textView = (TextView) findViewById(R.id.bankname_view_title);
        this.bankname = textView;
        textView.setText(this.title);
        this.balanceNumber = (TextView) findViewById(R.id.check_balance_number);
        this.customerNumber = (TextView) findViewById(R.id.customer_care_number);
        this.ministatementNumber = (TextView) findViewById(R.id.mini_statement_number);
        this.balanceCard = (CardView) findViewById(R.id.check_balance_card);
        this.customerCard = (CardView) findViewById(R.id.customer_care_card);
        this.ministatementCard = (CardView) findViewById(R.id.mini_statement_card);
        this.balancephonecall = (CardView) findViewById(R.id.balance_phonecall);
        this.customerphonecall = (CardView) findViewById(R.id.customercare_phonecall);
        this.ministatemenphonecall = (CardView) findViewById(R.id.statement_phonecall);
        this.sharebalancenumber = (RelativeLayout) findViewById(R.id.share1);
        this.sharecustomernumber = (RelativeLayout) findViewById(R.id.share2);
        this.sharestatementnumber = (RelativeLayout) findViewById(R.id.share3);

        context = pgrs_BankInfoDetails_Activity.this;

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        this.sharebalancenumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", pgrs_BankInfoDetails_Activity.this.title + " to check the bank balance findlocation is :---" + pgrs_BankInfoDetails_Activity.this.balance);
                pgrs_BankInfoDetails_Activity bankInfoDetails_Activity = pgrs_BankInfoDetails_Activity.this;
                bankInfoDetails_Activity.startActivity(Intent.createChooser(intent, bankInfoDetails_Activity.getResources().getText(R.string.app_name)));

            }
        });
        this.sharecustomernumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", pgrs_BankInfoDetails_Activity.this.title + " to check the customercare findlocation is :---" + pgrs_BankInfoDetails_Activity.this.customer);
                pgrs_BankInfoDetails_Activity bankInfoDetails_Activity = pgrs_BankInfoDetails_Activity.this;
                bankInfoDetails_Activity.startActivity(Intent.createChooser(intent, bankInfoDetails_Activity.getResources().getText(R.string.app_name)));
            }
        });
        this.sharestatementnumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", pgrs_BankInfoDetails_Activity.this.title + " to check the ministatement findlocation is :---" + pgrs_BankInfoDetails_Activity.this.statement);
                pgrs_BankInfoDetails_Activity bankInfoDetails_Activity = pgrs_BankInfoDetails_Activity.this;
                bankInfoDetails_Activity.startActivity(Intent.createChooser(intent, bankInfoDetails_Activity.getResources().getText(R.string.app_name)));
            }
        });
        this.balanceNumber.setText(this.balance);
        this.customerNumber.setText(this.customer);
        this.ministatementNumber.setText(this.statement);
        this.balancephonecall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_BankInfoDetails_Activity bankInfoDetails_Activity = pgrs_BankInfoDetails_Activity.this;
                bankInfoDetails_Activity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + pgrs_BankInfoDetails_Activity.this.balance)));
            }
        });
        this.customerphonecall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_BankInfoDetails_Activity bankInfoDetails_Activity = pgrs_BankInfoDetails_Activity.this;
                bankInfoDetails_Activity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + pgrs_BankInfoDetails_Activity.this.customer)));
            }
        });
        this.ministatemenphonecall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_BankInfoDetails_Activity bankInfoDetails_Activity = pgrs_BankInfoDetails_Activity.this;
                bankInfoDetails_Activity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + pgrs_BankInfoDetails_Activity.this.statement)));
            }
        });
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
