package com.pervy_sage.travvy.APIs;

import com.pervy_sage.travvy.models.NearbySearchList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pervy_sage on 7/7/17.
 */

public interface NearbySearchApi {
    @GET("/maps/api/place/nearbysearch/json?key=AIzaSyAKss8SNS-zZXLZYTTtk6e9-HhzPiBDP-c")
    Call<NearbySearchList> getNearBySearchResltsbytype(
            @Query("type") String type,
            @Query("location") String location,
            @Query("radius") int radius
    );
    @GET("/maps/api/place/nearbysearch/json?key=AIzaSyAKss8SNS-zZXLZYTTtk6e9-HhzPiBDP-c")
    Call<NearbySearchList> getNearBySearchReslts(
            @Query("location") String location,
            @Query("radius") int radius
    );
}
