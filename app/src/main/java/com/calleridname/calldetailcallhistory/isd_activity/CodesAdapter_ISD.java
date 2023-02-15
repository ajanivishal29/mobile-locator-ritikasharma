package com.calleridname.calldetailcallhistory.isd_activity;

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

public class CodesAdapter_ISD extends BaseAdapter {
    private ArrayList<CodesConstructor_ISD> arraylist;
    LayoutInflater inflater;
    Context mContext;
    private List<CodesConstructor_ISD> worldpopulationlist = null;

    public long getItemId(int i) {
        return (long) i;
    }

    public class ViewHolder {
        TextView country;
        TextView rank;

        public ViewHolder() {
        }
    }

    public CodesAdapter_ISD(Context context, List<CodesConstructor_ISD> list) {
        this.mContext = context;
        this.worldpopulationlist = list;
        this.inflater = LayoutInflater.from(context);
        ArrayList<CodesConstructor_ISD> arrayList = new ArrayList<>();
        this.arraylist = arrayList;
        arrayList.addAll(list);
    }

    public int getCount() {
        return this.worldpopulationlist.size();
    }

    public CodesConstructor_ISD getItem(int i) {
        return this.worldpopulationlist.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = this.inflater.inflate(R.layout.pgrs_custom_isd_codes_layout, (ViewGroup) null);
            viewHolder.rank = (TextView) view2.findViewById(R.id.number_code);
            viewHolder.country = (TextView) view2.findViewById(R.id.country_code);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.rank.setText(this.worldpopulationlist.get(i).getEmpId());
        viewHolder.country.setText(this.worldpopulationlist.get(i).getFirstName());
        return view2;
    }

    public void filter(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        this.worldpopulationlist.clear();
        if (lowerCase.length() == 0) {
            this.worldpopulationlist.addAll(this.arraylist);
        } else {
            Iterator<CodesConstructor_ISD> it = this.arraylist.iterator();
            while (it.hasNext()) {
                CodesConstructor_ISD next = it.next();
                if (next.getEmpId().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    this.worldpopulationlist.add(next);
                }
                if (next.getFirstName().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    this.worldpopulationlist.add(next);
                }
            }
        }
        notifyDataSetChanged();
    }
}
