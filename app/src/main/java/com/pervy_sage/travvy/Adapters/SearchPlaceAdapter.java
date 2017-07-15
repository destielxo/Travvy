package com.pervy_sage.travvy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pervy_sage.travvy.R;
import com.pervy_sage.travvy.interfaces.OnViewClickListener;
import com.pervy_sage.travvy.models.Prediction;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by pervy_sage on 11/7/17.
 */

public class SearchPlaceAdapter
        extends RecyclerView.Adapter<SearchPlaceAdapter.SearchPlaceHolder>{
    Context context;
    ArrayList<Prediction> predictions;

    public SearchPlaceAdapter(Context context, ArrayList<Prediction> predictions) {
        this.context = context;
        this.predictions = predictions;
    }

    public void updateList(ArrayList<Prediction> predictions, ProgressBar progressBar){
        this.predictions=predictions;
        progressBar.setVisibility(View.INVISIBLE);
        notifyDataSetChanged();
    }

    OnViewClickListener viewClickListener;
    public void setViewClickListener(OnViewClickListener viewClickListener){
        this.viewClickListener=viewClickListener;
    }

    @Override
    public SearchPlaceAdapter.SearchPlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_search_items,parent,false);
        return new SearchPlaceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SearchPlaceAdapter.SearchPlaceHolder holder, int position) {
        if(predictions!=null&&predictions.size()!=0){
            final Prediction thisPrediction = predictions.get(position);
            holder.tvPlaceDescrp.setText(thisPrediction.getDescription());
            holder.rootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewClickListener!=null){
                        viewClickListener.onViewClick(thisPrediction.getPlace_id());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(predictions!=null) {
            return predictions.size();
        }

        return 0;
    }

    public class SearchPlaceHolder extends RecyclerView.ViewHolder {
        TextView tvPlaceDescrp;
        View rootview;
        public SearchPlaceHolder(View itemView) {
            super(itemView);
            tvPlaceDescrp=(TextView)itemView.findViewById(R.id.tvSearchDescrp);
            rootview=itemView;
        }
    }
}
