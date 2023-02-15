package com.calleridname.calldetailcallhistory.numberlocation_activity.Callblock;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.calleridname.calldetailcallhistory.R;

import org.slf4j.Marker;

public class Block_Selection extends AppCompatActivity {
    Button add_from_conrtacts;
    Button add_new;
    boolean cursorFlag = false;
    Database_Class f152DC;
    Cursor f153c;
    String phone;
    Button show_list;

    private long mLastClickTime = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pgrs_block_selection_layout);
        Button button = (Button) findViewById(R.id.show_list);
        this.show_list = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Block_Selection block_Selection = Block_Selection.this;
                block_Selection.startActivity(new Intent(block_Selection.getApplicationContext(), Blocked_List.class));
            }
        });
        Database_Class database_Class = new Database_Class(this);
        this.f152DC = database_Class;
        database_Class.open();
        this.f153c = this.f152DC.getData();
        this.add_from_conrtacts = (Button) findViewById(R.id.from_contacts);
        this.add_new = (Button) findViewById(R.id.add_new);
        this.add_from_conrtacts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI);
                intent.setType("vnd.android.cursor.dir/phone_v2");
                Block_Selection.this.startActivityForResult(intent, 1001);
            }
        });
        this.add_new.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 1) {
            try {
                String stringExtra = intent.getStringExtra("value");
                if (stringExtra != null && stringExtra.length() > 0 && stringExtra.matches("[0-9]+")) {
                    if (stringExtra.charAt(0) == '0') {
                        stringExtra = stringExtra.substring(1);
                    }
                    Database_Class database_Class = new Database_Class(this);
                    this.f152DC = database_Class;
                    database_Class.open();
                    this.f152DC.createEntry(stringExtra, new Boolean(true).booleanValue(), new Boolean(true).booleanValue());
                    this.f152DC.close();
                }
                Toast.makeText(getBaseContext(), "Number cant containt extra charachter", 0).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (i == 1001) {
            try {
                Cursor managedQuery = managedQuery(intent.getData(), (String[]) null, (String) null, (String[]) null, (String) null);
                this.f153c = managedQuery;
                this.cursorFlag = true;
                if (managedQuery.moveToFirst()) {
                    Cursor cursor = this.f153c;
                    this.phone = cursor.getString(cursor.getColumnIndexOrThrow("data1"));
                }
                if (this.phone.contains(Marker.ANY_NON_NULL_MARKER)) {
                    this.phone = this.phone.split("\\+")[1];
                }
                if (this.phone.charAt(0) == '0') {
                    this.phone = this.phone.substring(1);
                }
                Database_Class database_Class2 = new Database_Class(this);
                this.f152DC = database_Class2;
                database_Class2.open();
                Log.d("Contsct Num", this.phone);
                this.f152DC.createEntry(this.phone, new Boolean(true).booleanValue(), new Boolean(true).booleanValue());
                this.f152DC.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
