package com.pervy_sage.travvy.APIs;

import com.pervy_sage.travvy.models.PlaceAutoComplete;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by pervy_sage on 11/7/17.
 */

public interface AutoCompleteAPI {
    @GET("/maps/api/place/autocomplete/json?key=AIzaSyAeQJnPACivs2Y2OkO0EQZoFz-BgHixsIo")
    Call<PlaceAutoComplete>getAUtoCompleteResults(
      @Query("input") String input
    );

}
