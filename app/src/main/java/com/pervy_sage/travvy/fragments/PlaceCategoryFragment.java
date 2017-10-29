package com.pervy_sage.travvy.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.pervy_sage.travvy.APIs.NearbySearchApi;
import com.pervy_sage.travvy.Adapters.PlaceAdapter;
import com.pervy_sage.travvy.R;
import com.pervy_sage.travvy.RetroFitPlaces;
import com.pervy_sage.travvy.ViewPlaceActivity;
import com.pervy_sage.travvy.interfaces.OnCatalogueCreated;
import com.pervy_sage.travvy.interfaces.OnViewClickListener;
import com.pervy_sage.travvy.models.NearbySearchList;
import com.pervy_sage.travvy.models.Results;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaceCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG="Category";
    private static final String ARG_LOCATION = "location";
    private static final String ARG_PLACETYPE="placeType";
    // TODO: Rename and change types of parameters
    private String locationCoordinates;
    private String placeType;
    private RecyclerView rvPlaceCategory;
    private PlaceAdapter placeAdapter;
    private ProgressBar progressBar;

    public PlaceCategoryFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlaceCategoryFragment newInstance(String locationCoordinates,
                                                    String placeType) {
        PlaceCategoryFragment fragment = new PlaceCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LOCATION, locationCoordinates);
        args.putString(ARG_PLACETYPE,placeType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            locationCoordinates = getArguments().getString(ARG_LOCATION);
            placeType=getArguments().getString(ARG_PLACETYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView= inflater.inflate(R.layout.fragment_place_category, container, false);
        rvPlaceCategory=(RecyclerView)itemView.findViewById(R.id.rvPlaceCategory);
        rvPlaceCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar=(ProgressBar)itemView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        placeAdapter=new PlaceAdapter(getContext(),
                new ArrayList<Results>(),
                new OnViewClickListener() {
            @Override
            public void onViewClick(String string) {
                Intent i = new Intent(getContext(),ViewPlaceActivity.class);
                i.putExtra("placeId",string);
                startActivity(i);
            }
        },
        "vertical");
        rvPlaceCategory.setAdapter(placeAdapter);
        prepareCategoryCatalogue();
        return itemView;
    }

    private void prepareCategoryCatalogue(){
        NearbySearchApi nearbySearchApi =
                RetroFitPlaces.getRetrofitAPIs().getNearbySearchApi();
        nearbySearchApi.getNearBySearchResltsbytype(
                placeType,
                locationCoordinates,
                2500).
                enqueue(new Callback<NearbySearchList>() {
                    @Override
                    public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                        Log.d(TAG, "onResponse: "+response.body().getResults());
                        placeAdapter.updatePlaceList(response.body().getResults(),progressBar);
                    }

                    @Override
                    public void onFailure(Call<NearbySearchList> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                        // to implement snack bar here
                    }
                });
    }

}
