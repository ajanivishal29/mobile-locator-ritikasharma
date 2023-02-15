package com.calleridname.calldetailcallhistory.bank_activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.calleridname.calldetailcallhistory.R;

import java.util.ArrayList;

public class pgrs_Bank_Adapter extends RecyclerView.Adapter<pgrs_Bank_Adapter.BankHolder> {
    Context mContext;
    ArrayList<pgrs_Bank_Modelclass> mListData;

    public class BankHolder extends RecyclerView.ViewHolder {
        ImageView bankImg;
        TextView bankTitle;
        LinearLayout bankViewName;

        public BankHolder(View view) {
            super(view);
            this.bankViewName = (LinearLayout) view.findViewById(R.id.bank_view_name);
            this.bankImg = (ImageView) view.findViewById(R.id.bank_view_img);
            this.bankTitle = (TextView) view.findViewById(R.id.bank_view_title);
        }
    }

    public pgrs_Bank_Adapter(Context context, ArrayList<pgrs_Bank_Modelclass> arrayList) {
        this.mContext = context;
        this.mListData = arrayList;
    }

    public BankHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BankHolder(LayoutInflater.from(this.mContext).inflate(R.layout.bankname_item, viewGroup, false));
    }

    public void onBindViewHolder(BankHolder bankHolder, int i) {
        final String title = this.mListData.get(i).getTitle();
        final String balance = this.mListData.get(i).getBalance();
        final String customer = this.mListData.get(i).getCustomer();
        final String statement = this.mListData.get(i).getStatement();
        final int img = this.mListData.get(i).getImg();
        bankHolder.bankViewName.setAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.calllocator_fade_scale_animation));
        bankHolder.bankTitle.setText(title);
        bankHolder.bankImg.setImageResource(img);
        bankHolder.bankViewName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(pgrs_Bank_Adapter.this.mContext, pgrs_BankInfoDetails_Activity.class);
                intent.putExtra("title", title);
                intent.putExtra("balance", balance);
                intent.putExtra("customer", customer);
                intent.putExtra("statement", statement);
                intent.putExtra("img", img);
                pgrs_Bank_Adapter.this.mContext.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.mListData.size();
    }
}
