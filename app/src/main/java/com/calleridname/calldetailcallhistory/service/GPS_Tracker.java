package com.calleridname.calldetailcallhistory.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.core.content.ContextCompat;

public class GPS_Tracker extends Service implements LocationListener {
    private static final long MIN_TIME_BW_UPDATES = 60000;
    private boolean canGetLocation = false;
    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private double latitude;
    private Location location;
    protected LocationManager locationManager;
    private double longitude;
    private final Context mContext;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onLocationChanged(Location location2) {
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void showSettingsAlert() {
    }

    public void stopUsingGPS() {
    }

    public GPS_Tracker(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {
        try {
            LocationManager locationManager2 = (LocationManager) this.mContext.getSystemService("calldetailcallhistory");
            this.locationManager = locationManager2;
            this.isGPSEnabled = locationManager2.isProviderEnabled("gps");
            boolean isProviderEnabled = this.locationManager.isProviderEnabled("network");
            this.isNetworkEnabled = isProviderEnabled;
            if (!this.isGPSEnabled && !isProviderEnabled) {
                return this.location;
            }
            this.canGetLocation = true;
            if (isProviderEnabled) {
                if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0 && ContextCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                    return null;
                }
                this.locationManager.requestLocationUpdates("network", 0, 0.0f, this);
                Log.d("Network", "Network");
                LocationManager locationManager3 = this.locationManager;
                if (locationManager3 != null) {
                    Location lastKnownLocation = locationManager3.getLastKnownLocation("network");
                    this.location = lastKnownLocation;
                    if (lastKnownLocation != null) {
                        this.latitude = lastKnownLocation.getLatitude();
                        this.longitude = this.location.getLongitude();
                    }
                }
            }
            if (this.isGPSEnabled && this.location == null) {
                this.locationManager.requestLocationUpdates("gps", MIN_TIME_BW_UPDATES, 10.0f, this);
                Log.d("GPS Enabled", "GPS Enabled");
                LocationManager locationManager4 = this.locationManager;
                if (locationManager4 != null) {
                    Location lastKnownLocation2 = locationManager4.getLastKnownLocation("gps");
                    this.location = lastKnownLocation2;
                    if (lastKnownLocation2 != null) {
                        this.latitude = lastKnownLocation2.getLatitude();
                        this.longitude = this.location.getLongitude();
                    }
                }
            }
            return this.location;
        } catch (Exception e) {
            e.printStackTrace();
            return this.location;
        }
    }

    public double getLatitude() {
        Location location2 = this.location;
        if (location2 != null) {
            this.latitude = location2.getLatitude();
        }
        return this.latitude;
    }

    public double getLongitude() {
        Location location2 = this.location;
        if (location2 != null) {
            this.longitude = location2.getLongitude();
        }
        return this.longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public boolean isgpsenabled() {
        return this.isGPSEnabled;
    }
}
