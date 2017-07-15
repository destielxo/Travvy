package com.pervy_sage.travvy.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.pervy_sage.travvy.PlaceTypes;
import com.pervy_sage.travvy.R;
import com.pervy_sage.travvy.interfaces.OnCatalogueCreated;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaceTypeBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceTypeBarFragment extends Fragment  implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LOCATION = "location";
    public static final String TAG="placetypebar";

    // TODO: Rename and change types of parameters
    private String locationCoordinates;
    private PlaceCategoryFragment categoryFragment;
    private LinearLayout llBar, llCafe, llRestaurant,
            llSpa, llHotel, llHospital, llMall, llMovies;


    public PlaceTypeBarFragment() {
        Log.d(TAG, "PlaceTypeBarFragment: ");
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PlaceTypeBarFragment newInstance(String locationCoordinates) {
        PlaceTypeBarFragment fragment = new PlaceTypeBarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LOCATION, locationCoordinates);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            locationCoordinates = getArguments().getString(ARG_LOCATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        View itemVIew = inflater.inflate(R.layout.fragment_place_type_bar, container, false);

        llBar = (LinearLayout) itemVIew.findViewById(R.id.llBar);
        llCafe = (LinearLayout) itemVIew.findViewById(R.id.llCafe);
        llRestaurant = (LinearLayout) itemVIew.findViewById(R.id.llRestaurant);
        llSpa = (LinearLayout) itemVIew.findViewById(R.id.llSpa);
        llHotel = (LinearLayout) itemVIew.findViewById(R.id.llHotel);
        llHospital = (LinearLayout) itemVIew.findViewById(R.id.llHospital);
        llMall = (LinearLayout) itemVIew.findViewById(R.id.llMall);
        llMovies = (LinearLayout) itemVIew.findViewById(R.id.llMovies);

        llMovies.setOnClickListener(this);
        llMall.setOnClickListener(this);
        llHospital.setOnClickListener(this);
        llHotel.setOnClickListener(this);
        llSpa.setOnClickListener(this);
        llRestaurant.setOnClickListener(this);
        llCafe.setOnClickListener(this);
        llBar.setOnClickListener(this);


        return itemVIew;
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");
        final FragmentManager fragmentManager = getFragmentManager();
        switch (v.getId()) {
            case R.id.llBar:
                createfragment(PlaceTypes.bar);
                break;
            case R.id.llCafe:
                createfragment(PlaceTypes.cafe);
                break;
            case R.id.llRestaurant:
                createfragment(PlaceTypes.restaurant);
                break;
            case R.id.llSpa:
                createfragment(PlaceTypes.spa);
                break;
            case R.id.llHospital:
                createfragment(PlaceTypes.hospitals);
                break;
            case R.id.llHotel:
                createfragment(PlaceTypes.hotels);
                break;
            case R.id.llMall:
                createfragment(PlaceTypes.mall);
                break;
            case R.id.llMovies:
                createfragment(PlaceTypes.movies);
                break;
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                fragmentManager.beginTransaction().
                        replace(R.id.catalogueFragConatiner,categoryFragment).
                        commit();
            }
        });
        t.start();
    }

    private void createfragment(String placType) {
        categoryFragment =
                PlaceCategoryFragment.newInstance(
                        locationCoordinates,
                        placType,
                        new OnCatalogueCreated() {
                            @Override
                            public void onCatalogueCreated() {
                                categoryFragment.prepareCategoryCatalogue();
                            }
                        }
                );
    }
}
