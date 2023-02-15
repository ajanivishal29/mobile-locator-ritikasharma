package com.calleridname.calldetailcallhistory.numberlocation_activity.Callblock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import com.calleridname.calldetailcallhistory.R;
import java.util.ArrayList;

public class Call_Blocker extends AppCompatActivity {
    CheckBox block_calls;
    Database_Class f202DC;
    SharedPreferences f203sp;
    private ArrayList<List_Model> items = null;
    ArrayList<String> numbers;

    private long mLastClickTime = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pgrs_call_blocker_layout);
        findViewById(R.id.block_calls_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Call_Blocker call_Blocker = Call_Blocker.this;
                call_Blocker.startActivity(new Intent(call_Blocker.getApplicationContext(), Block_Selection.class));
            }
        });
        this.f203sp = getApplicationContext().getSharedPreferences("call_setings", 0);
        this.block_calls = (CheckBox) findViewById(R.id.block_calls);
        if (this.f203sp.getBoolean("isenabled", false)) {
            this.block_calls.setChecked(true);
        }
        this.block_calls.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SharedPreferences.Editor edit = Call_Blocker.this.f203sp.edit();
                edit.putBoolean("isenabled", z);
                edit.commit();
            }
        });
    }

    private void loadData() {
        try {
            Database_Class database_Class = new Database_Class(this);
            this.f202DC = database_Class;
            database_Class.open();
            Cursor data = this.f202DC.getData();
            int columnIndex = data.getColumnIndex(this.f202DC.columnName()[0]);
            int columnIndex2 = data.getColumnIndex(this.f202DC.columnName()[1]);
            int columnIndex3 = data.getColumnIndex(this.f202DC.columnName()[2]);
            this.numbers = new ArrayList<>();
            this.items = new ArrayList<>();
            data.moveToFirst();
            while (!data.isAfterLast()) {
                this.items.add(new List_Model(data.getString(columnIndex), data.getString(columnIndex2).equals("1"), data.getString(columnIndex3).equals("1")));
                this.numbers.add(data.getString(columnIndex));
                data.moveToNext();
            }
            this.f202DC.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onResume() {
        super.onResume();
        loadData();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
