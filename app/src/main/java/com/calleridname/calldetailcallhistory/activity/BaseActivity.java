package com.calleridname.calldetailcallhistory.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public static ProgressDialog dialog;

    public abstract int getContentView();

    public abstract void initialization();

    public abstract void setViewListener();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getContentView());
        initialization();
        setViewListener();
    }

    public static void hideProgressDialog() {
        ProgressDialog progressDialog = dialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public static void ShowProgressDialog(Activity activity, String str) {
        ProgressDialog progressDialog;
        if (activity != null && !activity.isFinishing() && (progressDialog = dialog) != null && progressDialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        try {
            ProgressDialog progressDialog2 = new ProgressDialog(activity);
            dialog = progressDialog2;
            progressDialog2.setMessage(str);
            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
    }
}
