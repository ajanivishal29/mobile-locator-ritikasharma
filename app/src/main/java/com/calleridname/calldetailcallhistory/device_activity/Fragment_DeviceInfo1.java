package com.calleridname.calldetailcallhistory.device_activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.calleridname.calldetailcallhistory.R;
import com.jaredrummler.android.device.DeviceName;
import java.util.Locale;

public class Fragment_DeviceInfo1 extends Fragment {
    private String TAG = "xzfvasdfasdf";
    private TextView txt_androidver;
    private TextView txt_apilevel;
    private TextView txt_backcamera;
    private TextView txt_brandname;
    private TextView txt_density;
    private TextView txt_devicename;
    private TextView txt_frontcamera;
    private TextView txt_imei;
    private TextView txt_modelname;
    private TextView txt_physicalsize;
    private TextView txt_productcode;
    private TextView txt_refreshrate;
    private TextView txt_resolution;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_deviceinfo1, viewGroup, false);
        initview(inflate);
        return inflate;
    }

    private void initview(View view) {
        this.txt_devicename = (TextView) view.findViewById(R.id.txt_devicename);
        this.txt_modelname = (TextView) view.findViewById(R.id.txt_modelname);
        this.txt_brandname = (TextView) view.findViewById(R.id.txt_brandname);
        this.txt_productcode = (TextView) view.findViewById(R.id.txt_productcode);
        this.txt_imei = (TextView) view.findViewById(R.id.txt_imei);
        this.txt_resolution = (TextView) view.findViewById(R.id.txt_resolution);
        this.txt_density = (TextView) view.findViewById(R.id.txt_density);
        this.txt_refreshrate = (TextView) view.findViewById(R.id.txt_refreshrate);
        this.txt_physicalsize = (TextView) view.findViewById(R.id.txt_physicalsize);
        this.txt_frontcamera = (TextView) view.findViewById(R.id.txt_frontcamera);
        this.txt_backcamera = (TextView) view.findViewById(R.id.txt_backcamera);
        this.txt_androidver = (TextView) view.findViewById(R.id.txt_androidver);
        this.txt_apilevel = (TextView) view.findViewById(R.id.txt_apilevel);
        getdata();
    }

    public static String getDeviceId(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getDeviceId() != null) {
            return telephonyManager.getDeviceId();
        }
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    private void getdata() {
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        String str = defaultDisplay.getWidth() + " * " + defaultDisplay.getHeight();
        float refreshRate = ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay().getRefreshRate();
        this.txt_devicename.setText(DeviceName.getDeviceName());
        this.txt_modelname.setText(Build.MODEL);
        this.txt_brandname.setText(Build.BRAND);
        this.txt_productcode.setText(Build.DEVICE);
        this.txt_imei.setText(getDeviceId(getContext()));
        this.txt_resolution.setText(str);
        this.txt_density.setText(Density());
        this.txt_refreshrate.setText(Float.toString(refreshRate) + "Hz");
        this.txt_physicalsize.setText(getDisplaySize(getActivity()) + "''");
        this.txt_backcamera.setText(Integer.toString((int) getBackCameraResolutionInMp()) + " MegaPixel");
        this.txt_frontcamera.setText(Integer.toString((int) getFrontCameraResolutionInMp()) + " MegaPixel");
        this.txt_androidver.setText(Build.VERSION.RELEASE);
        this.txt_apilevel.setText(String.valueOf(Build.VERSION.SDK_INT));
    }

    public String Density() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.densityDpi == 120) {
            return "120 dpi (Low)";
        }
        if (displayMetrics.densityDpi == 160) {
            return "160 dpi (Medium)";
        }
        if (displayMetrics.densityDpi == 240) {
            return "240 dpi (High)";
        }
        if (displayMetrics.densityDpi == 320) {
            return "320 dpi (X High)";
        }
        if (displayMetrics.densityDpi == 480) {
            return "480 dpi (XX High)";
        }
        if (displayMetrics.densityDpi == 640) {
            return "640 dpi (XXX High)";
        }
        if (displayMetrics.densityDpi == 213) {
            return "TV";
        }
        return displayMetrics.densityDpi == 400 ? "400 dpi" : "Unknown";
    }

    static String getDisplaySize(Activity activity) {
        try {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            defaultDisplay.getMetrics(new DisplayMetrics());
            Point point = new Point();
            Display.class.getMethod("getRealSize", new Class[]{Point.class}).invoke(defaultDisplay, new Object[]{point});
            int i = point.x;
            int i2 = point.y;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            double pow = Math.pow((double) (((float) i) / displayMetrics.xdpi), 2.0d);
            try {
                double pow2 = Math.pow((double) (((float) i2) / displayMetrics.ydpi), 2.0d);
                return String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Math.sqrt(pow2 + pow))});
            } catch (Exception e) {
                e.printStackTrace();
                return String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Math.sqrt(pow + 0.0d))});
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Math.sqrt(0.0d))});
        }
    }

    public float getBackCameraResolutionInMp() {
        int numberOfCameras = Camera.getNumberOfCameras();
        float f = -1.0f;
        long j = -1;
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 0) {
                Camera open = Camera.open(i);
                Camera.Parameters parameters = open.getParameters();
                for (int i2 = 0; i2 < parameters.getSupportedPictureSizes().size(); i2++) {
                    long j2 = (long) (parameters.getSupportedPictureSizes().get(i2).width * parameters.getSupportedPictureSizes().get(i2).height);
                    if (j2 > j) {
                        f = ((float) j2) / 1024000.0f;
                        j = j2;
                    }
                }
                open.release();
            }
        }
        return f;
    }

    public float getFrontCameraResolutionInMp() {
        int numberOfCameras = Camera.getNumberOfCameras();
        float f = -1.0f;
        long j = -1;
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 1) {
                Camera open = Camera.open(i);
                Camera.Parameters parameters = open.getParameters();
                for (int i2 = 0; i2 < parameters.getSupportedPictureSizes().size(); i2++) {
                    long j2 = (long) (parameters.getSupportedPictureSizes().get(i2).width * parameters.getSupportedPictureSizes().get(i2).height);
                    if (j2 > j) {
                        f = ((float) j2) / 1024000.0f;
                        j = j2;
                    }
                }
                open.release();
            }
        }
        return f;
    }
}
