package com.pervy_sage.travvy.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pervy_sage.travvy.R;
import com.pervy_sage.travvy.models.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pervy_sage on 9/7/17.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    Context mContext;
    ArrayList<Results> placesList;
    public static final String TAG="debug";
    public static final String
            basePhotoUrl="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=";
    public static final String API_KEY="AIzaSyAKss8SNS-zZXLZYTTtk6e9-HhzPiBDP-c";


    public PlaceAdapter(Context mContext, ArrayList<Results> placesList) {
        this.mContext = mContext;
        this.placesList = placesList;
    }

    public void updatePlaceList(ArrayList<Results> placesList){
        Log.d(TAG, "updatePlaceList: "+placesList.size());
        this.placesList=placesList;
        notifyDataSetChanged();
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.list_item_places,parent,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        if(placesList.size()!=0) {
            Results thisPlace = placesList.get(position);
            if(thisPlace.getPhotos()!=null) {
                String photoreference = thisPlace.getPhotos().get(0).getPhoto_reference();
                Log.d(TAG, "onBindViewHolder: " + thisPlace.getPhotos().get(0).getHeight());
                Log.d(TAG, "onBindViewHolder: " + photoreference);
                String photoUri = basePhotoUrl + API_KEY + "&photoreference=" + photoreference;
                Picasso.with(mContext).load(photoUri).
                        fit().
                        placeholder(R.drawable.loading).
                        error(R.drawable.error_image).
                        into(holder.ivPlaceImage);
            }

            holder.tvPlaceName.setText(thisPlace.getName());
        }

    }

    @Override
    public int getItemCount() {
        return placesList.size()/3;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlaceImage;
        TextView tvPlaceName;
        View rootView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            tvPlaceName=(TextView)itemView.findViewById(R.id.tvPlaceName);
            ivPlaceImage=(ImageView)itemView.findViewById(R.id.ivPlaceImage);
            rootView=itemView;

        }
    }
}
