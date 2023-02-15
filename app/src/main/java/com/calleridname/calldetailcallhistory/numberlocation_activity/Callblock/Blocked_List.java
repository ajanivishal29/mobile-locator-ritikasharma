package com.calleridname.calldetailcallhistory.numberlocation_activity.Callblock;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.calleridname.calldetailcallhistory.R;
import java.util.ArrayList;

public class Blocked_List extends Activity {
    ListView BlockList;
    Database_Class f154DC;
    public ArrayList<List_Model> items = null;
    ListAdapter numberList;
    ArrayList<String> numbers;

    private long mLastClickTime = 0;

    private class ListAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public long getItemId(int i) {
            return (long) i;
        }

        public ListAdapter() {
            this.inflater = Blocked_List.this.getLayoutInflater();
        }

        public int getCount() {
            return Blocked_List.this.items.size();
        }

        public Object getItem(int i) {
            return Blocked_List.this.items.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            View inflate = this.inflater.inflate(R.layout.pgrs_blocked_list_items_layout, (ViewGroup) null);
            List_Model list_Model = Blocked_List.this.items.get(i);
            ((TextView) inflate.findViewById(R.id.number)).setText(list_Model.getNumber());
            CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.chkMsg);
            CheckBox checkBox2 = (CheckBox) inflate.findViewById(R.id.chkCall);
            checkBox.setChecked(list_Model.getMsg());
            checkBox2.setChecked(list_Model.getCall());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    Blocked_List.this.f154DC = new Database_Class(Blocked_List.this);
                    Blocked_List.this.f154DC.open();
                    Blocked_List.this.f154DC.EditMSG(Blocked_List.this.numbers.get(i), Boolean.valueOf(z));
                }
            });
            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    Blocked_List.this.f154DC = new Database_Class(Blocked_List.this);
                    Blocked_List.this.f154DC.open();
                    Blocked_List.this.f154DC.EditCALL(Blocked_List.this.numbers.get(i), Boolean.valueOf(z));
                }
            });
            ((ImageButton) inflate.findViewById(R.id.btnDelete)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return;
                    }
                    Blocked_List.this.f154DC = new Database_Class(Blocked_List.this);
                    Blocked_List.this.f154DC.open();
                    Blocked_List.this.f154DC.DeleteData(Blocked_List.this.numbers.get(i));
                    Blocked_List.this.f154DC.close();
                    Blocked_List.this.loadData();
                }
            });
            return inflate;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pgrs_blocked_list_layout);
        this.BlockList = (ListView) findViewById(R.id.list_item_iterator);
        loadData();
    }

    public void onResume() {
        super.onResume();
    }

    public void loadData() {
        try {
            Database_Class database_Class = new Database_Class(this);
            this.f154DC = database_Class;
            database_Class.open();
            Cursor data = this.f154DC.getData();
            int columnIndex = data.getColumnIndex(this.f154DC.columnName()[0]);
            int columnIndex2 = data.getColumnIndex(this.f154DC.columnName()[1]);
            int columnIndex3 = data.getColumnIndex(this.f154DC.columnName()[2]);
            this.numbers = new ArrayList<>();
            this.items = new ArrayList<>();
            data.moveToFirst();
            while (!data.isAfterLast()) {
                this.items.add(new List_Model(data.getString(columnIndex), data.getString(columnIndex2).equals("1"), data.getString(columnIndex3).equals("1")));
                this.numbers.add(data.getString(columnIndex));
                data.moveToNext();
            }
            this.f154DC.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        ListAdapter listAdapter = new ListAdapter();
        this.numberList = listAdapter;
        this.BlockList.setAdapter(listAdapter);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
