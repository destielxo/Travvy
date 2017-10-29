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
import com.pervy_sage.travvy.PlaceTypes;
import com.pervy_sage.travvy.models.Results;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogueFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_LOCATION="ARG_LOCATION";

    public static final String TAG="catalogue";

    // TODO: Rename and change types of parameters

    private RecyclerView rvPlacesView;
    private PlaceAdapter placeAdapter;
    private ProgressBar progressBar;
    private String location;




    public CatalogueFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CatalogueFragment newInstance(String locationCoordinates) {
        Log.d(TAG, "newInstance: ");
        CatalogueFragment fragment = new CatalogueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LOCATION,locationCoordinates);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if(getArguments()!=null){
            location=getArguments().getString(ARG_LOCATION);
            Log.d(TAG, "onCreate: location coordinates"+location);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        View itemView= inflater.inflate(R.layout.fragment_catalogue, container, false);
        rvPlacesView=(RecyclerView)itemView.findViewById(R.id.rvPlacesView);
        rvPlacesView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar= (ProgressBar)itemView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        placeAdapter=new PlaceAdapter(
                getContext(),
                new ArrayList<Results>(),
                new OnViewClickListener() {
                    @Override
                    public void onViewClick(String s) {
                        Intent i = new Intent(getContext(),ViewPlaceActivity.class);
                        i.putExtra("placeId",s);
                        startActivity(i);
                    }
                },
        "vertical");
        rvPlacesView.setAdapter(placeAdapter);
        prepareCatalogue(location);
        return itemView;
    }



    public void updatePlacesList(ArrayList<Results> results){
        Log.d(TAG, "updatePlacesList: "+ results.size());
        placeAdapter.updatePlaceList(results);
    }

    private void prepareCatalogue(String locationCoordinates){
        Log.d(TAG, "prepareCatalogue: ");
        NearbySearchApi nearbySearchApi=
                RetroFitPlaces.getRetrofitAPIs().getNearbySearchApi();
        final ArrayList<Results> placeCatalogue = new ArrayList<>();
        nearByRestauarants(nearbySearchApi,
                locationCoordinates,
                placeCatalogue);

    }

    private void nearByRestauarants(final NearbySearchApi nearbySearchApi,
                                    final String locationCoordinates,
                                    final ArrayList<Results>placeCatalogue){
        nearbySearchApi.getNearBySearchResltsbytype(
                PlaceTypes.restaurant,
                locationCoordinates,
                500).enqueue(new Callback<NearbySearchList>() {
            @Override
            public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                Log.d(TAG, "restaurant onResponse: "+response.body().getResults().size());
                if(response.body().getResults().size()!=0) {
                    placeCatalogue.add(response.body().getResults().get(0));
                    if(response.body().getResults().size()!=1)
                    placeCatalogue.add(response.body().getResults().get(1));
                }
                nearByBar(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }

            @Override
            public void onFailure(Call<NearbySearchList> call, Throwable t) {
                Log.d(TAG, "Prepare catalogue onFailure: "+t);
                nearByBar(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }
        });
    }
    private void nearByBar(final NearbySearchApi nearbySearchApi,
                           final String locationCoordinates,
                           final ArrayList<Results>placeCatalogue){
        nearbySearchApi.getNearBySearchResltsbytype(
                PlaceTypes.bar,
                locationCoordinates,
                2500).enqueue(new Callback<NearbySearchList>() {
            @Override
            public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                Log.d(TAG, "bar onResponse: "+response.body().getResults().size());

                if(response.body().getResults().size()!=0) {
                    placeCatalogue.add(response.body().getResults().get(0));
                    if(response.body().getResults().size()!=1) {
                        placeCatalogue.add(response.body().getResults().get(1));
                    }
                }
                nearByCafe(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }

            @Override
            public void onFailure(Call<NearbySearchList> call, Throwable t) {
                Log.d(TAG, "Prepare catalogue onFailure: "+t);
                nearByCafe(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }
        });
    }
    private void nearByCafe(final NearbySearchApi nearbySearchApi,
                            final String locationCoordinates,
                            final ArrayList<Results>placeCatalogue){
        nearbySearchApi.getNearBySearchResltsbytype(
                PlaceTypes.cafe,
                locationCoordinates,
                500).enqueue(new Callback<NearbySearchList>() {
            @Override
            public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                Log.d(TAG, "cafe onResponse: "+response.body().getResults().size());
                if(response.body().getResults().size()!=0) {
                    placeCatalogue.add(response.body().getResults().get(0));
                    if(response.body().getResults().size()!=1) {
                        placeCatalogue.add(response.body().getResults().get(1));
                    }
                }
                nearBySpa(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }

            @Override
            public void onFailure(Call<NearbySearchList> call, Throwable t) {
                Log.d(TAG, "Prepare catalogue onFailure: "+t);
                nearBySpa(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }
        });
    }
    private void nearBySpa(final NearbySearchApi nearbySearchApi,
                                   final String locationCoordinates,
                                   final ArrayList<Results>placeCatalogue){
        nearbySearchApi.getNearBySearchResltsbytype(
                PlaceTypes.spa,
                locationCoordinates,
                2500).enqueue(new Callback<NearbySearchList>() {
            @Override
            public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                Log.d(TAG, "spa onResponse: "+response.body().getResults().size());

                if(response.body().getResults().size()!=0) {
                   placeCatalogue.add(response.body().getResults().get(0));
               }
                nearByMall(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }

            @Override
            public void onFailure(Call<NearbySearchList> call, Throwable t) {
                Log.d(TAG, "Prepare catalogue onFailure: "+t);
                nearByMall(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }
        });
    }
    private void nearByMall(final NearbySearchApi nearbySearchApi,
                            final String locationCoordinates,
                            final ArrayList<Results>placeCatalogue){
        nearbySearchApi.getNearBySearchResltsbytype(
                PlaceTypes.mall,
                locationCoordinates,
                2500).enqueue(new Callback<NearbySearchList>() {
            @Override
            public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                Log.d(TAG, "mall onResponse: "+response.body().getResults().size());
                if(response.body().getResults().size()!=0) {
                    placeCatalogue.add(response.body().getResults().get(0));
                    if(response.body().getResults().size()!=1) {
                        placeCatalogue.add(response.body().getResults().get(1));
                    }
                }
                nearByMovies(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }

            @Override
            public void onFailure(Call<NearbySearchList> call, Throwable t) {
                Log.d(TAG, "Prepare catalogue onFailure: "+t);
                nearByMovies(nearbySearchApi,
                        locationCoordinates,
                        placeCatalogue);
            }
        });
    }
    private void nearByMovies(NearbySearchApi nearbySearchApi,
                            String locationCoordinates,
                            final ArrayList<Results>placeCatalogue){
        nearbySearchApi.getNearBySearchResltsbytype(
                PlaceTypes.movies,
                locationCoordinates,
                2500).enqueue(new Callback<NearbySearchList>() {
            @Override
            public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                Log.d(TAG, "movies onResponse: "+response.body().getResults().size());
                if(response.body().getResults().size()!=0) {
                    placeCatalogue.add(response.body().getResults().get(0));
                }
                Log.d(TAG, "Catalogue size onResponse: "+placeCatalogue.size());
                placeAdapter.updatePlaceList(placeCatalogue,progressBar);
            }

            @Override
            public void onFailure(Call<NearbySearchList> call, Throwable t) {
                Log.d(TAG, "Prepare catalogue onFailure: "+t);
                //To implement snack bar here
                Log.d(TAG, "onFailure: List preparation failed");
            }
        });
    }

}
