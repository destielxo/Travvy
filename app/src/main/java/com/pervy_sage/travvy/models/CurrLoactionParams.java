package com.pervy_sage.travvy.models;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.pervy_sage.travvy.APIs.NearbySearchApi;
import com.pervy_sage.travvy.fragments.CatalogueFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pervy_sage on 8/7/17.
 */

public class CurrLoactionParams {
    private double latitude;
    private double longitude;
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;
    private CatalogueFragment catalogueFragment;

    public static final String TAG="debug";

    private Context mContext;

    private Geocoder geocoder;
    private List<Address> addresses;
    private FusedLocationProviderClient mfusedLocationProviderClient;


    public interface OnFetchListener{
        void onFetch();
    }
    private OnFetchListener onFetchListener;



    public void setOnFetchListener(OnFetchListener onFetchListener){
        this.onFetchListener= onFetchListener;
    }

    public CurrLoactionParams(Context mContext,CatalogueFragment catalogueFragment,OnFetchListener onFetchListener) {
        Log.d(TAG, "CurrLoactionParams: ");
        this.mContext = mContext;
        this.catalogueFragment=catalogueFragment;
        this.mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext);
        this.geocoder = new Geocoder(mContext, Locale.ENGLISH);
        this.setOnFetchListener(onFetchListener);
    }

    public void fetchCurrLoactionParams() {
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.mfusedLocationProviderClient.getLastLocation()
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                            latitude=task.getResult().getLatitude();
                        Log.d(TAG, "onComplete: "+latitude);
                            longitude=task.getResult().getLongitude();

                        try {
                            addresses=geocoder.getFromLocation(latitude,
                                    longitude,1);
                            addressLine = addresses.get(0).getAddressLine(0);
                            city=addresses.get(0).getLocality();
                            postalCode=addresses.get(0).getPostalCode();
                            country=addresses.get(0).getCountryName();

                        } catch (IOException e) {
                            Log.d("timedout", "onComplete: ");
                            e.printStackTrace();
                        }
                    }
                });
        onFetchListener.onFetch();

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}
