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
import com.pervy_sage.travvy.interfaces.OnViewClickListener;
import com.pervy_sage.travvy.models.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pervy_sage on 9/7/17.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    Context mContext;
    ArrayList<Results> placesList;
    public static final String TAG="Adapter";
    public static final String
            basePhotoUrl="https://maps.googleapis.com/maps/api/place/photo?maxwidth=6000&key=";
    public static final String API_KEY="AIzaSyAyEkOpW4ZdBzMgtoz_5SDOa1oUK4DCeMA";
    private OnViewClickListener viewClickListener;
    public void setViewClickListener(OnViewClickListener ovl){
        this.viewClickListener=ovl;
    }

    public PlaceAdapter(Context mContext,
                        ArrayList<Results> placesList,
                        OnViewClickListener viewClickListener) {
        this.mContext = mContext;
        this.placesList = placesList;
        this.viewClickListener=viewClickListener;
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
            final Results thisPlace = placesList.get(position);
            String photoUri=basePhotoUrl + API_KEY;
            int width=0,height=0;
            if(thisPlace.getPhotos()!=null) {
                String photoreference = thisPlace.getPhotos().get(0).getPhoto_reference();
                photoUri += "&photoreference=" + photoreference;
            }
            Picasso.with(mContext).setLoggingEnabled(true);
                    Picasso.with(mContext).load(photoUri).
                    resize(600,600).
                    placeholder(R.drawable.loading).
                    error(R.drawable.error_image).
                    into(holder.ivPlaceImage);
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<thisPlace.getTypes().size();i++){
                String type = thisPlace.getTypes().get(i);
                if(!type.equals("point_of_interest")&&!type.equals("establishment")){
                    sb.append(type+", ");
                }
            }
            Log.d(TAG, "Place id: "+thisPlace.getPlaci_id());

            holder.tvType.setText(sb.toString());
            holder.tvPlaceName.setText(thisPlace.getName());
            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewClickListener.onViewClick(thisPlace.getPlaci_id());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlaceImage;
        TextView tvPlaceName;
        TextView tvType;
        View rootView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            tvPlaceName=(TextView)itemView.findViewById(R.id.tvPlaceName);
            ivPlaceImage=(ImageView)itemView.findViewById(R.id.ivPlaceImage);
            tvType=(TextView)itemView.findViewById(R.id.tvType);
            rootView=itemView;

        }
    }
}
