package com.calleridname.calldetailcallhistory.sim_information_activity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.calleridname.calldetailcallhistory.R;

public class Details_Adapter extends RecyclerView.Adapter<Details_Adapter.MyViewHolder> {
    private Activity activity;
    String[] f56a = new String[8];
    private LayoutInflater inflater;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private Details_Adapter f57a;
        private final RelativeLayout layoutMain;
        private Details_Adapter this$0;
        public final TextView txtTitle;
        public final TextView txtdesc;

        public MyViewHolder(Details_Adapter details_Adapter, Details_Adapter details_Adapter2, View view) {
            super(view);
            this.layoutMain = (RelativeLayout) view.findViewById(R.id.layoutMain);
            this.txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            this.txtdesc = (TextView) view.findViewById(R.id.txtdesc);
        }
    }

    public Details_Adapter(Activity activity2, String[] strArr) {
        this.activity = activity2;
        this.inflater = LayoutInflater.from(activity2);
        this.f56a = strArr;
    }

    public int getItemCount() {
        return this.f56a.length;
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.txtTitle.setText(pgrs_SimInfo_Activity.arr[i]);
        myViewHolder.txtdesc.setText(this.f56a[i]);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this, this, this.inflater.inflate(R.layout.pgrs_calllocator_item2, viewGroup, false));
    }
}
