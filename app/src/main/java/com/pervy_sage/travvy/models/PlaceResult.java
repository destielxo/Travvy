package com.pervy_sage.travvy.models;

import java.util.ArrayList;

/**
 * Created by pervy_sage on 10/7/17.
 */

public class PlaceResult {
    String formatted_address;
    Geometry geometry;
    String international_phone_number;
    String name;
    String place_id;
    double rating;
    ArrayList<Photo> photos;
    String website;
    ArrayList<String>types;
    ArrayList<Review> reviews;
    OpeningHours opening_hours;

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public PlaceResult(String formatted_address,
                       Geometry geometry,
                       String international_phone_number,
                       String name,
                       String place_id,
                       double rating,
                       ArrayList<Photo> photos,
                       String website,
                       ArrayList<String> types,
                       ArrayList<Review> reviews,
                       OpeningHours opening_hours) {
        this.formatted_address = formatted_address;
        this.geometry = geometry;
        this.international_phone_number = international_phone_number;
        this.name = name;
        this.place_id = place_id;
        this.rating = rating;
        this.photos = photos;
        this.website = website;
        this.types = types;
        this.reviews = reviews;

        this.opening_hours=opening_hours;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getInternational_phone_number() {
        return international_phone_number;
    }

    public String getName() {
        return name;
    }

    public String getPlace_id() {
        return place_id;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public String getWebsite() {
        return website;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
