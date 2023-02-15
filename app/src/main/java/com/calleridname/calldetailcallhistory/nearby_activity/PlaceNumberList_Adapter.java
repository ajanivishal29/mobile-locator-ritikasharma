package com.calleridname.calldetailcallhistory.nearby_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;

import java.util.ArrayList;

public class PlaceNumberList_Adapter extends RecyclerView.Adapter<PlaceNumberList_Adapter.PlacesListViewHolder> implements Filterable {
    public ArrayList<Places> callLocatorPlacesListDataModelArrayList = new ArrayList<>();
    public ClickItem clickItem;
    private Context context;
    private CustomFilter filter;
    public ArrayList<Places> filterList = new ArrayList<>();
    private TextView tvFilter;

    public DataItem convertedObject;

    private long mLastClickTime = 0;

    public interface ClickItem {
        void click(int i, String str);
    }

    class CustomFilter extends Filter {
        CustomFilter() {
        }

        public FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence == null || charSequence.length() <= 0) {
                filterResults.count = PlaceNumberList_Adapter.this.filterList.size();
                filterResults.values = PlaceNumberList_Adapter.this.filterList;
            } else {
                String upperCase = charSequence.toString().toUpperCase();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < PlaceNumberList_Adapter.this.filterList.size(); i++) {
                    if (PlaceNumberList_Adapter.this.filterList.get(i).getPlace_name().toUpperCase().contains(upperCase)) {
                        arrayList.add(new Places(PlaceNumberList_Adapter.this.filterList.get(i).getPlace_id(), PlaceNumberList_Adapter.this.filterList.get(i).getPlace_name(), PlaceNumberList_Adapter.this.filterList.get(i).getPlace_type()));
                    }
                }
                filterResults.count = arrayList.size();
                filterResults.values = arrayList;
            }
            return filterResults;
        }

        public void publishResults(CharSequence charSequence, FilterResults filterResults) {
            PlaceNumberList_Adapter.this.callLocatorPlacesListDataModelArrayList = (ArrayList) filterResults.values;
            PlaceNumberList_Adapter.this.notifyDataSetChanged();
        }
    }

    public class PlacesListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llPlaceSelect;
        TextView txtPlaceName;

        public PlacesListViewHolder(View view) {
            super(view);
            this.llPlaceSelect = (LinearLayout) view.findViewById(R.id.llPlaceSelect);
            this.txtPlaceName = (TextView) view.findViewById(R.id.txtPlaceName);
        }
    }

    public Filter getFilter() {
        if (this.filter == null) {
            this.filter = new CustomFilter();
        }
        return this.filter;
    }

    public void PlacesInterface(ClickItem clickItem2) {
        this.clickItem = clickItem2;
    }

    public PlaceNumberList_Adapter(Context context2, ArrayList<Places> arrayList) {
        this.context = context2;
        this.callLocatorPlacesListDataModelArrayList = arrayList;
        this.filterList = arrayList;
    }

    public PlacesListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        convertedObject = Utils.getResponse(context);
        return new PlacesListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.places_item, viewGroup, false));
    }

    public void onBindViewHolder(PlacesListViewHolder placesListViewHolder, final int i) {
        placesListViewHolder.txtPlaceName.setText(this.callLocatorPlacesListDataModelArrayList.get(i).getPlace_name());
        placesListViewHolder.llPlaceSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PlaceNumberList_Adapter.this.clickItem != null) {
                    PlaceNumberList_Adapter.this.clickItem.click(i, PlaceNumberList_Adapter.this.callLocatorPlacesListDataModelArrayList.get(i).getPlace_name());
                }

            }
        });
    }

    public int getItemCount() {
        return this.callLocatorPlacesListDataModelArrayList.size();
    }

    public long getItemId(int i) {
        ArrayList<Places> arrayList = this.callLocatorPlacesListDataModelArrayList;
        if (arrayList != null) {
            i = arrayList.indexOf(this.filterList.get(i));
        }
        return (long) i;
    }
}
