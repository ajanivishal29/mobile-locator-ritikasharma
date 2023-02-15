package com.calleridname.calldetailcallhistory.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.DataItem;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Adintermethod;
import com.calleridname.calldetailcallhistory.Main_Ads.admob_ads.Utils;
import com.calleridname.calldetailcallhistory.R;

public class pgrs_Web_pp extends AppCompatActivity {

    /* renamed from: p */
    public WebView f6446p;

    public DataItem convertedObject;
    public Activity context;

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.Startup.FFskin_Web_pp$a */
    public class C1703a extends WebChromeClient {
        public C1703a() {
        }

        public void onProgressChanged(WebView webView, int i) {
            pgrs_Web_pp.this.setProgress(i * 100);
        }
    }

    /* renamed from: com.loctracker.findlocation.calldetailcallhistory.Startup.FFskin_Web_pp$b */
    public class C1704b implements View.OnClickListener {
        public C1704b() {
        }

        public void onClick(View view) {
            pgrs_Web_pp.this.onBackPressed();
        }
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
        requestWindowFeature(2);
        setContentView((int) R.layout.pgrs_activity_webv);
        WebView webView = (WebView) findViewById(R.id.webwiew);
        this.f6446p = webView;

        convertedObject = Utils.getResponse(this);
        context = pgrs_Web_pp.this;

        if (convertedObject != null) {
            Adintermethod.getInstance(this).ShowInter(this);
            this.f6446p.getSettings().setJavaScriptEnabled(true);
            this.f6446p.getSettings().setBuiltInZoomControls(true);
            this.f6446p.getSettings().setUseWideViewPort(true);
            this.f6446p.loadUrl(convertedObject.getPrivacyPolicy());
            this.f6446p.setWebChromeClient(new C1703a());
            this.f6446p.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        } else {
            this.f6446p.getSettings().setJavaScriptEnabled(true);
            this.f6446p.getSettings().setBuiltInZoomControls(true);
            this.f6446p.getSettings().setUseWideViewPort(true);
            this.f6446p.loadUrl("");
            this.f6446p.setWebChromeClient(new C1703a());
            this.f6446p.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
        findViewById(R.id.back).setOnClickListener(new C1704b());
    }
}
