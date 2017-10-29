package com.pervy_sage.travvy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pervy_sage.travvy.R;
import com.pervy_sage.travvy.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pervy_sage on 10/7/17.
 */

public class PlaceImageAdapter extends RecyclerView.Adapter
        <PlaceImageAdapter.PlaceImageHolder> {
    private Context context;
    private ArrayList<Photo> photos;
    public static final String TAG="ViewImage";

    public static final String
            basePhotoUrl="https://maps.googleapis.com/maps/api/place/photo?maxwidth=1600&key=";
    public static final String API_KEY="AIzaSyCMFUMXldDohn6d3WbtHgvPpGhU3aVqFFE";

    public PlaceImageAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.photos = photos;
    }
    public void updateImageList(ArrayList<Photo> photos){
        this.photos=photos;
        notifyDataSetChanged();
    }

    @Override
    public PlaceImageAdapter.PlaceImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_image_view,parent,false);

        return new PlaceImageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlaceImageAdapter.PlaceImageHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
            if(photos.size()!=0){
                Photo thisPhoto = photos.get(position);
                StringBuilder sb = new StringBuilder();
                Log.d(TAG, "onBindViewHolder: "+thisPhoto.getWidth());
                sb.append(basePhotoUrl).
                        append(API_KEY).
                        append("&photoreference=").
                        append(thisPhoto.getPhoto_reference());
                Picasso picasso =Picasso.with(context);
                        picasso.setLoggingEnabled(true);
                        picasso.load(sb.toString())
                                .placeholder(R.drawable.loading)
                                .error(R.drawable.error_image)
                                .resize(800,600)
                                .into(holder.ivPlaceImage);

            }
    }

    @Override
    public int getItemCount() {
        if(photos!=null) {
            return photos.size();
        }
        return 0;
    }

    public class PlaceImageHolder extends RecyclerView.ViewHolder{
        ImageView ivPlaceImage;
        public PlaceImageHolder(View itemView) {
            super(itemView);
            ivPlaceImage=(ImageView)itemView.findViewById(R.id.ivPlaceImages);
        }
    }
}
