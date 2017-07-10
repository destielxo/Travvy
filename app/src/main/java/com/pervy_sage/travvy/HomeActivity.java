package com.pervy_sage.travvy;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.pervy_sage.travvy.fragments.CatalogueFragment;
import com.pervy_sage.travvy.fragments.PlaceTypeBarFragment;
import com.pervy_sage.travvy.interfaces.OnCatalogueCreated;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = "Home";
    private MyLocationListener myLocationListener;
    private CatalogueFragment catalogueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myLocationListener = new MyLocationListener(this);
        final String locationCoordinates=myLocationListener.fetchLocationParms();
        Log.d(TAG, "onCreate: "+locationCoordinates);
        if(locationCoordinates!=MyLocationListener.Connection_Failed){
            catalogueFragment = CatalogueFragment.newInstance(new OnCatalogueCreated() {
                @Override
                public void onCatalogueCreated() {
                    catalogueFragment.prepareCatalogue(locationCoordinates);
                }
            });
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.placeTypesFragContainer,
                            PlaceTypeBarFragment.newInstance(locationCoordinates))
                    .commitNow();
            fragmentManager.beginTransaction()
                    .replace(R.id.catalogueFragConatiner,
                            catalogueFragment)
                    .commitNow();
//            ArrayList<Results>
//                    catalogue = prepareCatalogue(locationCoordinates);
//            Log.d(TAG, "onCreate: preparedCatalogue "+catalogue.size());
//            catalogueFragment.updatePlacesList(catalogue);


        }
    }

}
