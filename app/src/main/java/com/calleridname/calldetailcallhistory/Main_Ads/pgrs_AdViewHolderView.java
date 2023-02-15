package com.calleridname.calldetailcallhistory.Main_Ads;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.calleridname.calldetailcallhistory.R;


public class pgrs_AdViewHolderView extends RecyclerView.ViewHolder {

    public ImageView appicon;
    public TextView appname;
    public RelativeLayout rel_cardList;
    public RelativeLayout ll_adl;

    public pgrs_AdViewHolderView(View itemView) {
        super(itemView);
        appicon = (ImageView) itemView.findViewById(R.id.appicon);
        appname = (TextView) itemView.findViewById(R.id.appname);

        ll_adl = (RelativeLayout) itemView.findViewById(R.id.ll_adl);
    }
}