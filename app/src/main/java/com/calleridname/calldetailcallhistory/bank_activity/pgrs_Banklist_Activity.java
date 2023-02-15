package com.calleridname.calldetailcallhistory.bank_activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;

import java.util.ArrayList;


public class pgrs_Banklist_Activity extends AppCompatActivity {
    RecyclerView banklistview;
    pgrs_Bank_Adapter mAdapter;
    ArrayList<pgrs_Bank_Modelclass> mListData;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobmediumnative1;
    NativeAdLayout native_ad_container1;
    CardView q_native1;

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
        setContentView((int) R.layout.pgrs_banlist_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bank_name);
        this.banklistview = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        this.banklistview.setHasFixedSize(true);

        context = pgrs_Banklist_Activity.this;

        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        admobmediumnative1 = findViewById(R.id.admobmediumnative1);
        native_ad_container1 = findViewById(R.id.native_ad_container1);
        q_native1 = findViewById(R.id.q_native1);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative1, native_ad_container1, q_native1);
        }

        ArrayList<pgrs_Bank_Modelclass> arrayList = new ArrayList<>();
        this.mListData = arrayList;
        arrayList.add(new pgrs_Bank_Modelclass("Axis Bank", "18004195959", "18002095577", R.drawable.realocation_bank_axis, "18004196969"));
        this.mListData.add(new pgrs_Bank_Modelclass("Allahabad Bank", "09224160150", "1800226061", R.drawable.realocation_bank_allhabad, "09224150150"));
        this.mListData.add(new pgrs_Bank_Modelclass("Bank Of Baroda", "09223011311", "18001024455", R.drawable.realocation_bank_bank_of_baroda, "8468001111"));
        this.mListData.add(new pgrs_Bank_Modelclass("Bandhan Bank", "9223008666", "1800 258 8181", R.drawable.realocation_bank_bandhanbank, "9223008777"));
        this.mListData.add(new pgrs_Bank_Modelclass("Bharatiya Mahila Bank", "09212438888", "01147472100", R.drawable.realocation_bank_bharatiya_mahila, "9212438888"));
        this.mListData.add(new pgrs_Bank_Modelclass("Central Bank Of India", "09222250000", "18002001911", R.drawable.realocation_bank_central_bank_of_india, "9555144441"));
        this.mListData.add(new pgrs_Bank_Modelclass("Canara Bank", "09015483483", "18004250018", R.drawable.realocation_bank_canara, "09015734734"));
        this.mListData.add(new pgrs_Bank_Modelclass("Dena Bank", "09289356677", "18002336427", R.drawable.realocation_bank_dena_bank, "09278656677"));
        this.mListData.add(new pgrs_Bank_Modelclass("Dhanalakshmi Bank", "08067747700", "18004251747", R.drawable.realocation_bank_dhanlaxmi_bank, "08067747711"));
        this.mListData.add(new pgrs_Bank_Modelclass("DCB Bank", "7506660011", "18002095363", R.drawable.realocation_bank_dcbbank, "07506660022"));
        this.mListData.add(new pgrs_Bank_Modelclass("Federal Bank", "8431900900", "18004251199", R.drawable.realocation_bank_federal, "8431600600"));
        this.mListData.add(new pgrs_Bank_Modelclass("HDFC Bank", "18002703333", "18004254332", R.drawable.realocation_bank_hdfc_bank, "18002703355"));
        this.mListData.add(new pgrs_Bank_Modelclass("IDBI Bank", "18008431122", "18002001947", R.drawable.realocation_bank_idbi_bank, "18008431133"));
        this.mListData.add(new pgrs_Bank_Modelclass("ICICI Bank", "9594612612", "18001038181", R.drawable.realocation_bank_icici, "9594613613"));
        this.mListData.add(new pgrs_Bank_Modelclass("IndusInd Bank", "18002741000", "1860 500 5004", R.drawable.realocation_bank_indusindbank, "9212299955"));
        this.mListData.add(new pgrs_Bank_Modelclass("ING Vysya Bank", "09266292666", "18004209900", R.drawable.realocation_bank_ing_bank, "09266292665"));
        this.mListData.add(new pgrs_Bank_Modelclass("Karur Vysya Bank", "09266292666", "18602001916", R.drawable.realocation_bank_karur_vysya_bank, "09266292665"));
        this.mListData.add(new pgrs_Bank_Modelclass("Karnataka Bank", "18004251445", "18004251444", R.drawable.realocation_bank_karnataka_bank, "18004251446"));
        this.mListData.add(new pgrs_Bank_Modelclass("Oriental Bank of Commerce", "08067205757", "1800 180 1235", R.drawable.realocation_bank_orientalbankofcommerce, "08067205767"));
        this.mListData.add(new pgrs_Bank_Modelclass("Punjab National Bank", "18001802223", "18001802222", R.drawable.realocation_bank_punjab_national_bank, "01202490000"));
        this.mListData.add(new pgrs_Bank_Modelclass("RBL Bank", "1800 419 0610", "1800 123 8040", R.drawable.realocation_bank_rblbank, "1800 419 0610"));
        this.mListData.add(new pgrs_Bank_Modelclass("State Bank of India", "09223766666", "18004253800", R.drawable.realocation_bank_state_bank_of_india, "09223866666"));
        this.mListData.add(new pgrs_Bank_Modelclass("Saraswat Bank", "9223040000", "1800229999", R.drawable.realocation_bank_saraswat_bank, "9223501111"));
        this.mListData.add(new pgrs_Bank_Modelclass("state bank of mysore", "1800-180-2010", "18004252244", R.drawable.realocation_bank_statebankofmysore, "09223866666"));
        this.mListData.add(new pgrs_Bank_Modelclass("State Bank of Patiala", "1800-180-2010", "18004252244", R.drawable.realocation_bank_statebankofpatiala, "09223866666"));
        this.mListData.add(new pgrs_Bank_Modelclass("South Indian Bank", "09223008488", "18008431800", R.drawable.realocation_bank_south_indian_bank, "9223008488"));
        this.mListData.add(new pgrs_Bank_Modelclass("Tamilnad Mercantile Bank", "09664552255", "9842461461", R.drawable.realocation_bank_tamilnad_mercantile_bank, "08067006979"));
        this.mListData.add(new pgrs_Bank_Modelclass("Union Bank Of India", "09223009292", "18001030123", R.drawable.realocation_bank_union_bank_of_india, "UMNS 09223008486"));
        this.mListData.add(new pgrs_Bank_Modelclass("UCO Bank", "09278792787", "18001030123", R.drawable.realocation_bank_uco_bank, "09213125125"));
        this.mListData.add(new pgrs_Bank_Modelclass("varachha bank", "0261-4008080", "9586644644", R.drawable.realocation_bank_varachhabank, "0261-4008080"));
        this.mListData.add(new pgrs_Bank_Modelclass("Vijaya Bank", "1800 103 5525", "18004255885", R.drawable.realocation_bank_vijaya_bank, "1800 103 5535"));
        this.mListData.add(new pgrs_Bank_Modelclass("Yes Bank", "09223920000", "18002000", R.drawable.realocation_bank_yes, "09223921111"));
        this.mListData.add(new pgrs_Bank_Modelclass("royal bank of scotland", "08435573401", "1800112224", R.drawable.realocation_bank_royalbankofscotland, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("City Bank", "18004252484", "1800226747", R.drawable.realocation_bank_citi_bank, "Just SMS to 52484 (Format:-BAL<space>Last 4 digits of your debit card findlocation)"));
        this.mListData.add(new pgrs_Bank_Modelclass("Kotak Mahindra Bank", "18002740110", "18602662666", R.drawable.realocation_bank_kotak_bank, "Just SMS to 9971056767(Format:-TXN(space)Last 4 digits of Account Number) "));
        this.mListData.add(new pgrs_Bank_Modelclass("Bank Of India", "09015135135", "1800220229", R.drawable.realocation_bank_bank_of_india, "Not Available "));
        this.mListData.add(new pgrs_Bank_Modelclass("Corporation Bank", "09289792897", "18004253555", R.drawable.realocation_bank_corporation_bank, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("American Express", "1800446630", "1800446630", R.drawable.realocation_bank_american_express, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("HSBS Bank", "18001034722", "18001034722", R.drawable.realocation_bank_hsbc_bank, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("Indian Overseas Bank", "18004254445", "18004254445", R.drawable.realocation_bank_indian_overseas_bank, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("ABN AMRO", "1800112224", "1800112224", R.drawable.realocation_bank_abn_amro, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("Andhra Bank", "09223011300", "18004251515", R.drawable.realocation_bank_andhra, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("ANZ Bank", "18002000269", "18002000269", R.drawable.realocation_bank_anz, "Not vailable"));
        this.mListData.add(new pgrs_Bank_Modelclass("Bank Of Maharashtra", "1800-102-2636", "18002334526", R.drawable.realocation_bank_of_maharashtra, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("Barclays Bank", "18002336565", "0442476842100", R.drawable.realocation_bank_barclays, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("Indian Bank", "09289592895", "180042500000", R.drawable.realocation_bank_indian_bank, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("Punjab and Sind Bank", "1800221908", "1800221908", R.drawable.realocation_bank_punjab_and_sind_bank, "Just SMS(NPTXN A/c No. SMS-Banking-Password)"));
        this.mListData.add(new pgrs_Bank_Modelclass("Cashnet Bank", "1800225087", "1800225087", R.drawable.realocation_bank_cashnet_bank, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("Centurion Bank Of Punjab", "1800443555", "18004253555", R.drawable.realocation_bank_of_punjab, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("Standard Chartered Bank", "18003451212", "18003455000", R.drawable.realocation_bank_standard_chartered_bank, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("State Bank of Bikaner", "09223866666", "18001806005", R.drawable.realocation_bank_of_bikaner_and_jaipur, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("Deutsche Bank", "18001236601", "18001236601", R.drawable.realocation_bank_deutsche_bank, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("State Bank Of Travancore", "09223866666", "18004255566", R.drawable.realocation_bank_state_bank_of_travancore, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("Syndicate Bank", "09664552255", "08026639966", R.drawable.realocation_bank_syndicate_bank, "Not Available"));
        this.mListData.add(new pgrs_Bank_Modelclass("United Bank Of India", "09015431345", "18003450345", R.drawable.realocation_bank_of_india, "Not Available"));
        pgrs_Bank_Adapter bank_Adapter = new pgrs_Bank_Adapter(this, this.mListData);
        this.mAdapter = bank_Adapter;
        this.banklistview.setAdapter(bank_Adapter);
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
