package com.calleridname.calldetailcallhistory.numberlocation_activity.countrycodepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.calleridname.calldetailcallhistory.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;

@SuppressLint("ResourceType")

public class CountryCode_Picker extends RelativeLayout {
    private static final int DEFAULT_BACKGROUND_COLOR = 0;
    private static final String DEFAULT_ISO_COUNTRY = "ID";
    private static final int DEFAULT_TEXT_COLOR = 0;
    private static String TAG = "CountryCodePicker";
    private final String DEFAULT_COUNTRY = Locale.getDefault().getCountry();
    private int mBackgroundColor = 0;
    private CountryCode_Dialog mCallLocatorCountryCodeDialog;
    private OnClickListener mCountryCodeHolderClickListener;
    private String mCountryPreference;
    private String mCustomMasterCountries;
    private List<Country> mCustomMasterCountriesList;
    private Country mDefaultCallLocatorCountry;
    private int mDefaultCountryCode;
    private String mDefaultCountryNameCode;
    private int mDialogTextColor = 0;
    private boolean mHideNameCode = false;
    private boolean mHidePhoneCode = false;
    private ImageView mImvArrow;
    private ImageView mImvFlag;
    private boolean mIsClickable = true;
    private boolean mIsEnablePhoneNumberWatcher = true;
    private boolean mIsHintEnabled = true;
    private boolean mKeyboardAutoPopOnSearch = true;
    private LinearLayout mLlyFlagHolder;
    private OnCountryChangeListener mOnCountryChangeListener;
    PhoneNumberInputValidityListener mPhoneNumberInputValidityListener;
    private PhoneNumberWatcher mPhoneNumberWatcher;
    public PhoneNumberUtil mPhoneUtil;
    private List<Country> mPreferredCountries;
    private TextView mRegisteredPhoneNumberTextView;
    private RelativeLayout mRlyClickConsumer;
    private RelativeLayout mRlyHolder;
    public Country mSelectedCallLocatorCountry;
    private boolean mSelectionDialogShowSearch = true;
    private boolean mSetCountryByTimeZone = true;
    private boolean mShowFlag = true;
    private boolean mShowFullName = false;
    private int mTextColor = 0;
    private TextView mTvSelectedCountry;
    private Typeface mTypeFace;
    private boolean mUseFullName = false;

    public interface OnCountryChangeListener {
        void onCountrySelected(Country country);
    }

    public interface PhoneNumberInputValidityListener {
        void onFinish(CountryCode_Picker countryCode_Picker, boolean z);
    }

    public int getDefaultBackgroundColor() {
        return 0;
    }

    public int getDefaultContentColor() {
        return 0;
    }

    private class PhoneNumberWatcher extends PhoneNumberFormattingTextWatcher {
        private boolean lastValidity;
        private String previousCountryCode = "";

        public String getPreviousCountryCode() {
            return this.previousCountryCode;
        }

        public PhoneNumberWatcher() {
        }

        public PhoneNumberWatcher(String str) {
            super(str);
            this.previousCountryCode = str;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            super.onTextChanged(charSequence, i, i2, i3);
            try {
                CountryCode_Picker.this.mPhoneUtil.getRegionCodeForNumber(CountryCode_Picker.this.mPhoneUtil.parse(charSequence.toString(), CountryCode_Picker.this.mSelectedCallLocatorCountry != null ? CountryCode_Picker.this.mSelectedCallLocatorCountry.getPhoneCode().toUpperCase() : null));
            } catch (NumberParseException unused) {
            }
            if (CountryCode_Picker.this.mPhoneNumberInputValidityListener != null) {
                boolean isValid = CountryCode_Picker.this.isValid();
                if (isValid != this.lastValidity) {
                    CountryCode_Picker.this.mPhoneNumberInputValidityListener.onFinish(CountryCode_Picker.this, isValid);
                }
                this.lastValidity = isValid;
            }
        }
    }

    public CountryCode_Picker(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public CountryCode_Picker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public CountryCode_Picker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public CountryCode_Picker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet);
    }

    private long mLastClickTime = 0;
    private void init(AttributeSet attributeSet) {
        inflate(getContext(), R.layout.pgrs_country_code_picker_layout_code_picker_layout, this);
        this.mTvSelectedCountry = (TextView) findViewById(R.id.selected_country_tv);
        this.mRlyHolder = (RelativeLayout) findViewById(R.id.country_code_holder_rly);
        this.mImvArrow = (ImageView) findViewById(R.id.arrow_imv);
        this.mImvFlag = (ImageView) findViewById(R.id.flag_imv);
        this.mLlyFlagHolder = (LinearLayout) findViewById(R.id.flag_holder_lly);
        this.mRlyClickConsumer = (RelativeLayout) findViewById(R.id.click_consumer_rly);
        applyCustomProperty(attributeSet);
        OnClickListener r3 = new OnClickListener() {
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                if (CountryCode_Picker.this.isClickable()) {
                    CountryCode_Picker.this.showCountryCodePickerDialog();
                }
            }
        };
        this.mCountryCodeHolderClickListener = r3;
        this.mRlyClickConsumer.setOnClickListener(r3);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void applyCustomProperty(AttributeSet attributeSet) {
        this.mPhoneUtil = PhoneNumberUtil.createInstance(getContext());
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.CountryCodePicker, 0, 0);
        try {
            this.mHidePhoneCode = obtainStyledAttributes.getBoolean(11, false);
            this.mShowFullName = obtainStyledAttributes.getBoolean(16, false);
            this.mHideNameCode = obtainStyledAttributes.getBoolean(10, false);
            this.mIsHintEnabled = obtainStyledAttributes.getBoolean(8, true);
            this.mIsEnablePhoneNumberWatcher = obtainStyledAttributes.getBoolean(9, true);
            setKeyboardAutoPopOnSearch(obtainStyledAttributes.getBoolean(12, true));
            this.mCustomMasterCountries = obtainStyledAttributes.getString(4);
            refreshCustomMasterList();
            this.mCountryPreference = obtainStyledAttributes.getString(3);
            refreshPreferredCountries();
            applyCustomPropertyOfDefaultCountryNameCode(obtainStyledAttributes);
            showFlag(obtainStyledAttributes.getBoolean(15, true));
            applyCustomPropertyOfColor(obtainStyledAttributes);
            String string = obtainStyledAttributes.getString(18);
            if (string != null && !string.isEmpty()) {
                setTypeFace(string);
            }
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(19, 0);
            if (dimensionPixelSize > 0) {
                this.mTvSelectedCountry.setTextSize(0, (float) dimensionPixelSize);
                setFlagSize(dimensionPixelSize);
                setArrowSize(dimensionPixelSize);
            } else {
                setTextSize(Math.round((getContext().getResources().getDisplayMetrics().xdpi / 160.0f) * 18.0f));
            }
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            if (dimensionPixelSize2 > 0) {
                setArrowSize(dimensionPixelSize2);
            }
            this.mSelectionDialogShowSearch = obtainStyledAttributes.getBoolean(13, true);
            setClickable(obtainStyledAttributes.getBoolean(2, true));
            this.mSetCountryByTimeZone = obtainStyledAttributes.getBoolean(14, true);
            String str = this.mDefaultCountryNameCode;
            if (str == null || str.isEmpty()) {
                setDefaultCountryFlagAndCode();
            }
        } catch (Exception e) {
            String str2 = TAG;
            Log.d(str2, "exception = " + e.toString());
            if (isInEditMode()) {
                this.mTvSelectedCountry.setText(getContext().getString(R.string.phone_code, new Object[]{getContext().getString(R.string.country_indonesia_number)}));
            } else {
                this.mTvSelectedCountry.setText(e.getMessage());
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        obtainStyledAttributes.recycle();
    }

    private void applyCustomPropertyOfDefaultCountryNameCode(TypedArray typedArray) {
        String string = typedArray.getString(6);
        this.mDefaultCountryNameCode = string;
        if (string != null && !string.isEmpty()) {
            if (this.mDefaultCountryNameCode.trim().isEmpty()) {
                this.mDefaultCountryNameCode = null;
                return;
            }
            setDefaultCountryUsingNameCode(this.mDefaultCountryNameCode);
            setSelectedCountry(this.mDefaultCallLocatorCountry);
        }
    }

    private void applyCustomPropertyOfColor(TypedArray typedArray) {
        int i;
        if (isInEditMode()) {
            i = typedArray.getColor(17, 0);
        } else {
            i = typedArray.getColor(17, getColor(getContext(), R.color.white));
        }
        if (i != 0) {
            setTextColor(i);
        }
        this.mDialogTextColor = typedArray.getColor(7, 0);
        int color = typedArray.getColor(1, 0);
        this.mBackgroundColor = color;
        if (color != 0) {
            this.mRlyHolder.setBackgroundColor(color);
        }
    }

    private Country getDefaultCountry() {
        return this.mDefaultCallLocatorCountry;
    }

    private void setDefaultCountry(Country country) {
        this.mDefaultCallLocatorCountry = country;
    }

    private Country getSelectedCountry() {
        return this.mSelectedCallLocatorCountry;
    }

    public void setSelectedCountry(Country country) {
        this.mSelectedCallLocatorCountry = country;
        Context context = getContext();
        if (country == null) {
            country = CountryUtils34.getByCode(context, this.mPreferredCountries, this.mDefaultCountryCode);
        }
        TextView textView = this.mRegisteredPhoneNumberTextView;
        if (textView != null) {
            setPhoneNumberWatcherToTextView(textView, country.getIso().toUpperCase());
        }
        OnCountryChangeListener onCountryChangeListener = this.mOnCountryChangeListener;
        if (onCountryChangeListener != null) {
            onCountryChangeListener.onCountrySelected(country);
        }
        try {
            this.mImvFlag.setImageResource(CountryUtils34.getFlagDrawableResId(country));
        } catch (Exception unused) {
        }
        if (this.mIsHintEnabled) {
            setPhoneNumberHint();
        }
        setSelectedCountryText(context, country);
    }

    private void setSelectedCountryText(Context context, Country country) {
        if (!this.mHideNameCode || !this.mHidePhoneCode || this.mShowFullName) {
            String phoneCode = country.getPhoneCode();
            if (this.mShowFullName) {
                String upperCase = country.getName().toUpperCase();
                if (this.mHidePhoneCode && this.mHideNameCode) {
                    this.mTvSelectedCountry.setText(upperCase);
                } else if (this.mHideNameCode) {
                    this.mTvSelectedCountry.setText(context.getString(R.string.country_full_name_and_phone_code, new Object[]{upperCase, phoneCode}));
                } else {
                    String upperCase2 = country.getIso().toUpperCase();
                    if (this.mHidePhoneCode) {
                        this.mTvSelectedCountry.setText(context.getString(R.string.country_full_name_and_name_code, new Object[]{upperCase, upperCase2}));
                        return;
                    }
                    this.mTvSelectedCountry.setText(context.getString(R.string.country_full_name_name_code_and_phone_code, new Object[]{upperCase, upperCase2, phoneCode}));
                }
            } else {
                boolean z = this.mHideNameCode;
                if (z && this.mHidePhoneCode) {
                    this.mTvSelectedCountry.setText(country.getName().toUpperCase());
                } else if (z) {
                    this.mTvSelectedCountry.setText(context.getString(R.string.phone_code, new Object[]{phoneCode}));
                } else if (this.mHidePhoneCode) {
                    this.mTvSelectedCountry.setText(country.getIso().toUpperCase());
                } else {
                    String upperCase3 = country.getIso().toUpperCase();
                    this.mTvSelectedCountry.setText(context.getString(R.string.country_code_and_phone_code, new Object[]{upperCase3, phoneCode}));
                }
            }
        } else {
            this.mTvSelectedCountry.setText("");
        }
    }

    public boolean isKeyboardAutoPopOnSearch() {
        return this.mKeyboardAutoPopOnSearch;
    }

    public void setKeyboardAutoPopOnSearch(boolean z) {
        this.mKeyboardAutoPopOnSearch = z;
    }

    public boolean isPhoneAutoFormatterEnabled() {
        return this.mIsEnablePhoneNumberWatcher;
    }

    public void enablePhoneAutoFormatter(boolean z) {
        this.mIsEnablePhoneNumberWatcher = z;
        if (!z) {
            this.mPhoneNumberWatcher = null;
        } else if (this.mPhoneNumberWatcher == null) {
            this.mPhoneNumberWatcher = new PhoneNumberWatcher(getSelectedCountryNameCode());
        }
    }

    private OnClickListener getCountryCodeHolderClickListener() {
        return this.mCountryCodeHolderClickListener;
    }

    public void refreshPreferredCountries() {
        String str = this.mCountryPreference;
        if (str == null || str.length() == 0) {
            this.mPreferredCountries = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String byNameCodeFromCustomCountries : this.mCountryPreference.split(",")) {
            Country byNameCodeFromCustomCountries2 = CountryUtils34.getByNameCodeFromCustomCountries(getContext(), this.mCustomMasterCountriesList, byNameCodeFromCustomCountries);
            if (byNameCodeFromCustomCountries2 != null && !isAlreadyInList(byNameCodeFromCustomCountries2, arrayList)) {
                arrayList.add(byNameCodeFromCustomCountries2);
            }
        }
        if (arrayList.size() == 0) {
            this.mPreferredCountries = null;
        } else {
            this.mPreferredCountries = arrayList;
        }
    }

    public void refreshCustomMasterList() {
        String str = this.mCustomMasterCountries;
        if (str == null || str.length() == 0) {
            this.mCustomMasterCountriesList = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String byNameCodeFromAllCountries : this.mCustomMasterCountries.split(",")) {
            Country byNameCodeFromAllCountries2 = CountryUtils34.getByNameCodeFromAllCountries(getContext(), byNameCodeFromAllCountries);
            if (byNameCodeFromAllCountries2 != null && !isAlreadyInList(byNameCodeFromAllCountries2, arrayList)) {
                arrayList.add(byNameCodeFromAllCountries2);
            }
        }
        if (arrayList.size() == 0) {
            this.mCustomMasterCountriesList = null;
        } else {
            this.mCustomMasterCountriesList = arrayList;
        }
    }

    public List<Country> getCustomCountries() {
        return this.mCustomMasterCountriesList;
    }

    public List<Country> getCustomCountries(CountryCode_Picker countryCode_Picker) {
        countryCode_Picker.refreshCustomMasterList();
        if (countryCode_Picker.getCustomCountries() == null || countryCode_Picker.getCustomCountries().size() <= 0) {
            return CountryUtils34.getAllCountries(countryCode_Picker.getContext());
        }
        return countryCode_Picker.getCustomCountries();
    }

    public void setCustomMasterCountriesList(List<Country> list) {
        this.mCustomMasterCountriesList = list;
    }

    public String getCustomMasterCountries() {
        return this.mCustomMasterCountries;
    }

    public List<Country> getPreferredCountries() {
        return this.mPreferredCountries;
    }

    public void setCustomMasterCountries(String str) {
        this.mCustomMasterCountries = str;
    }

    private boolean isAlreadyInList(Country country, List<Country> list) {
        if (!(country == null || list == null)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIso().equalsIgnoreCase(country.getIso())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String detectCarrierNumber(String str, Country country) {
        throw new UnsupportedOperationException("Method not decompiled: com.mobilecallerlocation.phonenumberlocation.currentmobilelocationtrack.countrycodepicker.CountryCodePicker.detectCarrierNumber(java.lang.String, com.mobilecallerlocation.phonenumberlocation.currentmobilelocationtrack.countrycodepicker.Country):java.lang.String");
    }

    @Deprecated
    public void setDefaultCountryUsingPhoneCode(int i) {
        Country byCode = CountryUtils34.getByCode(getContext(), this.mPreferredCountries, i);
        if (byCode != null) {
            this.mDefaultCountryCode = i;
            setDefaultCountry(byCode);
        }
    }

    public void setDefaultCountryUsingPhoneCodeAndApply(int i) {
        Country byCode = CountryUtils34.getByCode(getContext(), this.mPreferredCountries, i);
        if (byCode != null) {
            this.mDefaultCountryCode = i;
            setDefaultCountry(byCode);
            resetToDefaultCountry();
        }
    }

    public void setDefaultCountryUsingNameCode(String str) {
        Country byNameCodeFromAllCountries = CountryUtils34.getByNameCodeFromAllCountries(getContext(), str);
        if (byNameCodeFromAllCountries != null) {
            this.mDefaultCountryNameCode = byNameCodeFromAllCountries.getIso();
            setDefaultCountry(byNameCodeFromAllCountries);
        }
    }

    public void setDefaultCountryUsingNameCodeAndApply(String str) {
        Country byNameCodeFromAllCountries = CountryUtils34.getByNameCodeFromAllCountries(getContext(), str);
        if (byNameCodeFromAllCountries != null) {
            this.mDefaultCountryNameCode = byNameCodeFromAllCountries.getIso();
            setDefaultCountry(byNameCodeFromAllCountries);
            setEmptyDefault((String) null);
        }
    }

    public String getDefaultCountryCode() {
        return this.mDefaultCallLocatorCountry.getPhoneCode();
    }

    public int getDefaultCountryCodeAsInt() {
        try {
            return Integer.parseInt(getDefaultCountryCode());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getDefaultCountryCodeWithPlus() {
        return getContext().getString(R.string.phone_code, new Object[]{getDefaultCountryCode()});
    }

    public String getDefaultCountryName() {
        return this.mDefaultCallLocatorCountry.getName();
    }

    public String getDefaultCountryNameCode() {
        return this.mDefaultCallLocatorCountry.getIso().toUpperCase();
    }

    public void resetToDefaultCountry() {
        setEmptyDefault();
    }

    public String getSelectedCountryCode() {
        return this.mSelectedCallLocatorCountry.getPhoneCode();
    }

    public String getSelectedCountryCodeWithPlus() {
        return getContext().getString(R.string.phone_code, new Object[]{getSelectedCountryCode()});
    }

    public int getSelectedCountryCodeAsInt() {
        try {
            return Integer.parseInt(getSelectedCountryCode());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getSelectedCountryName() {
        return this.mSelectedCallLocatorCountry.getName();
    }

    public String getSelectedCountryNameCode() {
        return this.mSelectedCallLocatorCountry.getIso().toUpperCase();
    }

    public void setCountryForPhoneCode(int i) {
        Context context = getContext();
        Country byCode = CountryUtils34.getByCode(context, this.mPreferredCountries, i);
        if (byCode == null) {
            if (this.mDefaultCallLocatorCountry == null) {
                this.mDefaultCallLocatorCountry = CountryUtils34.getByCode(context, this.mPreferredCountries, this.mDefaultCountryCode);
            }
            setSelectedCountry(this.mDefaultCallLocatorCountry);
            return;
        }
        setSelectedCountry(byCode);
    }

    public void setCountryForNameCode(String str) {
        Context context = getContext();
        Country byNameCodeFromAllCountries = CountryUtils34.getByNameCodeFromAllCountries(context, str);
        if (byNameCodeFromAllCountries == null) {
            if (this.mDefaultCallLocatorCountry == null) {
                this.mDefaultCallLocatorCountry = CountryUtils34.getByCode(context, this.mPreferredCountries, this.mDefaultCountryCode);
            }
            setSelectedCountry(this.mDefaultCallLocatorCountry);
            return;
        }
        setSelectedCountry(byNameCodeFromAllCountries);
    }

    public void registerPhoneNumberTextView(TextView textView) {
        setRegisteredPhoneNumberTextView(textView);
        if (this.mIsHintEnabled) {
            setPhoneNumberHint();
        }
    }

    public TextView getRegisteredPhoneNumberTextView() {
        return this.mRegisteredPhoneNumberTextView;
    }

    public void setRegisteredPhoneNumberTextView(TextView textView) {
        this.mRegisteredPhoneNumberTextView = textView;
        if (this.mIsEnablePhoneNumberWatcher) {
            if (this.mPhoneNumberWatcher == null) {
                this.mPhoneNumberWatcher = new PhoneNumberWatcher(getDefaultCountryNameCode());
            }
            this.mRegisteredPhoneNumberTextView.addTextChangedListener(this.mPhoneNumberWatcher);
        }
    }

    private void setPhoneNumberWatcherToTextView(TextView textView, String str) {
        if (this.mIsEnablePhoneNumberWatcher) {
            PhoneNumberWatcher phoneNumberWatcher = this.mPhoneNumberWatcher;
            if (phoneNumberWatcher == null) {
                PhoneNumberWatcher phoneNumberWatcher2 = new PhoneNumberWatcher(str);
                this.mPhoneNumberWatcher = phoneNumberWatcher2;
                textView.addTextChangedListener(phoneNumberWatcher2);
            } else if (!phoneNumberWatcher.getPreviousCountryCode().equalsIgnoreCase(str)) {
                textView.removeTextChangedListener(this.mPhoneNumberWatcher);
                PhoneNumberWatcher phoneNumberWatcher3 = new PhoneNumberWatcher(str);
                this.mPhoneNumberWatcher = phoneNumberWatcher3;
                textView.addTextChangedListener(phoneNumberWatcher3);
            }
        }
    }

    public String getFullNumber() {
        String phoneCode = this.mSelectedCallLocatorCountry.getPhoneCode();
        if (this.mRegisteredPhoneNumberTextView == null) {
            Log.w(TAG, getContext().getString(R.string.error_unregister_carrier_number));
            return phoneCode;
        }
        return phoneCode + this.mRegisteredPhoneNumberTextView.getText().toString();
    }

    public void setFullNumber(String str) {
        Country byNumber = CountryUtils34.getByNumber(getContext(), this.mPreferredCountries, str);
        setSelectedCountry(byNumber);
        String detectCarrierNumber = detectCarrierNumber(str, byNumber);
        TextView textView = this.mRegisteredPhoneNumberTextView;
        if (textView == null) {
            Log.w(TAG, getContext().getString(R.string.error_unregister_carrier_number));
        } else {
            textView.setText(detectCarrierNumber);
        }
    }

    public String getFullNumberWithPlus() {
        return getContext().getString(R.string.phone_code, new Object[]{getFullNumber()});
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public void setTextColor(int i) {
        this.mTextColor = i;
        this.mTvSelectedCountry.setTextColor(i);
        this.mImvArrow.setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        this.mRlyHolder.setBackgroundColor(i);
    }

    public void setTextSize(int i) {
        if (i > 0) {
            this.mTvSelectedCountry.setTextSize(0, (float) i);
            setArrowSize(i);
            setFlagSize(i);
        }
    }

    private void setArrowSize(int i) {
        if (i > 0) {
            LayoutParams layoutParams = (LayoutParams) this.mImvArrow.getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i;
            this.mImvArrow.setLayoutParams(layoutParams);
        }
    }

    public void hideNameCode(boolean z) {
        this.mHideNameCode = z;
        setSelectedCountry(this.mSelectedCallLocatorCountry);
    }

    public void setCountryPreference(String str) {
        this.mCountryPreference = str;
    }

    public void setTypeFace(Typeface typeface) {
        this.mTypeFace = typeface;
        try {
            this.mTvSelectedCountry.setTypeface(typeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTypeFace(String str) {
        try {
            Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), str);
            this.mTypeFace = createFromAsset;
            this.mTvSelectedCountry.setTypeface(createFromAsset);
        } catch (Exception e) {
            String str2 = TAG;
            Log.d(str2, "Invalid fontPath. " + e.toString());
        }
    }

    public void setTypeFace(Typeface typeface, int i) {
        try {
            this.mTvSelectedCountry.setTypeface(typeface, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typeface getTypeFace() {
        return this.mTypeFace;
    }

    public void setOnCountryChangeListener(OnCountryChangeListener onCountryChangeListener) {
        this.mOnCountryChangeListener = onCountryChangeListener;
    }

    public void setFlagSize(int i) {
        this.mImvFlag.getLayoutParams().height = i;
        this.mImvFlag.requestLayout();
    }

    public void showFlag(boolean z) {
        this.mShowFlag = z;
        this.mLlyFlagHolder.setVisibility(z ? 0 : 8);
    }

    public void showFullName(boolean z) {
        this.mShowFullName = z;
        setSelectedCountry(this.mSelectedCallLocatorCountry);
    }

    public boolean isSelectionDialogShowSearch() {
        return this.mSelectionDialogShowSearch;
    }

    public void setSelectionDialogShowSearch(boolean z) {
        this.mSelectionDialogShowSearch = z;
    }

    public boolean isClickable() {
        return this.mIsClickable;
    }

    public void setClickable(boolean z) {
        this.mIsClickable = z;
        this.mRlyClickConsumer.setOnClickListener(z ? this.mCountryCodeHolderClickListener : null);
        this.mRlyClickConsumer.setClickable(z);
        this.mRlyClickConsumer.setEnabled(z);
    }

    public boolean isHidePhoneCode() {
        return this.mHidePhoneCode;
    }

    public boolean isHideNameCode() {
        return this.mHideNameCode;
    }

    public boolean isHintEnabled() {
        return this.mIsHintEnabled;
    }

    public void enableHint(boolean z) {
        this.mIsHintEnabled = z;
        if (z) {
            setPhoneNumberHint();
        }
    }

    public void hidePhoneCode(boolean z) {
        this.mHidePhoneCode = z;
        setSelectedCountry(this.mSelectedCallLocatorCountry);
    }

    private void setPhoneNumberHint() {
        Country country;
        if (this.mRegisteredPhoneNumberTextView != null && (country = this.mSelectedCallLocatorCountry) != null && country.getIso() != null) {
            Phonenumber.PhoneNumber exampleNumberForType = this.mPhoneUtil.getExampleNumberForType(this.mSelectedCallLocatorCountry.getIso().toUpperCase(), PhoneNumberUtil.PhoneNumberType.MOBILE);
            if (exampleNumberForType == null) {
                this.mRegisteredPhoneNumberTextView.setHint("");
            } else {
                this.mRegisteredPhoneNumberTextView.setHint(this.mPhoneUtil.format(exampleNumberForType, PhoneNumberUtil.PhoneNumberFormat.NATIONAL));
            }
        }
    }

    public String getNumber() {
        Phonenumber.PhoneNumber phoneNumber = getPhoneNumber();
        if (phoneNumber == null) {
            return null;
        }
        if (this.mRegisteredPhoneNumberTextView != null) {
            return this.mPhoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
        }
        Log.w(TAG, getContext().getString(R.string.error_unregister_carrier_number));
        return null;
    }

    public Phonenumber.PhoneNumber getPhoneNumber() {
        try {
            Country country = this.mSelectedCallLocatorCountry;
            String upperCase = country != null ? country.getIso().toUpperCase() : null;
            TextView textView = this.mRegisteredPhoneNumberTextView;
            if (textView != null) {
                return this.mPhoneUtil.parse(textView.getText().toString(), upperCase);
            }
            Log.w(TAG, getContext().getString(R.string.error_unregister_carrier_number));
            return null;
        } catch (NumberParseException unused) {
        }
        return null;
    }

    public boolean isValid() {
        Phonenumber.PhoneNumber phoneNumber = getPhoneNumber();
        return phoneNumber != null && this.mPhoneUtil.isValidNumber(phoneNumber);
    }

    public void setPhoneNumberInputValidityListener(PhoneNumberInputValidityListener phoneNumberInputValidityListener) {
        this.mPhoneNumberInputValidityListener = phoneNumberInputValidityListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setDefaultCountryFlagAndCode() {
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService("phone");
        if (telephonyManager == null) {
            Log.e(TAG, "Can't access TelephonyManager. Using default county code");
            setEmptyDefault(getDefaultCountryCode());
            return;
        }
        try {
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (simCountryIso != null && !simCountryIso.isEmpty()) {
                setEmptyDefault(simCountryIso);
            }
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            if (networkCountryIso != null && !networkCountryIso.isEmpty()) {
                setEmptyDefault(networkCountryIso);
            }
            enableSetCountryByTimeZone(true);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Error when getting sim country, error = " + e.toString());
            setEmptyDefault(getDefaultCountryCode());
        }
    }

    private void setEmptyDefault() {
        setEmptyDefault((String) null);
    }

    private void setEmptyDefault(String str) {
        if (str == null || str.isEmpty()) {
            String str2 = this.mDefaultCountryNameCode;
            if (str2 == null || str2.isEmpty()) {
                String str3 = this.DEFAULT_COUNTRY;
                str = (str3 == null || str3.isEmpty()) ? DEFAULT_ISO_COUNTRY : this.DEFAULT_COUNTRY;
            } else {
                str = this.mDefaultCountryNameCode;
            }
        }
        if (this.mIsEnablePhoneNumberWatcher && this.mPhoneNumberWatcher == null) {
            this.mPhoneNumberWatcher = new PhoneNumberWatcher(str);
        }
        setDefaultCountryUsingNameCode(str);
        setSelectedCountry(getDefaultCountry());
    }

    public void enableSetCountryByTimeZone(boolean z) {
        if (z) {
            String str = this.mDefaultCountryNameCode;
            if ((str != null && !str.isEmpty()) || this.mRegisteredPhoneNumberTextView != null) {
                return;
            }
            if (this.mSetCountryByTimeZone) {
                List<String> countryIsoByTimeZone = CountryUtils34.getCountryIsoByTimeZone(getContext(), TimeZone.getDefault().getID());
                if (countryIsoByTimeZone == null) {
                    setEmptyDefault();
                } else {
                    setDefaultCountryUsingNameCode(countryIsoByTimeZone.get(0));
                    setSelectedCountry(getDefaultCountry());
                }
            }
        }
        this.mSetCountryByTimeZone = z;
    }

    public int getDialogTextColor() {
        return this.mDialogTextColor;
    }

    public void setDialogTextColor(int i) {
        this.mDialogTextColor = i;
    }

    public static int getColor(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(i);
        }
        return context.getResources().getColor(i);
    }

    public void showCountryCodePickerDialog() {
        if (this.mCallLocatorCountryCodeDialog == null) {
            this.mCallLocatorCountryCodeDialog = new CountryCode_Dialog(this);
        }
        this.mCallLocatorCountryCodeDialog.show();
    }
}
