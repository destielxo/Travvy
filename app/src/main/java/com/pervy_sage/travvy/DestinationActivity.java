package com.pervy_sage.travvy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pervy_sage.travvy.APIs.NearbySearchApi;
import com.pervy_sage.travvy.Adapters.PlaceAdapter;
import com.pervy_sage.travvy.interfaces.OnViewClickListener;
import com.pervy_sage.travvy.models.NearbySearchList;
import com.pervy_sage.travvy.models.Results;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationActivity extends AppCompatActivity {
    RecyclerView rvPOT,rvHotels;
    PlaceAdapter placeAdapterPOT,placeAdapterHotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        rvPOT=(RecyclerView)findViewById(R.id.rvPointsOfInterest);
        rvHotels=(RecyclerView)findViewById(R.id.rvHotels);
        rvPOT.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false));
        rvHotels.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false));
        placeAdapterHotel= new PlaceAdapter(
                this,
                new ArrayList<Results>(),
                new OnViewClickListener() {
                    @Override
                    public void onViewClick(String string) {
                        Intent i = new Intent(DestinationActivity.this,
                                ViewPlaceActivity.class);
                        i.putExtra("placeId",string);
                        startActivity(i);
                    }
                },
                "horizontal"
        );
        placeAdapterPOT= new PlaceAdapter(
                this,
                new ArrayList<Results>(),
                new OnViewClickListener() {
                    @Override
                    public void onViewClick(String string) {
                        Intent i = new Intent(DestinationActivity.this,
                                ViewPlaceActivity.class);
                        i.putExtra("placeId",string);
                        startActivity(i);
                    }
                },
                "horizontal"
        );
        rvHotels.setAdapter(placeAdapterHotel);
        rvPOT.setAdapter(placeAdapterPOT);

        String location = getIntent().getStringExtra("DestPlace");
        NearbySearchApi nearbySearchApi = RetroFitPlaces
                                            .getRetrofitAPIs()
                                            .getNearbySearchApi();
        nearbySearchApi.getNearBySearchReslts(
                PlaceTypes.POT,
                location,
                2500).enqueue(new Callback<NearbySearchList>() {
            @Override
            public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                placeAdapterPOT.updatePlaceList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<NearbySearchList> call, Throwable t) {

            }
        });

        nearbySearchApi.getNearBySearchResltsbytype(
                PlaceTypes.hotels,
                location,
                2500).enqueue(new Callback<NearbySearchList>() {
            @Override
            public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                placeAdapterHotel.updatePlaceList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<NearbySearchList> call, Throwable t) {

            }
        });

    }
}
