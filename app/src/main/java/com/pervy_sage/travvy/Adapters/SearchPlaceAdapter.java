package com.pervy_sage.travvy.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pervy_sage on 11/7/17.
 */

public class SearchPlaceAdapter
        extends RecyclerView.Adapter<SearchPlaceAdapter.SearchPlaceHolder>{
    @Override
    public SearchPlaceAdapter.SearchPlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchPlaceAdapter.SearchPlaceHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchPlaceHolder extends RecyclerView.ViewHolder {
        public SearchPlaceHolder(View itemView) {
            super(itemView);
        }
    }
}
