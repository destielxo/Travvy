package com.pervy_sage.travvy.models;

import java.util.ArrayList;

/**
 * Created by pervy_sage on 11/7/17.
 */

public class PlaceAutoComplete {
    ArrayList<Prediction> predictions;

    public PlaceAutoComplete(ArrayList<Prediction> predictions) {
        this.predictions = predictions;
    }

    public ArrayList<Prediction> getPredictions() {
        return predictions;
    }
}
