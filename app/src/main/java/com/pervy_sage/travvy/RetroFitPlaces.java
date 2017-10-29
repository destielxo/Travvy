package com.pervy_sage.travvy;

import com.pervy_sage.travvy.APIs.AutoCompleteAPI;
import com.pervy_sage.travvy.APIs.NearbySearchApi;
import com.pervy_sage.travvy.APIs.PlaceDetailApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pervy_sage on 9/7/17.
 */

public class RetroFitPlaces {
    private static RetroFitPlaces placesAPI= null;
    private Retrofit retrofitInstance;
    private NearbySearchApi nearbySearchApi = null;
    private PlaceDetailApi placeDetailApi = null;
    private AutoCompleteAPI autoCompleteAPI= null;

    public PlaceDetailApi getPlaceDetailApi() {
        return placeDetailApi;
    }

    public AutoCompleteAPI getAutoCompleteAPI() {
        return autoCompleteAPI;
    }

    private RetroFitPlaces(){
        retrofitInstance = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        nearbySearchApi=retrofitInstance.create(NearbySearchApi.class);
        placeDetailApi=retrofitInstance.create(PlaceDetailApi.class);
        autoCompleteAPI=retrofitInstance.create(AutoCompleteAPI.class);

    }

    public NearbySearchApi getNearbySearchApi() {
        return nearbySearchApi;
    }

    public static RetroFitPlaces getRetrofitAPIs(){
        if(placesAPI==null){
            placesAPI= new RetroFitPlaces();
        }
        return placesAPI;

    }
}
