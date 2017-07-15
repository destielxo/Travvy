package com.pervy_sage.travvy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by pervy_sage on 9/7/17.
 */

public class MyLocationListener implements LocationListener {

    public static final String TAG = "Locate";
    private double latitude, longitude;
    private LocationManager locationManager;
    private Context context;
    private Location location;
    public static final String Connection_Failed="Failed";

    public MyLocationListener(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private String CheckRequirements() {
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            Log.d(TAG, "CheckRequirements: Network Success");
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Log.d(TAG, "CheckRequirements: GPS Success");
                ConnectivityManager connectivityManager = (ConnectivityManager)context.
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                        .getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                                .getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    return LocationManager.NETWORK_PROVIDER;
                }
                else
                    return Connection_Failed;
            }
            return LocationManager.NETWORK_PROVIDER;
        } else {
            Log.d(TAG, "CheckRequirements: Failed");
            return Connection_Failed;
        }
    }

    public String  fetchLocationParms() {
        String provider = CheckRequirements();
        if (provider!=Connection_Failed) {
            if (ActivityCompat.checkSelfPermission(context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }
            locationManager.requestLocationUpdates
                    (provider,
                    0,
                    0,
                    this);
            this.location=locationManager.getLastKnownLocation(provider);
            latitude=this.location.getLatitude();
            longitude=this.location.getLongitude();
            return latitude+","+longitude;
        }
        return provider;
    }
}
