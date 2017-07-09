package com.pervy_sage.travvy.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tagmanager.InstallReferrerService;
import com.pervy_sage.travvy.Adapters.PlaceAdapter;
import com.pervy_sage.travvy.R;
import com.pervy_sage.travvy.models.Results;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogueFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG="debug";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rvPlacesView;
    private PlaceAdapter placeAdapter;


    public CatalogueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogueFragment newInstance(String param1, String param2) {
        Log.d(TAG, "newInstance: ");
        CatalogueFragment fragment = new CatalogueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        placeAdapter=new PlaceAdapter(getContext(),new ArrayList<Results>());
        rvPlacesView.setAdapter(placeAdapter);

        return itemView;
    }

    public void updatePlacesList(ArrayList<Results> results){
        Log.d(TAG, "updatePlacesList: "+ results.size());
        placeAdapter.updatePlaceList(results);
    }

}
