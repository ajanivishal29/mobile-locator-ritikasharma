package com.calleridname.calldetailcallhistory.std_code_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.calleridname.calldetailcallhistory.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class STD_Adapter extends BaseAdapter {
    private List<CodeConstructor_STD> areacodelist = null;
    private ArrayList<CodeConstructor_STD> arraylist;
    LayoutInflater inflater;
    Context mContext;

    public long getItemId(int i) {
        return (long) i;
    }

    public class ViewHolder {
        TextView country;
        TextView population;
        TextView rank;

        public ViewHolder() {
        }
    }

    public STD_Adapter(Context context, List<CodeConstructor_STD> list) {
        this.mContext = context;
        this.areacodelist = list;
        this.inflater = LayoutInflater.from(context);
        ArrayList<CodeConstructor_STD> arrayList = new ArrayList<>();
        this.arraylist = arrayList;
        arrayList.addAll(list);
    }

    public int getCount() {
        return this.areacodelist.size();
    }

    public CodeConstructor_STD getItem(int i) {
        return this.areacodelist.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = this.inflater.inflate(R.layout.pgrs_custom_std_codes_layout, (ViewGroup) null);
            viewHolder.rank = (TextView) view2.findViewById(R.id.number_code);
            viewHolder.country = (TextView) view2.findViewById(R.id.std_code);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        TextView textView = viewHolder.rank;
        textView.setText("0" + this.areacodelist.get(i).getAreacode());
        viewHolder.country.setText(this.areacodelist.get(i).getAreaname());
        return view2;
    }

    public void filter(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        this.areacodelist.clear();
        if (lowerCase.length() == 0) {
            this.areacodelist.addAll(this.arraylist);
        } else {
            Iterator<CodeConstructor_STD> it = this.arraylist.iterator();
            while (it.hasNext()) {
                CodeConstructor_STD next = it.next();
                if (next.getAreacode().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    this.areacodelist.add(next);
                }
                if (next.getAreaname().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    this.areacodelist.add(next);
                }
            }
        }
        notifyDataSetChanged();
    }
}
