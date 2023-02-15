package com.calleridname.calldetailcallhistory.isd_activity;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.calleridname.calldetailcallhistory.R;
import com.calleridname.calldetailcallhistory.std_code_activity.DataBaseHelper_std;

import java.util.ArrayList;
import java.util.Locale;

public class pgrs_CodeList_ISD extends AppCompatActivity {
    private static final String DB_NAME = "locatordatabase";
    String[] EmpId;
    String[] FirstName;
    String[] LastName;
    CodesAdapter_ISD adapter;
    ArrayList<CodesConstructor_ISD> arraylist = new ArrayList<>();
    DataBaseHelper_std callLocatorDataBaseHelper;
    EditText editsearch;
    ListView list;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.pgrs_isd_codes_list_layout);
        /*try {
            if (Application_Class.mInterstitialAd2.isLoaded()) {
                Application_Class.mInterstitialAd2.show();
            }
        } catch (Exception unused) {
        }*/
        this.callLocatorDataBaseHelper = DataBaseHelper_std.getInstance(this, DB_NAME);
        ListView listView = (ListView) findViewById(R.id.list);
        this.list = listView;
        listView.setDivider((Drawable) null);
        showISDcodes();
        EditText editText = (EditText) findViewById(R.id.isd_search);
        this.editsearch = editText;
        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                pgrs_CodeList_ISD.this.adapter.filter(pgrs_CodeList_ISD.this.editsearch.getText().toString().toLowerCase(Locale.getDefault()));
            }
        });
    }

    private void showISDcodes() {
        ArrayList<CodesConstructor_ISD> arrayList = new ArrayList<>();
        this.arraylist = arrayList;
        arrayList.clear();
        Cursor rawQuery = DataBaseHelper_std.rawQuery("SELECT * FROM isdcodes");
        if (rawQuery == null || rawQuery.getCount() == 0 || !rawQuery.moveToFirst()) {
            CodesAdapter_ISD codesAdapter_ISD = new CodesAdapter_ISD(this, this.arraylist);
            this.adapter = codesAdapter_ISD;
            this.list.setAdapter(codesAdapter_ISD);
        }
        do {
            CodesConstructor_ISD codesConstructor_ISD = new CodesConstructor_ISD();
            codesConstructor_ISD.setEmpId(rawQuery.getString(rawQuery.getColumnIndex("isdcode")));
            codesConstructor_ISD.setFirstName(rawQuery.getString(rawQuery.getColumnIndex("country")));
            this.arraylist.add(codesConstructor_ISD);
        } while (rawQuery.moveToNext());
        CodesAdapter_ISD codesAdapter_ISD2 = new CodesAdapter_ISD(this, this.arraylist);
        this.adapter = codesAdapter_ISD2;
        this.list.setAdapter(codesAdapter_ISD2);
    }

    public void onBackPressed() {
        finish();
    }
}
