package com.calleridname.calldetailcallhistory.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.WeatherapiResponse;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.retrofit.APIClient;
import com.calleridname.calldetailcallhistory.Main_Ads.retrofit.RetrofitInterface;
import com.facebook.ads.NativeAdLayout;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.TemplateView;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pgrs_LiveWeatherActivity extends AppCompatActivity {

    /* renamed from: p */
    public Calendar f6363p;

    /* renamed from: q */
    public SimpleDateFormat f6364q;

    /* renamed from: r */
    public SimpleDateFormat f6365r;

    /* renamed from: s */
    public String f6366s;

    /* renamed from: t */
    public String f6367t;

    /* renamed from: u */
    public C6216h f6368u;

    /* renamed from: v */
    public ImageView f6369v;

    public DataItem convertedObject;
    public Activity context;

    TemplateView admobmediumnative;
    NativeAdLayout native_ad_container;
    CardView q_native;

    TemplateView admobsmallnative;
    NativeAdLayout native_banner_ad_container;
    CardView q_native_banner;

    public static RetrofitInterface apiInterface;

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.LiveWeatherActivity$a */
    public class C1659a implements View.OnClickListener {
        public C1659a() {
        }

        public void onClick(View view) {
            pgrs_LiveWeatherActivity.this.onBackPressed();
        }
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.LiveWeatherActivity$b */
    public class C1660b implements C6126a {
        public C1660b() {
        }

        /* renamed from: a */
        public void mo7153a(Throwable th) {
            Log.v("LiveWeatherActivity", th.getMessage());
        }
    }

    /* renamed from: I */
    public String mo7150I(Instant instant) {
        int i = Build.VERSION.SDK_INT;
        if (i < 26) {
            return "";
        }
        return (i >= 26 ? DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH).withZone(ZoneId.of("Asia/Kathmandu")) : null).format(instant);
    }

    public void onBackPressed() {
        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInterBack(this);
            finish();
        } else {
            finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = getLayoutInflater().inflate(R.layout.pgrs_activity_live_weather, (ViewGroup) null, false);

        apiInterface = APIClient.getweatherclient().create(RetrofitInterface.class);

        admobsmallnative = inflate.findViewById(R.id.admobsmallnative);
        native_banner_ad_container = inflate.findViewById(R.id.native_banner_ad_container);
        q_native_banner = inflate.findViewById(R.id.q_native_banner);

        admobmediumnative = inflate.findViewById(R.id.admobmediumnative);
        native_ad_container = inflate.findViewById(R.id.native_ad_container);
        q_native = inflate.findViewById(R.id.q_native);

        context = pgrs_LiveWeatherActivity.this;
        convertedObject = Utils.getResponse(this);

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            Adintermethod.getInstance(this).checkNativeBannerAdsMode(this, admobsmallnative, native_banner_ad_container, q_native_banner);
            Adintermethod.getInstance(this).checkNativeAdsMode(this, admobmediumnative, native_ad_container, q_native);
        }

        WeaterResponce();

        int i = R.id.back;
        ImageView imageView = (ImageView) inflate.findViewById(R.id.back);
        if (imageView != null) {
            i = R.id.constraintLayout;
            RelativeLayout linearLayout = (RelativeLayout) inflate.findViewById(R.id.constraintLayout);
            if (linearLayout != null) {
                ImageView imageView2 = (ImageView) inflate.findViewById(R.id.icon);
                if (imageView2 != null) {
                    i = R.id.imageView2;
                    ImageView imageView3 = (ImageView) inflate.findViewById(R.id.imageView2);
                    if (imageView3 != null) {
                        i = R.id.iv_address;
                        TextView textView = (TextView) inflate.findViewById(R.id.iv_address);
                        if (textView != null) {
                            i = R.id.iv_cc;
                            TextView textView2 = (TextView) inflate.findViewById(R.id.iv_cc);
                            if (textView2 != null) {
                                i = R.id.iv_date;
                                TextView textView3 = (TextView) inflate.findViewById(R.id.iv_date);
                                if (textView3 != null) {
                                    i = R.id.iv_feels_like;
                                    TextView textView4 = (TextView) inflate.findViewById(R.id.iv_feels_like);
                                    if (textView4 != null) {
                                        i = R.id.iv_humidity;
                                        TextView textView5 = (TextView) inflate.findViewById(R.id.iv_humidity);
                                        if (textView5 != null) {
                                            i = R.id.iv_pressure;
                                            TextView textView6 = (TextView) inflate.findViewById(R.id.iv_pressure);
                                            if (textView6 != null) {
                                                i = R.id.iv_sunRise;
                                                TextView textView7 = (TextView) inflate.findViewById(R.id.iv_sunRise);
                                                if (textView7 != null) {
                                                    i = R.id.iv_sunSet;
                                                    TextView textView8 = (TextView) inflate.findViewById(R.id.iv_sunSet);
                                                    if (textView8 != null) {
                                                        i = R.id.iv_temp;
                                                        TextView textView9 = (TextView) inflate.findViewById(R.id.iv_temp);
                                                        if (textView9 != null) {
                                                            i = R.id.iv_time;
                                                            TextView textView10 = (TextView) inflate.findViewById(R.id.iv_time);
                                                            if (textView10 != null) {
                                                                i = R.id.iv_vissiblity;
                                                                TextView textView11 = (TextView) inflate.findViewById(R.id.iv_vissiblity);
                                                                if (textView11 != null) {
                                                                    i = R.id.iv_wind;
                                                                    TextView textView12 = (TextView) inflate.findViewById(R.id.iv_wind);
                                                                    if (textView12 != null) {
                                                                        i = R.id.linearLayout;
                                                                        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.linearLayout);
                                                                        if (linearLayout2 != null) {
                                                                            i = R.id.linearLayout2;
                                                                            LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.linearLayout2);
                                                                            if (linearLayout3 != null) {
                                                                                i = R.id.linearLayout3;
                                                                                LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.linearLayout3);
                                                                                if (linearLayout4 != null) {
                                                                                    i = R.id.linearLayout4;
                                                                                    LinearLayout linearLayout5 = (LinearLayout) inflate.findViewById(R.id.linearLayout4);
                                                                                    if (linearLayout5 != null) {
                                                                                        i = R.id.linearLayout5;
                                                                                        LinearLayout linearLayout6 = (LinearLayout) inflate.findViewById(R.id.linearLayout5);
                                                                                        if (linearLayout6 != null) {
                                                                                            i = R.id.linearLayout6;
                                                                                            LinearLayout linearLayout7 = (LinearLayout) inflate.findViewById(R.id.linearLayout6);
                                                                                            if (linearLayout7 != null) {
                                                                                                i = R.id.liner;
                                                                                                LinearLayout linearLayout8 = (LinearLayout) inflate.findViewById(R.id.liner);
                                                                                                if (linearLayout8 != null) {
                                                                                                    i = R.id.mist;
                                                                                                    TextView textView13 = (TextView) inflate.findViewById(R.id.mist);
                                                                                                    if (textView13 != null) {
                                                                                                        RelativeLayout relativeLayout = (RelativeLayout) inflate;
//                                                                                                                C6216h hVar = r4;
                                                                                                        C6216h hVar = new C6216h(relativeLayout, imageView, linearLayout, imageView2, imageView3, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, textView13);
                                                                                                        this.f6368u = hVar;
                                                                                                        setContentView((View) relativeLayout);
                                                                                                        this.f6369v = (ImageView) findViewById(R.id.icon);
                                                                                                        this.f6368u.f27761b.setOnClickListener(new C1659a());
                                                                                                        return;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i)));
    }

    public void WeaterResponce() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("units", "imperial");
        params.put("q", "surat");
        params.put("lang", "en");
        params.put("appid", "11c8fa4244e165268199d4ec0245963c");
        Call<WeatherapiResponse> call1 = apiInterface.weatherdetails(params);

        call1.enqueue(new Callback<WeatherapiResponse>() {
            @Override
            public void onResponse(Call<WeatherapiResponse> call, Response<WeatherapiResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    WeatherapiResponse aVar2 = response.body();
                    try {
                        Date date = new Date(Long.valueOf(aVar2.getDt()).longValue());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
                        String format = simpleDateFormat.format(date);
                        simpleDateFormat2.format(date);
                        String format2 = String.format("%f", new Object[]{Double.valueOf((Double.valueOf(aVar2.getMain().getTempMax() - 32.0d).doubleValue() * 5.0d) / 9.0d)});
                        String valueOf = String.valueOf(new DecimalFormat("#.##").format(Double.valueOf((Double.valueOf(aVar2.getMain().getTempMin() - 32.0d).doubleValue() * 5.0d) / 9.0d)));
                        String b = aVar2.getWeather().get(0).getIcon();
                        StringBuilder sb = new StringBuilder();
                        String str = "";
                        sb.append("http://openweathermap.org/img/w/");
                        sb.append(b);
                        sb.append(".png");
                        String sb2 = sb.toString();
                        String str2 = " °C";
                        String str3 = " m";
                        if (Build.VERSION.SDK_INT >= 26) {
                            pgrs_LiveWeatherActivity.this.f6363p = Calendar.getInstance();
                            pgrs_LiveWeatherActivity.this.f6364q = new SimpleDateFormat("HH:mm:ss");
                            pgrs_LiveWeatherActivity.this.f6365r = new SimpleDateFormat("dd-MM-yyyy");
                            pgrs_LiveWeatherActivity liveWeatherActivity = pgrs_LiveWeatherActivity.this;
                            liveWeatherActivity.f6366s = liveWeatherActivity.f6364q.format(liveWeatherActivity.f6363p.getTime());
                            pgrs_LiveWeatherActivity liveWeatherActivity2 = pgrs_LiveWeatherActivity.this;
                            liveWeatherActivity2.f6367t = liveWeatherActivity2.f6365r.format(liveWeatherActivity2.f6363p.getTime());
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("onSuccess:+-+-+- ");
                            sb3.append(pgrs_LiveWeatherActivity.this.f6366s);
                            Log.e("LiveWeatherActivity", sb3.toString());
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("onSuccess:+-+-+- ");
                            sb4.append(pgrs_LiveWeatherActivity.this.f6367t);
                            Log.e("LiveWeatherActivity", sb4.toString());
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(aVar2.getMain().getHumidity());
                            sb5.append(" %");
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append(aVar2.getWind().getSpeed());
                            sb6.append(" miles/hour");
                            StringBuilder sb7 = new StringBuilder();
                            sb7.append(aVar2.getMain().getPressure());
                            sb7.append(" hPa");
                            StringBuilder sb8 = new StringBuilder();
                            sb8.append(aVar2.getClouds().getAll());
                            sb8.append(" %");
                            StringBuilder sb9 = new StringBuilder();
                            sb9.append(aVar2.getVisibility());
                            String str4 = str3;
                            sb9.append(str4);
                            StringBuilder sb10 = new StringBuilder();
                            sb10.append(valueOf);
                            String str5 = str2;
                            sb10.append(str5);
                            TextView textView = pgrs_LiveWeatherActivity.this.f6368u.f27763d;
                            StringBuilder sb11 = new StringBuilder();
                            String str6 = str;
                            sb11.append(str6);
                            sb11.append(aVar2.getName());
                            sb11.append(" , ");
                            sb11.append(aVar2.getSys().getCountry());
                            textView.setText(sb11.toString());
                            TextView textView2 = pgrs_LiveWeatherActivity.this.f6368u.f27765f;
                            textView2.setText(str6 + pgrs_LiveWeatherActivity.this.f6367t);
                            TextView textView3 = pgrs_LiveWeatherActivity.this.f6368u.f27772m;
                            textView3.setText(str6 + pgrs_LiveWeatherActivity.this.f6366s);
                            TextView textView4 = pgrs_LiveWeatherActivity.this.f6368u.f27771l;
                            textView4.setText(str6 + format2 + str5);
                            TextView textView5 = pgrs_LiveWeatherActivity.this.f6368u.f27775p;
                            textView5.setText(str6 + aVar2.getWeather().get(0).getDescription());
                            TextView textView6 = pgrs_LiveWeatherActivity.this.f6368u.f27769j;
                            textView6.setText(str6 + pgrs_LiveWeatherActivity.this.mo7150I(Instant.ofEpochSecond(Long.valueOf(aVar2.getSys().getSunrise()).longValue())));
                            TextView textView7 = pgrs_LiveWeatherActivity.this.f6368u.f27770k;
                            textView7.setText(str6 + pgrs_LiveWeatherActivity.this.mo7150I(Instant.ofEpochSecond(Long.valueOf(aVar2.getSys().getSunset()).longValue())));
                            TextView textView8 = pgrs_LiveWeatherActivity.this.f6368u.f27767h;
                            textView8.setText(str6 + aVar2.getMain().getHumidity() + "%");
                            TextView textView9 = pgrs_LiveWeatherActivity.this.f6368u.f27774o;
                            textView9.setText(str6 + aVar2.getWind().getSpeed() + "m/h");
                            TextView textView10 = pgrs_LiveWeatherActivity.this.f6368u.f27768i;
                            textView10.setText(str6 + aVar2.getMain().getPressure() + " hPa");
                            TextView textView11 = pgrs_LiveWeatherActivity.this.f6368u.f27764e;
                            textView11.setText(str6 + aVar2.getClouds().getAll() + " %");
                            TextView textView12 = pgrs_LiveWeatherActivity.this.f6368u.f27773n;
                            textView12.setText(str6 + aVar2.getVisibility() + str4);
                            TextView textView13 = pgrs_LiveWeatherActivity.this.f6368u.f27766g;
                            textView13.setText(str6 + valueOf + str5);
                        }
                    } catch (Exception unused) {
                    }

                }

            }

            @Override
            public void onFailure(Call<WeatherapiResponse> call, Throwable t) {
                call.cancel();

            }
        });
    }

    public interface C6126a {
    }

    public final class C6216h {

        /* renamed from: a */
        public final RelativeLayout f27760a;

        /* renamed from: b */
        public final ImageView f27761b;

        /* renamed from: d */
        public final TextView f27763d;

        /* renamed from: e */
        public final TextView f27764e;

        /* renamed from: f */
        public final TextView f27765f;

        /* renamed from: g */
        public final TextView f27766g;

        /* renamed from: h */
        public final TextView f27767h;

        /* renamed from: i */
        public final TextView f27768i;

        /* renamed from: j */
        public final TextView f27769j;

        /* renamed from: k */
        public final TextView f27770k;

        /* renamed from: l */
        public final TextView f27771l;

        /* renamed from: m */
        public final TextView f27772m;

        /* renamed from: n */
        public final TextView f27773n;

        /* renamed from: o */
        public final TextView f27774o;

        /* renamed from: p */
        public final TextView f27775p;

        public C6216h(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout linearLayout, ImageView imageView2, ImageView imageView3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, TextView textView13) {
            this.f27760a = relativeLayout;
            this.f27761b = imageView;
            this.f27763d = textView;
            this.f27764e = textView2;
            this.f27765f = textView3;
            this.f27766g = textView4;
            this.f27767h = textView5;
            this.f27768i = textView6;
            this.f27769j = textView7;
            this.f27770k = textView8;
            this.f27771l = textView9;
            this.f27772m = textView10;
            this.f27773n = textView11;
            this.f27774o = textView12;
            this.f27775p = textView13;
        }
    }

    public void onRestart() {
        super.onRestart();
    }
}
