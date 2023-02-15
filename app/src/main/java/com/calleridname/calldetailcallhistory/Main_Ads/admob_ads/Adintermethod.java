package com.calleridname.calldetailcallhistory.Main_Ads.admob_ads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.calleridname.calldetailcallhistory.Main_Ads.Blue_mycustomtab.Blue_LoadCustom;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.R;

import java.util.ArrayList;
import java.util.List;

public class Adintermethod {

    static Context activity;
    public static DataItem convertedObject;
    private int adcount;
    private int backadcount;

    public static MyCallback myCallback;
    static onAdIntent adIntent;
    static oncloseintent adIntent1;
    private Dialog adprogress;

    InterstitialAd mInterstitialAd;
    private static Adintermethod mInstance;

    public static NativeAd mNativebannerAd;
    public static NativeAd mNativeAd;

    public Adintermethod(Context context) {
        activity = context;
    }

    public interface MyCallback {
        void OnCall();
    }

    public interface onAdIntent {
        public void onintentscreen();
    }

    public interface oncloseintent {
        public void onclosead();
    }

    public void addialogshow(Context activity) {
        //todo Ad Loading Dialog
        adprogress = new Dialog(activity, R.style.Custom);
        adprogress.requestWindowFeature(1);
        adprogress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        adprogress.setCancelable(false);
        adprogress.setContentView(R.layout.ads_loading_dialog_inter);
        adprogress.show();
    }

    public void addialoghide(Context activity) {
        adprogress.dismiss();
    }

    public static Adintermethod getInstance(Context context) {
        activity = context;
        convertedObject = Utils.getResponse(context);
        if (mInstance == null) {
            mInstance = new Adintermethod(context);
        }
        return mInstance;
    }

    public void ShowInter(Activity activity) {

        if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.admob)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getBackpressadcount()) {
                SharedPreferencesManager.getInstance(activity).storeClicks(1);
                ShowAdmobInterstitialAd(activity);
            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.fb)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).storeClicks(1);
                addialogshow(activity);
                show_FB_Interstitial(activity, null, null);
            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.appopen)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).storeClicks(1);
                addialogshow(activity);
                show_Appopen_Interstitial(activity, null, null);
            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.qureka)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).storeClicks(1);
                Blue_LoadCustom.myCustom(activity, convertedObject.getQurekaUrl());
            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
            }
        }
    }

    public void ShowotherInter(Activity activity, oncloseintent oncloseintent) {

        if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.admob)) {
            Adintermethod.adIntent1 = oncloseintent;
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).storeClicks(1);
                ShowAdmobInterstitialAdclosemethod(activity, oncloseintent);
            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
                oncloseadCllback();
            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.fb)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).storeClicks(1);
                addialogshow(activity);
                show_FB_Interstitial(activity, null, null);
            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.appopen)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).storeClicks(1);
                addialogshow(activity);
                show_Appopen_Interstitial(activity, null, null);
            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.qureka)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).storeClicks(1);
                Blue_LoadCustom.myCustom(activity, convertedObject.getQurekaUrl());
            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
            }
        }
    }

    public void ShowInterBack(Activity activity) {

        if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.admob)) {
            backadcount = SharedPreferencesManager.getInstance(activity).getbackpressNumberOfClicks();
            if (backadcount == convertedObject.getBackpressadcount()) {
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(1);
                ShowAdmobInterstitialAd(activity);
            } else {
                backadcount = backadcount + 1;
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(backadcount);
            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.fb)) {
            backadcount = SharedPreferencesManager.getInstance(activity).getbackpressNumberOfClicks();
            if (backadcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(1);
                addialogshow(activity);
                show_FB_Interstitial(activity, null, null);
            } else {
                backadcount = backadcount + 1;
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(backadcount);

            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.appopen)) {
            backadcount = SharedPreferencesManager.getInstance(activity).getbackpressNumberOfClicks();
            if (backadcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(1);
                addialogshow(activity);
                show_Appopen_Interstitial(activity, null, null);
            } else {
                backadcount = backadcount + 1;
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(backadcount);

            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.qureka)) {
            backadcount = SharedPreferencesManager.getInstance(activity).getbackpressNumberOfClicks();
            if (backadcount == convertedObject.getAdcount()) {
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(1);
                Blue_LoadCustom.myCustom(activity, convertedObject.getQurekaUrl());
            } else {
                backadcount = backadcount + 1;
                SharedPreferencesManager.getInstance(activity).backpressstoreClicks(backadcount);

            }
        }
    }

    public void ShowAdmobInterstitialAd(Context context) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show((Activity) context);
            Log.e("mInterstitialAd", "first");
            return;
        } /*else {
            staticShow_Interstitial((Activity) context);
        }*/
        Log.d("check52146", "" + mInterstitialAd);
        LoadAdmobInterstitial((Activity) context);
    }

    public void ShowAdmobInterstitialAdclosemethod(Activity activity, oncloseintent oncloseintent) {
        Adintermethod.adIntent1 = oncloseintent;
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
            Log.e("mInterstitialAd", "first");
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                public void onAdDismissedFullScreenContent() {
                    Log.d("TAG", "The ad was dismissed.");
                    mInterstitialAd = null;
                    LoadAdmobInterstitial(activity);
                    oncloseadCllback();
                }

                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.d("TAG", "The ad failed to show.");
                    oncloseadCllback();
                }

                public void onAdShowedFullScreenContent() {
                    LoadAdmobInterstitial(activity);
                    Log.d("TAG", "The ad was shown.");
                }
            });

        }
        Log.d("check52146", "" + mInterstitialAd);
        LoadAdmobInterstitial(activity);
    }

    public void LoadAdmobInterstitial(final Activity activity2) {
        InterstitialAd.load(activity2, convertedObject.getAdmobInterid(), new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
            public void onAdLoaded(InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                Log.i("TAG", "onAdLoaded");
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    public void onAdDismissedFullScreenContent() {
                        Log.d("TAG", "The ad was dismissed.");
                    }

                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        Log.d("TAG", "The ad failed to show.");
                    }

                    public void onAdShowedFullScreenContent() {
                        mInterstitialAd = null;
                        LoadAdmobInterstitial(activity2);
                        Log.d("TAG", "The ad was shown.");
                    }
                });
            }

            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.i("TAG", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
    }

    public void adintercheck(DataItem convertedObject, final Activity activity, String fbinter2, onAdIntent adIntent) {

        Adintermethod.adIntent = adIntent;
        if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.admob)) {
            addialogshow(activity);
            staticShow_Interstitial(activity, convertedObject.getAdmobInterid(), new MyCallback() {
                @Override
                public void OnCall() {
                    addialoghide(activity);
                    adIntentCallback();

                }
            });
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.fb)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                addialogshow(activity);
                show_FB_Interstitial(activity, fbinter2, new MyCallback() {
                    @Override
                    public void OnCall() {
                        addialoghide(activity);
                        SharedPreferencesManager.getInstance(activity).storeClicks(1);
                        adIntentCallback();

                    }
                });

            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
                adIntentCallback();

            }
        } else if (convertedObject.getCheckInter().equalsIgnoreCase(Constant.appopen)) {
            adcount = SharedPreferencesManager.getInstance(activity).getNumberOfClicks();
            if (adcount == convertedObject.getAdcount()) {
                addialogshow(activity);
                show_Appopen_Interstitial(activity, convertedObject.getAppopenadId(), new MyCallback() {
                    @Override
                    public void OnCall() {
                        addialoghide(activity);
                        SharedPreferencesManager.getInstance(activity).storeClicks(1);
                        adIntentCallback();

                    }
                });

            } else {
                adcount = adcount + 1;
                SharedPreferencesManager.getInstance(activity).storeClicks(adcount);
                adIntentCallback();
            }
        } else {
            adIntentCallback();

        }
    }

    public void staticShow_Interstitial(Activity activity, String admobInterid, MyCallback myCallback2) {
        myCallback = myCallback2;
        InterstitialAd.load(activity, admobInterid, new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
                                if (myCallback != null) {
                                    myCallback.OnCall();
                                    myCallback = null;

                                }
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                                if (myCallback != null) {
                                    myCallback.OnCall();
                                    myCallback = null;
                                }
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;
                                getInstance(activity).LoadAdmobInterstitial(activity);
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                        mInterstitialAd = null;
                        if (myCallback != null) {
                            myCallback.OnCall();
                            myCallback = null;
                        }
                    }
                });

    }

    public AppOpenAd appOpenAd = null;
    FullScreenContentCallback fullScreenContentCallback;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailable() {
        return this.appOpenAd != null;
    }

    public void show_Appopen_Interstitial(Activity activity2, String fbinter2, MyCallback myCallback2) {
        myCallback = myCallback2;
        if (!isAdAvailable()) {
            fullScreenContentCallback = new FullScreenContentCallback() {
                public void onAdDismissedFullScreenContent() {
                    if (Adintermethod.myCallback != null) {
                        Adintermethod.myCallback.OnCall();
                        Adintermethod.myCallback = null;
                    }
                }

                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e("LOG_TAG", adError.getMessage());
                    if (Adintermethod.myCallback != null) {
                        Adintermethod.myCallback.OnCall();
                        Adintermethod.myCallback = null;
                    }
                }

                public void onAdShowedFullScreenContent() {
                    Log.e("TAG", "onAdDismissedFullScreenContent:====> show ");
                }
            };
            getAdsLoad(activity2, myCallback2);
        }
    }

    private void getAdsLoad(final Activity activity2, MyCallback myCallback2) {
        myCallback = myCallback2;
        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            public void onAdLoaded(AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                addialoghide(activity2);
                appOpenAd.show(activity2);
                appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                AppOpenAd unused = appOpenAd = appOpenAd;

            }

            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                addialoghide(activity2);
                if (Adintermethod.myCallback != null) {
                    Adintermethod.myCallback.OnCall();
                    Adintermethod.myCallback = null;
                }
                Log.e("TAG", "onAdFailedToLoad: ===>" + loadAdError.getMessage());
            }
        };
        AppOpenAd.load(activity2, convertedObject.getAppopenadId(), getAdRequest(), 1, this.loadCallback);
//        Log.e("check541", appopeninterid);
    }

    com.facebook.ads.InterstitialAd interstitialAd;

    public void show_FB_Interstitial(Activity activity2, String fbinter2, MyCallback myCallback) {

        interstitialAd = new com.facebook.ads.InterstitialAd(activity2, convertedObject.getFbinter2());
        // Set a listener to get notified on changes or when the user interact with the ad.
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                //======code here===========
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                addialoghide(activity2);
                Log.e("check5214", "Interstitial ad failed to load: " + adError.getErrorMessage());

            }

            @Override
            public void onAdLoaded(Ad ad) {
                addialoghide(activity2);
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
    }

    public void admob_native_load(Activity activity, String admobNativeid) {
        MobileAds.initialize(activity);
        AdLoader adLoader = new AdLoader.Builder(activity, admobNativeid)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {

                        mNativeAd = nativeAd;
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public void admob_nativebanner_load(Activity activity, String admobNativeid) {

        MobileAds.initialize(activity);
        AdLoader adLoader = new AdLoader.Builder(activity, admobNativeid)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        mNativebannerAd = nativeAd;
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public void checkstaticNativeAdsMode(Activity activity, TemplateView admobmediumnative, NativeAdLayout native_ad_container, CardView q_native) {
        // TODO: 11-09-2020  Native Ads
        if (convertedObject.getCheckAdNative().equals(Constant.admob)) {
            admob_native(activity, admobmediumnative);
        } else if (convertedObject.getCheckAdNative().equals(Constant.fb)) {
            Fb_loadNativeAd(activity, native_ad_container);
        } else if (convertedObject.getCheckAdNative().equals(Constant.qureka)) {
            Q_Native(activity, q_native);
        }

    }

    public void admob_native(Activity activity, TemplateView templateView) {
        try {
            MobileAds.initialize(activity);
            AdLoader adLoader = new AdLoader.Builder(activity, convertedObject.getAdmobNativeid())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            templateView.setVisibility(View.VISIBLE);
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
//                            TemplateView template = findViewById(R.id.admobmediumnative);
                            templateView.setStyles(styles);
                            templateView.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
        }
    }

    // TODO: 08-Oct-20  Fb Native
    public LinearLayout adView;

    public com.facebook.ads.NativeAd nativeAd1;

    // TODO: 11-09-2020  Fb Native Ads
    public void Fb_loadNativeAd(Activity activity, NativeAdLayout native_ad_container1) {
        nativeAd1 = new com.facebook.ads.NativeAd(activity, convertedObject.getFbNative1());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                if (nativeAd1 == null || nativeAd1 != ad) {
                    return;
                }
                // Inflate Native Ad into Container
                inflateAd1(activity, nativeAd1, native_ad_container1);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        nativeAd1.loadAd(
                nativeAd1.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }

    public void inflateAd1(Activity activity, com.facebook.ads.NativeAd nativeAd, NativeAdLayout native_ad_container1) {
        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        LayoutInflater inflater = LayoutInflater.from(activity);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView = (LinearLayout) inflater.inflate(R.layout.fb_native_ad_unit, native_ad_container1, false);
        native_ad_container1.addView(adView);

        // Add the AdChoices icon
        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container1);
        AdOptionsView adChoicesView = new AdOptionsView(activity, nativeAd, native_ad_container1);
        adChoicesContainer.addView(adChoicesView, 0);

        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        nativeAd.registerViewForInteraction(
                adView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
    }

    public void Q_Native(Activity activity, CardView qurekanativeid) {
        qurekanativeid.setVisibility(View.VISIBLE);
        qurekanativeid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Blue_LoadCustom.myCustom(activity, convertedObject.getQurekaUrl());
            }
        });

    }

    public void checkstaticNativeBannerAdsMode(Activity activity, TemplateView admobsmallnative, NativeAdLayout native_banner_ad_container, CardView q_native_banner) {
        // TODO: 11-09-2020  Native Ads
        if (convertedObject.getCheckAdNativeBanner().equals(Constant.admob)) {
            admob_native_banner(activity, admobsmallnative);
        } else if (convertedObject.getCheckAdNativeBanner().equals(Constant.fb)) {
            loadnative_bannerad(activity, native_banner_ad_container);
        } else if (convertedObject.getCheckAdNativeBanner().equals(Constant.qureka)) {
            Q_Native_banner(activity, q_native_banner);
        }

    }

    public void admob_native_banner(Activity activity, TemplateView admobsmallnative) {
        try {

            MobileAds.initialize(activity);
            AdLoader adLoader = new AdLoader.Builder(activity, convertedObject.getAdmobNativeid())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            admobsmallnative.setVisibility(View.VISIBLE);
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            admobsmallnative.setStyles(styles);
                            admobsmallnative.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }

    // TODO: 08-Oct-20  FB Banner Ads
    public NativeBannerAd nativeBannerAd;

    public void loadnative_bannerad(Activity activity, NativeAdLayout native_banner_ad_container) {
        nativeBannerAd = new NativeBannerAd(activity, convertedObject.getFbNativeBanner1());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                // Inflate Native Banner Ad into Container
                inflateAd(activity, nativeBannerAd, native_banner_ad_container);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

        };
        // load the ad
        nativeBannerAd.loadAd(nativeBannerAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
    }

    public void inflateAd(Activity activity, NativeBannerAd nativeBannerAd, NativeAdLayout native_banner_ad_container) {
        nativeBannerAd.unregisterView();
        LayoutInflater inflater = LayoutInflater.from(activity);
        adView = (LinearLayout) inflater.inflate(R.layout.fb_native_banner_ad_layout, native_banner_ad_container, false);
        native_banner_ad_container.addView(adView);

        RelativeLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeBannerAd, native_banner_ad_container);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        MediaView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews);
    }

    public void Q_Native_banner(Activity activity, CardView qurekaid) {

//        q_native_banner = findViewById(R.id.q_native_banner);
        qurekaid.setVisibility(View.VISIBLE);
        qurekaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Blue_LoadCustom.myCustom(activity, convertedObject.getQurekaUrl());
            }
        });
    }


    public void checkNativeAdsMode(Activity activity, TemplateView admobmediumnative, NativeAdLayout native_ad_container, CardView q_native) {
        // TODO: 11-09-2020  Native Ads
        if (convertedObject.getCheckAdNative().equals(Constant.admob)) {
            admob_native_show(activity, admobmediumnative);
        } else if (convertedObject.getCheckAdNative().equals(Constant.fb)) {
            Fb_loadNativeAd(activity, native_ad_container);
        } else if (convertedObject.getCheckAdNative().equals(Constant.qureka)) {
            Q_Native(activity, q_native);
        }

    }

    public void admob_native_show(Activity activity, TemplateView templateView) {

        if (convertedObject.getCheckAdNative().equalsIgnoreCase("admob")) {
            if (mNativeAd != null) {
                templateView.setVisibility(View.VISIBLE);
                NativeTemplateStyle styles = new
                        NativeTemplateStyle.Builder().build();
                templateView.setStyles(styles);
                templateView.setNativeAd(mNativeAd);
            } else {
                admob_native_load(activity, convertedObject.getAdmobNativeid());
            }
        }

    }

    public void checkNativeBannerAdsMode(Activity activity, TemplateView admobsmallnative, NativeAdLayout native_banner_ad_container, CardView q_native_banner) {
        // TODO: 11-09-2020  Native Ads
        if (convertedObject.getCheckAdNativeBanner().equals(Constant.admob)) {
            admob_nativebanner_show(activity, admobsmallnative);
        } else if (convertedObject.getCheckAdNativeBanner().equals(Constant.fb)) {
            loadnative_bannerad(activity, native_banner_ad_container);
        } else if (convertedObject.getCheckAdNativeBanner().equals(Constant.qureka)) {
            Q_Native_banner(activity, q_native_banner);
        }

    }

    public void admob_nativebanner_show(Activity activity, TemplateView admobsmallnative) {
        if (convertedObject.getCheckAdNativeBanner().equalsIgnoreCase("admob")) {
            if (mNativebannerAd != null) {
                admobsmallnative.setVisibility(View.VISIBLE);
                NativeTemplateStyle styles = new
                        NativeTemplateStyle.Builder().build();
                admobsmallnative.setStyles(styles);
                admobsmallnative.setNativeAd(mNativebannerAd);
            } else {
                admob_nativebanner_load(activity, convertedObject.getAdmobNativeid());
            }
        }
    }

    public void adIntentCallback() {
        if (adIntent != null) {
            adIntent.onintentscreen();
            adIntent = null;
        }
    }

    public void oncloseadCllback() {
        if (adIntent1 != null) {
            adIntent1.onclosead();
            adIntent1 = null;
        }
    }

    public void clearprefrence(Activity activity) {
        Utils.saveStringtoPrefrence(activity, Constant.AdResponse, null);
        nativeBannerAd = null;
        nativeAd1 = null;
        mNativeAd = null;
        mNativebannerAd = null;
        SharedPreferencesManager.getInstance(activity).backpressstoreClicks(1);
        SharedPreferencesManager.getInstance(activity).storeClicks(1);
    }

}
