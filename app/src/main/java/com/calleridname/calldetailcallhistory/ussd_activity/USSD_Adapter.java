package com.calleridname.calldetailcallhistory.ussd_activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.calleridname.calldetailcallhistory.R;
import java.util.Arrays;
import java.util.List;

public class USSD_Adapter extends BaseAdapter {
    private String[] Operator = {"Airtel", "Idea", "Vodafone", "Reliance Jio", "BSNL", "Tata Docomo", "Telenor"};
    private LayoutInflater inflater;
    private List<String> ussdList;

    public long getItemId(int i) {
        return (long) i;
    }

    USSD_Adapter(Context context, List<String> list) {
        this.ussdList = list;
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.ussdList.size();
    }

    public Object getItem(int i) {
        return this.ussdList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(R.layout.pgrs_ussd_item, viewGroup, false);
        }
        String str = (String) getItem(i);
        TextView textView = (TextView) view.findViewById(R.id.name);
        if (Arrays.asList(this.Operator).contains(str.split(":")[0])) {
            textView.setTextColor(Color.BLUE);
            textView.setTextSize(18.0f);
            textView.setTypeface((Typeface) null, 1);
            textView.setPadding(50, 5, 0, 5);
        } else {
            textView.setTextColor(Color.BLUE);
            textView.setTextSize(15.0f);
            textView.setTypeface((Typeface) null, 0);
            textView.setPadding(0, 0, 0, 5);
        }
        textView.setText(str.split(":")[0]);
        ((TextView) view.findViewById(R.id.value)).setText(str.split(":")[1]);
        return view;
    }
}
