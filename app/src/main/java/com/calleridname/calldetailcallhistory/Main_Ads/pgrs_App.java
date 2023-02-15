package com.calleridname.calldetailcallhistory.Main_Ads;


import static android.content.ContentValues.TAG;
import static com.calleridname.calldetailcallhistory.Main_Ads.pgrs_Splace_Activity.apiInterface;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.anchorfree.partner.api.ClientInfo;
import com.anchorfree.partner.api.auth.AuthMethod;
import com.anchorfree.partner.api.response.User;
import com.anchorfree.sdk.HydraTransportConfig;
import com.anchorfree.sdk.NotificationConfig;
import com.anchorfree.sdk.UnifiedSDK;
import com.anchorfree.sdk.UnifiedSDKConfig;
import com.anchorfree.vpnsdk.callbacks.CompletableCallback;
import com.anchorfree.vpnsdk.exceptions.VpnException;
import com.calleridname.calldetailcallhistory.BuildConfig;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.LocaladsResponce;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pgrs_App extends Application {
    private int success;
    public static ArrayList<DataItem> arrAdDataStart = new ArrayList<>();
    private StartAdListener startAdListener;
    public static pgrs_Touch_AppOpenManager valeAppOpenManager;

    public static Context context;
    public static SharedPreferences.Editor editor;
    public static SharedPreferences preferences;

    private static pgrs_App instance;

    UnifiedSDK unifiedSDK;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("vpn", "Sample VPN", 3);
            notificationChannel.setDescription("VPN notification");
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    public void initHydraSdk() {
        createNotificationChannel();
        ClientInfo build = ClientInfo.newBuilder().baseUrl(BuildConfig.BASE_HOST).carrierId("asp_test_prj").build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(HydraTransportConfig.create());
//        arrayList.add(OpenVpnTransportConfig.tcp());
//        arrayList.add(OpenVpnTransportConfig.udp());
        UnifiedSDK.update(arrayList, CompletableCallback.EMPTY);
        this.unifiedSDK = UnifiedSDK.getInstance(build, UnifiedSDKConfig.newBuilder().idfaEnabled(false).build());
        UnifiedSDK.update(NotificationConfig.newBuilder().title(getResources().getString(R.string.app_name)).channelId("vpn").build());
        UnifiedSDK.setLoggingLevel(2);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();
        SharedPreferences sharedPreferences = getSharedPreferences("LiveCricScoreData", 0);
        preferences = sharedPreferences;
        editor = sharedPreferences.edit();
        context = getApplicationContext();

        MobileAds.initialize((Context) this, (OnInitializationCompleteListener) new OnInitializationCompleteListener() {
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        valeAppOpenManager = new pgrs_Touch_AppOpenManager(this);

        if (AudienceNetworkAds.isInitialized(this)) {
            return;
        }
        AudienceNetworkAds.initialize(this);
    }

    public void loginToVpn() {
        Log.e("TAG", "loginToVpn: 1111");
        //initilizeShowDialog();
        UnifiedSDK.getInstance().getBackend().login(AuthMethod.anonymous(), new com.anchorfree.vpnsdk.callbacks.Callback<User>() {
            public void success(User user) {
                // Toast.makeText(VideoPlayer_App.this, "Sucess", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "loginToVpn: Sucess");
//                dialog.dismiss();
                // connectToVpn();
//                updateUI();
            }

            public void failure(VpnException vpnException) {
                Log.e(TAG, "loginToVpn: 1111");
                //  Toast.makeText(VideoPlayer_App.this, "fail", Toast.LENGTH_SHORT).show();

//                updateUI();
//                handleError(vpnException);
            }
        });
    }

    private void initApplication() {
        instance = this;
    }

    public static pgrs_App getInstance() {
        return instance;
    }

   @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public void FatchStartApps() {
        arrAdDataStart.clear();
        Call<LocaladsResponce> call1 = apiInterface.localads("com.findlocation.calldetailcallhistory");

        call1.enqueue(new Callback<LocaladsResponce>() {
            @Override
            public void onResponse(Call<LocaladsResponce> call, Response<LocaladsResponce> response) {
//                Toast.makeText(App.this, response.message(), Toast.LENGTH_LONG).show();
//                Toast.makeText(App.this, "Success", Toast.LENGTH_SHORT).show();

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getData() != null && response.body().getData().size() > 0) {
                        arrAdDataStart.addAll(response.body().getData());
                        if (startAdListener != null)
                            startAdListener.onStartAdLoaded();
                    }
                }

            }

            @Override
            public void onFailure(Call<LocaladsResponce> call, Throwable t) {
                call.cancel();

//                Toast.makeText(App.this, "Error", Toast.LENGTH_SHORT).show();

                if (startAdListener != null)
                    startAdListener.onStartAdError();
            }
        });
    }

    // Listener defined earlier
    public interface StartAdListener {

        public void onStartAdError();

        public void onStartAdLoaded();
    }

    public void setStartAdListener(StartAdListener listener) {
        this.startAdListener = listener;
        FatchStartApps();

    }


}
