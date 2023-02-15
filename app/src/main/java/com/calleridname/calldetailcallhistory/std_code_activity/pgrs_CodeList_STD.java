package com.calleridname.calldetailcallhistory.std_code_activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.calleridname.calldetailcallhistory.R;
import java.util.ArrayList;
import java.util.Locale;

public class pgrs_CodeList_STD extends AppCompatActivity {
    private static final String DB_NAME = "locatordatabase";
    String[] EmpId;
    String[] FirstName;
    String[] LastName;
    STD_Adapter adapter;
    ArrayList<CodeConstructor_STD> arraylist = new ArrayList<>();
    DataBaseHelper_std callLocatorDataBaseHelper;
    EditText editsearch;
    ListView list;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.pgrs_std__codes__list_layout);
        /*try {
            if (Application_Class.mInterstitialAd1.isLoaded()) {
                Application_Class.mInterstitialAd1.show();
            }
        } catch (Exception unused) {
        }*/
        this.callLocatorDataBaseHelper = DataBaseHelper_std.getInstance(this, DB_NAME);
        ListView listView = (ListView) findViewById(R.id.area_list);
        this.list = listView;
        listView.setDivider((Drawable) null);
        showSTDcodes();
        EditText editText = (EditText) findViewById(R.id.area_search);
        this.editsearch = editText;
        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                pgrs_CodeList_STD.this.adapter.filter(pgrs_CodeList_STD.this.editsearch.getText().toString().toLowerCase(Locale.getDefault()));
            }
        });
    }

    @SuppressLint("Range")
    private void showSTDcodes() {
        ArrayList<CodeConstructor_STD> arrayList = new ArrayList<>();
        this.arraylist = arrayList;
        arrayList.clear();
        Cursor rawQuery = DataBaseHelper_std.rawQuery("SELECT * FROM stdcodes");
        if (rawQuery == null || rawQuery.getCount() == 0 || !rawQuery.moveToFirst()) {
            STD_Adapter sTD_Adapter = new STD_Adapter(this, this.arraylist);
            this.adapter = sTD_Adapter;
            this.list.setAdapter(sTD_Adapter);
        }
        do {
            CodeConstructor_STD codeConstructor_STD = new CodeConstructor_STD();
            codeConstructor_STD.setAreacode(rawQuery.getString(rawQuery.getColumnIndex("stdcode")));
            codeConstructor_STD.setAreaname(rawQuery.getString(rawQuery.getColumnIndex("city")));
            this.arraylist.add(codeConstructor_STD);
        } while (rawQuery.moveToNext());
        STD_Adapter sTD_Adapter2 = new STD_Adapter(this, this.arraylist);
        this.adapter = sTD_Adapter2;
        this.list.setAdapter(sTD_Adapter2);
    }

    public void onBackPressed() {
        finish();
    }
}
