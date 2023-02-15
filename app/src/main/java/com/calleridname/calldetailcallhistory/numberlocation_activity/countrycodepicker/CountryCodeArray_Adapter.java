package com.calleridname.calldetailcallhistory.numberlocation_activity.countrycodepicker;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.calleridname.calldetailcallhistory.R;

import java.util.List;

public class CountryCodeArray_Adapter extends ArrayAdapter<Country> {
    private final CountryCode_Picker mCallLocatorCountryCodePicker;

    private static class ViewHolder {
        ImageView imvFlag;
        LinearLayout llyFlagHolder;
        RelativeLayout rlyMain;
        TextView tvCode;
        TextView tvName;
        View viewDivider;

        private ViewHolder() {
        }
    }

    CountryCodeArray_Adapter(Context context, List<Country> list, CountryCode_Picker countryCode_Picker) {
        super(context, 0, list);
        this.mCallLocatorCountryCodePicker = countryCode_Picker;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        Country country = (Country) getItem(i);
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = LayoutInflater.from(getContext()).inflate(R.layout.pgrs_country_code_picker_item_country_layout, viewGroup, false);
            viewHolder.rlyMain = (RelativeLayout) view2.findViewById(R.id.item_country_rly);
            viewHolder.tvName = (TextView) view2.findViewById(R.id.country_name_tv);
            viewHolder.tvCode = (TextView) view2.findViewById(R.id.code_tv);
            viewHolder.imvFlag = (ImageView) view2.findViewById(R.id.flag_imv);
            viewHolder.llyFlagHolder = (LinearLayout) view2.findViewById(R.id.flag_holder_lly);
            viewHolder.viewDivider = view2.findViewById(R.id.preference_divider_view);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        setData(country, viewHolder);
        return view2;
    }

    private void setData(Country country, ViewHolder viewHolder) {
        if (country == null) {
            viewHolder.viewDivider.setVisibility(0);
            viewHolder.tvName.setVisibility(8);
            viewHolder.tvCode.setVisibility(8);
            viewHolder.llyFlagHolder.setVisibility(8);
            return;
        }
        viewHolder.viewDivider.setVisibility(8);
        viewHolder.tvName.setVisibility(0);
        viewHolder.tvCode.setVisibility(0);
        viewHolder.llyFlagHolder.setVisibility(0);
        Context context = viewHolder.tvName.getContext();
        String name = country.getName();
        String upperCase = country.getIso().toUpperCase();
        if (!this.mCallLocatorCountryCodePicker.isHideNameCode()) {
            name = context.getString(R.string.country_name_and_code, new Object[]{name, upperCase});
        }
        viewHolder.tvName.setText(name);
        if (this.mCallLocatorCountryCodePicker.isHidePhoneCode()) {
            viewHolder.tvCode.setVisibility(8);
        } else {
            viewHolder.tvCode.setText(context.getString(R.string.phone_code, new Object[]{country.getPhoneCode()}));
        }
        Typeface typeFace = this.mCallLocatorCountryCodePicker.getTypeFace();
        if (typeFace != null) {
            viewHolder.tvCode.setTypeface(typeFace);
            viewHolder.tvName.setTypeface(typeFace);
        }
        try {
            viewHolder.imvFlag.setImageResource(CountryUtils34.getFlagDrawableResId(country));
        } catch (Exception unused) {
            Toast.makeText(context, "Errror12", 0).show();
        }
        int dialogTextColor = this.mCallLocatorCountryCodePicker.getDialogTextColor();
        if (dialogTextColor != this.mCallLocatorCountryCodePicker.getDefaultContentColor()) {
            viewHolder.tvCode.setTextColor(dialogTextColor);
            viewHolder.tvName.setTextColor(dialogTextColor);
        }
    }
}
