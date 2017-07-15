package com.pervy_sage.travvy;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import com.pervy_sage.travvy.fragments.CatalogueFragment;
import com.pervy_sage.travvy.fragments.DestinationFragment;
import com.pervy_sage.travvy.fragments.PlaceCategoryFragment;
import com.pervy_sage.travvy.fragments.PlaceTypeBarFragment;
import com.pervy_sage.travvy.fragments.SearchFragment;
import com.pervy_sage.travvy.interfaces.OnCatalogueCreated;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = "Home";
    private MyLocationListener myLocationListener;
    private CatalogueFragment catalogueFragment;
    private LinearLayout llDestination,llNearby,llExplorer;
    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        llNearby=(LinearLayout)findViewById(R.id.llNearby);
        llDestination=(LinearLayout)findViewById(R.id.llDestination);
        llExplorer=(LinearLayout)findViewById(R.id.llExplore);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.CDlayout);
        myLocationListener = new MyLocationListener(this);

       createLayouts();


//        final String locationCoordinates=myLocationListener.fetchLocationParms();
//        Log.d(TAG, "onCreate: "+locationCoordinates);
//
//        if(locationCoordinates!=MyLocationListener.Connection_Failed){
//            catalogueFragment = CatalogueFragment.newInstance(new OnCatalogueCreated() {
//                @Override
//                public void onCatalogueCreated() {
//                    catalogueFragment.prepareCatalogue(locationCoordinates);
//                }
//            });
//           final  FragmentManager fragmentManager = getSupportFragmentManager();
//
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.placeTypesFragContainer,
//                                    PlaceTypeBarFragment.newInstance(locationCoordinates))
//                            .commit();
//                }
//            });
//            t.start();
//            Thread t1 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.catalogueFragConatiner,
//                                    catalogueFragment)
//                            .commit();
//
//                }
//            });
//            t1.start();
//
//            llNearby.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Fragment f = getSupportFragmentManager()
//                            .findFragmentById(R.id.SearchfragContainer);
//                    if(f!=null){
//                    fragmentManager.
//                            beginTransaction()
//                            .remove(f)
//                            .commitNow();
//                    }
//                    f=getSupportFragmentManager()
//                            .findFragmentById(R.id.DesitinationsfragContainer);
//                    if(f!=null) {
//                        fragmentManager.
//                                beginTransaction().
//                                remove(f)
//                                .commitNow();
//                    }
//                    fragmentManager
//                            .beginTransaction()
//                            .replace(R.id.placeTypesFragContainer,
//                                    PlaceTypeBarFragment.newInstance(locationCoordinates))
//                            .commit();
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.catalogueFragConatiner,
//                                    catalogueFragment)
//                            .commit();
//                }
//            });
//
//            llDestination.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   Fragment f=getSupportFragmentManager()
//                           .findFragmentById(R.id.SearchfragContainer);
//                    if(f!=null) {
//                        fragmentManager.
//                                beginTransaction()
//                                .remove(f)
//                                .commitNow();
//                    }
//                    f=getSupportFragmentManager()
//                            .findFragmentById(R.id.placeTypesFragContainer);
//                    if(f!=null) {
//                        fragmentManager
//                                .beginTransaction()
//                                .remove(f)
//                                .commitNow();
//                    }
//                    f=getSupportFragmentManager()
//                            .findFragmentById(R.id.catalogueFragConatiner);
//                    if(f!=null) {
//                        fragmentManager
//                                .beginTransaction()
//                                .remove(f)
//                                .commitNow();
//                    }
//                    fragmentManager.
//                            beginTransaction()
//                            .replace(R.id.DesitinationsfragContainer,
//                                    DestinationFragment.newInstance())
//                            .commit();
//
//
//                }
//            });
//            llExplorer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Fragment f =getSupportFragmentManager()
//                            .findFragmentById(R.id.placeTypesFragContainer);
//                    if(f!=null) {
//                        fragmentManager
//                                .beginTransaction()
//                                .remove(f)
//                                .commitNow();
//                    }
//                    f=getSupportFragmentManager()
//                            .findFragmentById(R.id.catalogueFragConatiner);
//                    if(f!=null) {
//                        fragmentManager
//                                .beginTransaction()
//                                .remove(f)
//                                .commitNow();
//                    }
//                    f=getSupportFragmentManager()
//                            .findFragmentById(R.id.DesitinationsfragContainer);
//                    if(f!=null) {
//                        fragmentManager.
//                                beginTransaction().
//                                remove(f)
//                                .commitNow();
//                    }
//                    fragmentManager
//                            .beginTransaction()
//                            .replace(R.id.SearchfragContainer, SearchFragment.newInstance())
//                            .commit();
//                }
//            });
//
    }

    private void Retry(){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createLayouts();
                    }
                });
        snackbar.show();
    }

    private void createLayouts() {
        myLocationListener = new MyLocationListener(this);

        final String locationCoordinates = myLocationListener.fetchLocationParms();
        Log.d(TAG, "onCreate: " + locationCoordinates);

        if (locationCoordinates != MyLocationListener.Connection_Failed) {
            catalogueFragment = CatalogueFragment.newInstance(new OnCatalogueCreated() {
                @Override
                public void onCatalogueCreated() {
                    catalogueFragment.prepareCatalogue(locationCoordinates);
                }
            });
            final FragmentManager fragmentManager = getSupportFragmentManager();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    fragmentManager.beginTransaction()
                            .replace(R.id.placeTypesFragContainer,
                                    PlaceTypeBarFragment.newInstance(locationCoordinates))
                            .commit();
                }
            });
            t.start();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {

                    fragmentManager.beginTransaction()
                            .replace(R.id.catalogueFragConatiner,
                                    catalogueFragment)
                            .commit();

                }
            });
            t1.start();

            llNearby.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment f = getSupportFragmentManager()
                            .findFragmentById(R.id.SearchfragContainer);
                    if (f != null) {
                        fragmentManager.
                                beginTransaction()
                                .remove(f)
                                .commitNow();
                    }
                    f = getSupportFragmentManager()
                            .findFragmentById(R.id.DesitinationsfragContainer);
                    if (f != null) {
                        fragmentManager.
                                beginTransaction().
                                remove(f)
                                .commitNow();
                    }
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.placeTypesFragContainer,
                                    PlaceTypeBarFragment.newInstance(locationCoordinates))
                            .commit();
                    fragmentManager.beginTransaction()
                            .replace(R.id.catalogueFragConatiner,
                                    catalogueFragment)
                            .commit();
                }
            });

            llDestination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment f = getSupportFragmentManager()
                            .findFragmentById(R.id.SearchfragContainer);
                    if (f != null) {
                        fragmentManager.
                                beginTransaction()
                                .remove(f)
                                .commitNow();
                    }
                    f = getSupportFragmentManager()
                            .findFragmentById(R.id.placeTypesFragContainer);
                    if (f != null) {
                        fragmentManager
                                .beginTransaction()
                                .remove(f)
                                .commitNow();
                    }
                    f = getSupportFragmentManager()
                            .findFragmentById(R.id.catalogueFragConatiner);
                    if (f != null) {
                        fragmentManager
                                .beginTransaction()
                                .remove(f)
                                .commitNow();
                    }
                    fragmentManager.
                            beginTransaction()
                            .replace(R.id.DesitinationsfragContainer,
                                    DestinationFragment.newInstance())
                            .commit();


                }
            });
            llExplorer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment f = getSupportFragmentManager()
                            .findFragmentById(R.id.placeTypesFragContainer);
                    if (f != null) {
                        fragmentManager
                                .beginTransaction()
                                .remove(f)
                                .commitNow();
                    }
                    f = getSupportFragmentManager()
                            .findFragmentById(R.id.catalogueFragConatiner);
                    if (f != null) {
                        fragmentManager
                                .beginTransaction()
                                .remove(f)
                                .commitNow();
                    }
                    f = getSupportFragmentManager()
                            .findFragmentById(R.id.DesitinationsfragContainer);
                    if (f != null) {
                        fragmentManager.
                                beginTransaction().
                                remove(f)
                                .commitNow();
                    }
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.SearchfragContainer, SearchFragment.newInstance())
                            .commit();
                }
            });


        }else {
            Retry();
        }
    }



}
