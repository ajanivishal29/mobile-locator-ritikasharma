package com.calleridname.calldetailcallhistory.recharge_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;
import com.facebook.ads.NativeAdLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressLint("WrongConstant")

public class pgrs_Rechargee_Activity extends AppCompatActivity {
    public static final String LOCAL = "local";
    static RV_Adapter callLocatorRvAdapter;
    public static String circle;
    public static String circle1;
    public static List<R_Plan> mContentItems = new ArrayList();
    public static String operator;
    public static String[] type = {"topup", "4g", "3g", "local", "sms", "std_code", "isd", "other"};
    String[] Operator = {"Airtel", "Jio", "Vodafone", "Idea", "BSNL", "MTNL", "Tata Docomo", "T24"};
    String[] OperatorShort = {"Airtel", "Jio", "Vodafone", "Idea", "BSNL", "MTNL", "Docomo", "T24"};
    String[] State = {"AndhraPradesh", "Assam", "Bihar", "Chennai", "Delhi NCR", "Gujarat", "Himachal Pradesh", "Haryana", "Jammu-Kashmir", "Karnataka", "Kerala", "Kolkata", "Goa", "Madhya Pradesh", "Mumbai", "North East", "North East 1", "North East 2", "Orrisa", "Punjab", "Rajasthan", "Tamil Nadu", "Uttar Pradesh East", "Uttar Pradesh West", "West Bengal"};
    String[] StateShort = {"AP", "AS", "BR", "Chennai", "Delhi", "GJ", "HP", "HR", "JK", "KA", "KL", "Kolkata", "MH", "MP", "Mumbai", "NE", "NE1", "NE2", "OR", "PB", "RJ", "TN", "UPE", "UPW", "WB"};
    ActionBar actionBar;
    public SectionsPagerAdapter mSectionsPagerAdapter;
    public ViewPager mViewPager;
    ProgressBar simpleProgressBar;
    public String[] tabname = {"topup", "4g", "2g/3g/4g", "local", "sms", "std_code", "isd", "other"};

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


    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        List<Object> arr_filteredlist = new ArrayList();
        Context context;

        static PlaceholderFragment newInstance(int i) {
            PlaceholderFragment placeholderFragment = new PlaceholderFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_SECTION_NUMBER, i);
            placeholderFragment.setArguments(bundle);
            return placeholderFragment;
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View inflate = layoutInflater.inflate(R.layout.fragment_recharge, viewGroup, false);
            this.context = inflate.getContext();

            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            Collection filter = Collections2.filter(pgrs_Rechargee_Activity.mContentItems, new Predicate<R_Plan>() {
                /* class com.findlocation.calldetailcallhistory.recharge_activity.Rechargee_Activity.PlaceholderFragment.AnonymousClass1 */

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
//                @Override // com.google.common.base.Predicate, java.util.function.Predicate
                public /* synthetic */ boolean test(R_Plan r_Plan) {
//                    return Predicate.CC.$default$test(this, r_Plan);
                    return test(r_Plan);
                }

                public boolean apply(R_Plan r_Plan) {
                    return r_Plan.getCategory().contains(pgrs_Rechargee_Activity.type[PlaceholderFragment.this.getArguments().getInt(PlaceholderFragment.ARG_SECTION_NUMBER) - 1]);
                }
            });
            this.arr_filteredlist.clear();
            this.arr_filteredlist = new ArrayList(filter);
            pgrs_Rechargee_Activity.callLocatorRvAdapter = new RV_Adapter(getActivity(), this.arr_filteredlist);
            recyclerView.setAdapter(pgrs_Rechargee_Activity.callLocatorRvAdapter);
            pgrs_Rechargee_Activity.callLocatorRvAdapter.notifyDataSetChanged();
            return inflate;
        }
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        public int getCount() {
            return 8;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return PlaceholderFragment.newInstance(i + 1);
        }

        public CharSequence getPageTitle(int i) {
            return pgrs_Rechargee_Activity.this.tabname[i];
        }
    }

    public pgrs_Rechargee_Activity() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pgrs_recharge_layout);

        ActionBar supportActionBar = getSupportActionBar();
        this.actionBar = supportActionBar;
        this.simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            this.actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ((ImageView) findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });

        context = pgrs_Rechargee_Activity.this;
        admobmediumnative = findViewById(R.id.admobmediumnative);
        native_ad_container = findViewById(R.id.native_ad_container);
        q_native = findViewById(R.id.q_native);

        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        this.mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        this.mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(this.mViewPager);
        this.mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.ViewPagerOnTabSelectedListener(this.mViewPager));
        selectDialog();
    }

    private void selectDialog() {
        @SuppressLint("WrongConstant") View inflate = ((LayoutInflater) getBaseContext().getSystemService("layout_inflater")).inflate(R.layout.pgrs_spinner_layout, (ViewGroup) null);
        final Spinner spinner = (Spinner) inflate.findViewById(R.id.operatorspinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, this.Operator);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item1);
        spinner.setAdapter(arrayAdapter);
        final Spinner spinner2 = (Spinner) inflate.findViewById(R.id.locSpinner);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.spinner_item, this.State);
        arrayAdapter2.setDropDownViewResource(R.layout.spinner_item1);
        spinner2.setAdapter(arrayAdapter2);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        builder.setCancelable(true);
        final AlertDialog show = builder.show();
        ((Button) inflate.findViewById(R.id.viewplan)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pgrs_Rechargee_Activity.operator = pgrs_Rechargee_Activity.this.OperatorShort[spinner.getSelectedItemPosition()];
                pgrs_Rechargee_Activity.circle1 = (String) spinner2.getSelectedItem();
                pgrs_Rechargee_Activity.circle = pgrs_Rechargee_Activity.this.StateShort[spinner2.getSelectedItemPosition()];
                if (pgrs_Rechargee_Activity.this.getSupportActionBar() != null) {
                    ActionBar supportActionBar = pgrs_Rechargee_Activity.this.getSupportActionBar();
                    supportActionBar.setTitle((CharSequence) ((CharSequence) spinner.getSelectedItem()) + " - " + pgrs_Rechargee_Activity.circle1);
                }
                pgrs_Rechargee_Activity.this.getPlans();
                show.dismiss();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void getPlans() {
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        newRequestQueue.add(new StringRequest(0, getResources().getString(R.string.url) + "?circle=" + circle + "&service=" + operator + "&uid=16917f21-e786-477c-92bd-7db805153d90", new Response.Listener<String>() {
            public void onResponse(String str) {
                JSONArray jSONArray;
                pgrs_Rechargee_Activity.mContentItems.clear();
                try {
                    jSONArray = new JSONArray(str);
                } catch (JSONException e) {
                    e.printStackTrace();
                    jSONArray = null;
                }
                JSONArray jSONArray2 = jSONArray;
                if (jSONArray2 != null) {
                    pgrs_Rechargee_Activity.this.simpleProgressBar.setVisibility(8);
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        String str2 = "";
                        try {
                            String string = jSONArray2.getJSONObject(i).getString("category");
                            String string2 = jSONArray2.getJSONObject(i).has("detail") ? jSONArray2.getJSONObject(i).getString("detail") : str2;
                            String string3 = jSONArray2.getJSONObject(i).getString("price");
                            String string4 = jSONArray2.getJSONObject(i).getString("updated");
                            String string5 = jSONArray2.getJSONObject(i).has("validity") ? jSONArray2.getJSONObject(i).getString("validity") : str2;
                            if (jSONArray2.getJSONObject(i).has("talktime")) {
                                str2 = jSONArray2.getJSONObject(i).getString("talktime");
                            }
                            pgrs_Rechargee_Activity.mContentItems.add(new R_Plan(string, string2, string3, string4, string5, str2, jSONArray2.getJSONObject(i).getString("keywords")));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (pgrs_Rechargee_Activity.mContentItems.size() == 0) {
                    Toast.makeText(pgrs_Rechargee_Activity.this, " Service Not Available in selected circle.", 1).show();
                }
                pgrs_Rechargee_Activity.this.mViewPager.setAdapter(pgrs_Rechargee_Activity.this.mSectionsPagerAdapter);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(pgrs_Rechargee_Activity.this, "Service Not Available in selected circle", 1).show();
                pgrs_Rechargee_Activity.mContentItems.clear();
            }
        }));
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
