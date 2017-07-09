package com.pervy_sage.travvy;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.pervy_sage.travvy.APIs.NearbySearchApi;
import com.pervy_sage.travvy.fragments.CatalogueFragment;
import com.pervy_sage.travvy.models.CurrLoactionParams;
import com.pervy_sage.travvy.models.NearbySearchList;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private CurrLoactionParams currLoactionParams;
    public static final String TAG = "debug";
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final NearbySearchApi nearbySearchApi = retrofit.create(NearbySearchApi.class);

        final CatalogueFragment catalogueFragment = CatalogueFragment.newInstance("pui", "tui");


//        currLoactionParams = new CurrLoactionParams(this,catalogueFragment, new CurrLoactionParams.OnFetchListener() {
//            @Override
//            public void onFetch() {
//                Log.d(TAG, "onCreate:lAtitude "+currLoactionParams.getLatitude());
//
//            }
//        });
//        currLoactionParams.fetchCurrLoactionParams();


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.currLocationFragConatiner,
                        catalogueFragment)
                .commit();

        MyLocationListener myLocationListener = new MyLocationListener();
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationListener);
        Location lastLoaction = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        myLocationListener.onLocationChanged(lastLoaction);
        latitude = myLocationListener.getLatitude();
        Log.d(TAG, "onCreate: LATITUDE from listener "+latitude);
        nearbySearchApi.getNearBySearchReslts(
                myLocationListener.getLatitude()+","
                +myLocationListener.getLongitude(),
                500
        ).enqueue(new Callback<NearbySearchList>() {
            @Override
            public void onResponse(Call<NearbySearchList> call, Response<NearbySearchList> response) {
                Log.d(TAG, "onResponse: "+response.body().getResults().size());
                catalogueFragment.updatePlacesList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<NearbySearchList> call, Throwable t) {

            }
        });

 }

}
