package com.pervy_sage.travvy.models;

/**
 * Created by pervy_sage on 7/7/17.
 */

public class Location {

    double lat;
    double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Location(double lat, double lng) {

        this.lat = lat;
        this.lng = lng;
    }
}
