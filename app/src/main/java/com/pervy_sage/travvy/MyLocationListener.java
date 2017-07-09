package com.pervy_sage.travvy;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by pervy_sage on 9/7/17.
 */

public class MyLocationListener implements LocationListener {
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private double latitude,longitude;

    @Override
    public void onLocationChanged(Location location) {
        this.latitude=location.getLatitude();
        this.longitude=location.getLongitude();
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
}
