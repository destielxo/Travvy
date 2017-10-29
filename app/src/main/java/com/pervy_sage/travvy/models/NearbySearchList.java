package com.pervy_sage.travvy.models;

import java.util.ArrayList;

/**
 * Created by pervy_sage on 7/7/17.
 */

public class NearbySearchList {

    ArrayList<Results> results;

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    public NearbySearchList(ArrayList<Results> results) {

        this.results = results;
    }
}
