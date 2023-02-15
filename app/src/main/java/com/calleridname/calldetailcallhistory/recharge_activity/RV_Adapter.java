package com.calleridname.calldetailcallhistory.recharge_activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.calleridname.calldetailcallhistory.R;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class RV_Adapter extends RecyclerView.Adapter {
    private static final int TYPE_AD = 0;
    private static final int TYPE_ITEM = 1;
    private String Circlejson = "{\"Airtel\" : \"0\", \"Jio\":\"1\",\"Vodafone\":\"2\", \"Idea\" : \"3\", \"BSNL\" :\"4\",\"MTNL\" :\"5\",\"Tata Docomo\" :\"6\",\"T24\" :\"7\" }";
    private JSONObject circlej;
    private List<Object> contents;
    private Context context;
    private TypedArray service;
    private TypedArray sservice;

    public class RPViewHolder extends RecyclerView.ViewHolder {
        TextView itemdesc;
        TextView itemprice;
        TextView itemvalidity;

        RPViewHolder(View view) {
            super(view);
            this.itemprice = (TextView) view.findViewById(R.id.imageView1);
            this.itemdesc = (TextView) view.findViewById(R.id.description);
            this.itemvalidity = (TextView) view.findViewById(R.id.txtvalid);
        }
    }

    RV_Adapter(Context context2, List<Object> list) {
        this.contents = list;
        this.context = context2;
        this.service = context2.getResources().obtainTypedArray(R.array.operator);
        this.sservice = context2.getResources().obtainTypedArray(R.array.operators);
        try {
            this.circlej = new JSONObject(this.Circlejson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        return this.contents.size();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RPViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pgrs_card_picture, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        RPViewHolder rPViewHolder = (RPViewHolder) viewHolder;
        R_Plan r_Plan = (R_Plan) this.contents.get(i);
        try {
            TextView textView = rPViewHolder.itemprice;
            textView.setText("₹ " + r_Plan.getPrice());
            TextView textView2 = rPViewHolder.itemvalidity;
            textView2.setText("Validity : " + r_Plan.getValidity());
            if (r_Plan.getDetail().equals("")) {
                TextView textView3 = rPViewHolder.itemdesc;
                textView3.setText("Talktime ₹ " + r_Plan.getTalktime());
            } else {
                rPViewHolder.itemdesc.setText(r_Plan.getDetail());
            }
            rPViewHolder.itemprice.setBackground(this.service.getDrawable(Integer.parseInt(this.circlej.getString(pgrs_Rechargee_Activity.operator))));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rPViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }
}
