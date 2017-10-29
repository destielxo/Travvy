package com.pervy_sage.travvy.models;

/**
 * Created by pervy_sage on 11/7/17.
 */

public class Prediction {
    String description;
    String place_id;

    public Prediction(String description, String place_id) {
        this.description = description;
        this.place_id = place_id;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace_id() {
        return place_id;
    }
}
