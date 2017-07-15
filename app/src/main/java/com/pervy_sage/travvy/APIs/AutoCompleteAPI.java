package com.pervy_sage.travvy.APIs;

import com.pervy_sage.travvy.models.PlaceAutoComplete;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pervy_sage on 11/7/17.
 */

public interface AutoCompleteAPI {
    @GET("/maps/api/place/autocomplete/json?key=AIzaSyCAaX8xXI2RzBIzU9XNOVgwNWyJWgUHNGg")
    Call<PlaceAutoComplete>getAUtoCompleteResults(
      @Query("input") String input
    );

}
