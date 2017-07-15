package com.pervy_sage.travvy.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.pervy_sage.travvy.APIs.AutoCompleteAPI;
import com.pervy_sage.travvy.Adapters.SearchPlaceAdapter;
import com.pervy_sage.travvy.R;
import com.pervy_sage.travvy.RetroFitPlaces;
import com.pervy_sage.travvy.ViewPlaceActivity;
import com.pervy_sage.travvy.interfaces.OnViewClickListener;
import com.pervy_sage.travvy.models.PlaceAutoComplete;
import com.pervy_sage.travvy.models.Prediction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {
    private RecyclerView rvSearch;
    private EditText etSearchBox;
    private ImageButton btnSearch;
    private ProgressBar progressBar;
    private SearchPlaceAdapter searchPlaceAdapter;


    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        rvSearch=(RecyclerView)view.findViewById(R.id.rvSearch);
        etSearchBox=(EditText)view.findViewById(R.id.etSearchBox);
        btnSearch=(ImageButton)view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String input = etSearchBox.getText().toString();
                performSearch(input);

            }
        });
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        searchPlaceAdapter= new SearchPlaceAdapter(getContext(),new ArrayList<Prediction>());
        searchPlaceAdapter.setViewClickListener(new OnViewClickListener() {
            @Override
            public void onViewClick(String string) {
                Intent i = new Intent(getContext(), ViewPlaceActivity.class);
                i.putExtra("placeId",string);
                startActivity(i);
            }
        });
        rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSearch.setAdapter(searchPlaceAdapter);
        return  view;
    }

    private void performSearch(String input){
        AutoCompleteAPI autoCompleteAPI= RetroFitPlaces.getRetrofitAPIs().getAutoCompleteAPI();
        autoCompleteAPI.getAUtoCompleteResults(input).enqueue(new Callback<PlaceAutoComplete>() {
            @Override
            public void onResponse(Call<PlaceAutoComplete> call, Response<PlaceAutoComplete> response) {
               if(response.body().getPredictions()!=null &&
                       response.body().getPredictions().size()!=0)
                searchPlaceAdapter.updateList(response.body().getPredictions(),progressBar);
            }

            @Override
            public void onFailure(Call<PlaceAutoComplete> call, Throwable t) {

            }
        });

    }


}
