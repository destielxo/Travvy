package com.pervy_sage.travvy.APIs;

import com.pervy_sage.travvy.models.PlaceDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pervy_sage on 10/7/17.
 */

public interface PlaceDetailApi {
    @GET("/maps/api/place/details/json?key=AIzaSyAeQJnPACivs2Y2OkO0EQZoFz-BgHixsIo")
    Call<PlaceDetails>getPlaceDetails(
      @Query("placeid") String placeId
    );
}
