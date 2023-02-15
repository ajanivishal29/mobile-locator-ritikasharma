package com.calleridname.calldetailcallhistory.sim_information_activity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.calleridname.calldetailcallhistory.R;

import java.util.ArrayList;
import java.util.List;

public class Item_Adapter extends RecyclerView.Adapter<Item_Adapter.MyViewHolder> {
    public Activity activity;
    List<Sim_Model> data = new ArrayList();
    private LayoutInflater inflater;

    class MyViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imgLogo;
        public final RelativeLayout layoutMain;
        public final TextView txtName;

        public MyViewHolder(View view) {
            super(view);
            this.layoutMain = (RelativeLayout) view.findViewById(R.id.layoutMain);
            this.imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
            this.txtName = (TextView) view.findViewById(R.id.textViewName);
        }
    }

    public Item_Adapter(Activity activity2, List<Sim_Model> list) {
        this.activity = activity2;
        this.inflater = LayoutInflater.from(activity2);
        this.data = list;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this.inflater.inflate(R.layout.sim_item, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        myViewHolder.txtName.setText(this.data.get(i).getName());
        myViewHolder.imgLogo.setImageResource(this.data.get(i).getImage());
        myViewHolder.layoutMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Item_Adapter.this.activity, pgrs_Sim_Activity.class);
                intent.putExtra("position", i);
                Item_Adapter.this.activity.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.data.size();
    }
}
