package com.calleridname.calldetailcallhistory.numberlocation_activity.countrycodepicker;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.calleridname.calldetailcallhistory.R;
import java.util.ArrayList;
import java.util.List;

class CountryCode_Dialog extends Dialog {
    private static final String TAG = "CountryCodeDialog";
    private CountryCodeArray_Adapter mArrayAdapter;
    public CountryCode_Picker mCallLocatorCountryCodePicker;
    public EditText mEdtSearch;
    public List<Country> mFilteredCountries;
    public InputMethodManager mInputMethodManager;
    private ListView mLvCountryDialog;
    private RelativeLayout mRlyDialog;
    private List<Country> mTempCountries;
    private TextView mTvNoResult;
    private TextView mTvTitle;
    private List<Country> masterCountries;

    CountryCode_Dialog(CountryCode_Picker countryCode_Picker) {
        super(countryCode_Picker.getContext());
        this.mCallLocatorCountryCodePicker = countryCode_Picker;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.pgrs_country_code_picker_layout_picker_dialog);
        setupUI();
        setupData();
    }

    private void setupUI() {
        this.mRlyDialog = (RelativeLayout) findViewById(R.id.dialog_rly);
        this.mLvCountryDialog = (ListView) findViewById(R.id.country_dialog_lv);
        this.mTvTitle = (TextView) findViewById(R.id.title_tv);
        this.mEdtSearch = (EditText) findViewById(R.id.search_edt);
        this.mTvNoResult = (TextView) findViewById(R.id.no_result_tv);
    }

    private void setupData() {
        if (this.mCallLocatorCountryCodePicker.getTypeFace() != null) {
            Typeface typeFace = this.mCallLocatorCountryCodePicker.getTypeFace();
            this.mTvTitle.setTypeface(typeFace);
            this.mEdtSearch.setTypeface(typeFace);
            this.mTvNoResult.setTypeface(typeFace);
        }
        if (this.mCallLocatorCountryCodePicker.getBackgroundColor() != this.mCallLocatorCountryCodePicker.getDefaultBackgroundColor()) {
            this.mRlyDialog.setBackgroundColor(this.mCallLocatorCountryCodePicker.getBackgroundColor());
        }
        if (this.mCallLocatorCountryCodePicker.getDialogTextColor() != this.mCallLocatorCountryCodePicker.getDefaultContentColor()) {
            int dialogTextColor = this.mCallLocatorCountryCodePicker.getDialogTextColor();
            this.mTvTitle.setTextColor(dialogTextColor);
            this.mTvNoResult.setTextColor(dialogTextColor);
            this.mEdtSearch.setTextColor(dialogTextColor);
            this.mEdtSearch.setHintTextColor(adjustAlpha(dialogTextColor, 0.7f));
        }
        this.mCallLocatorCountryCodePicker.refreshCustomMasterList();
        this.mCallLocatorCountryCodePicker.refreshPreferredCountries();
        CountryCode_Picker countryCode_Picker = this.mCallLocatorCountryCodePicker;
        this.masterCountries = countryCode_Picker.getCustomCountries(countryCode_Picker);
        this.mFilteredCountries = getFilteredCountries();
        setupListView(this.mLvCountryDialog);
        this.mInputMethodManager = (InputMethodManager) this.mCallLocatorCountryCodePicker.getContext().getSystemService("input_method");
        setSearchBar();
    }

    private void setupListView(ListView listView) {
        this.mArrayAdapter = new CountryCodeArray_Adapter(getContext(), this.mFilteredCountries, this.mCallLocatorCountryCodePicker);
        if (!this.mCallLocatorCountryCodePicker.isSelectionDialogShowSearch()) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) listView.getLayoutParams();
            layoutParams.height = -2;
            listView.setLayoutParams(layoutParams);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (CountryCode_Dialog.this.mFilteredCountries == null) {
                    Log.e(CountryCode_Dialog.TAG, "no filtered countries found! This should not be happened, Please report!");
                } else if (CountryCode_Dialog.this.mFilteredCountries.size() < i || i < 0) {
                    Log.e(CountryCode_Dialog.TAG, "Something wrong with the ListView. Please report this!");
                } else {
                    Country country = CountryCode_Dialog.this.mFilteredCountries.get(i);
                    if (country != null) {
                        CountryCode_Dialog.this.mCallLocatorCountryCodePicker.setSelectedCountry(country);
                        CountryCode_Dialog.this.mInputMethodManager.hideSoftInputFromWindow(CountryCode_Dialog.this.mEdtSearch.getWindowToken(), 0);
                        CountryCode_Dialog.this.dismiss();
                    }
                }
            }
        });
        listView.setAdapter(this.mArrayAdapter);
    }

    private int adjustAlpha(int i, float f) {
        return Color.argb(Math.round(((float) Color.alpha(i)) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    private void setSearchBar() {
        if (this.mCallLocatorCountryCodePicker.isSelectionDialogShowSearch()) {
            setTextWatcher();
        } else {
            this.mEdtSearch.setVisibility(8);
        }
    }

    private void setTextWatcher() {
        InputMethodManager inputMethodManager;
        EditText editText = this.mEdtSearch;
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    CountryCode_Dialog.this.applyQuery(charSequence.toString());
                }
            });
            if (this.mCallLocatorCountryCodePicker.isKeyboardAutoPopOnSearch() && (inputMethodManager = this.mInputMethodManager) != null) {
                inputMethodManager.toggleSoftInput(2, 0);
            }
        }
    }

    public void applyQuery(String str) {
        this.mTvNoResult.setVisibility(8);
        String lowerCase = str.toLowerCase();
        if (lowerCase.length() > 0 && lowerCase.charAt(0) == '+') {
            lowerCase = lowerCase.substring(1);
        }
        List<Country> filteredCountries = getFilteredCountries(lowerCase);
        this.mFilteredCountries = filteredCountries;
        if (filteredCountries.size() == 0) {
            this.mTvNoResult.setVisibility(0);
        }
        this.mArrayAdapter.notifyDataSetChanged();
    }

    private List<Country> getFilteredCountries() {
        return getFilteredCountries("");
    }

    private List<Country> getFilteredCountries(String str) {
        List<Country> list = this.mTempCountries;
        if (list == null) {
            this.mTempCountries = new ArrayList();
        } else {
            list.clear();
        }
        List<Country> preferredCountries = this.mCallLocatorCountryCodePicker.getPreferredCountries();
        if (preferredCountries != null && preferredCountries.size() > 0) {
            for (Country next : preferredCountries) {
                if (next.isEligibleForQuery(str)) {
                    this.mTempCountries.add(next);
                }
            }
            if (this.mTempCountries.size() > 0) {
                this.mTempCountries.add((Country) null);
            }
        }
        for (Country next2 : this.masterCountries) {
            if (next2.isEligibleForQuery(str)) {
                this.mTempCountries.add(next2);
            }
        }
        return this.mTempCountries;
    }
}
