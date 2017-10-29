package com.pervy_sage.travvy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pervy_sage.travvy.R;
import com.pervy_sage.travvy.models.Review;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by pervy_sage on 11/7/17.
 */

public class ReviewAdapter extends
        RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    Context context;
    ArrayList<Review> reviews;

    public ReviewAdapter(Context context, ArrayList<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.list_item_reviews,parent,false);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ReviewViewHolder holder, int position) {
            if(reviews!=null && reviews.size()!=0){
                Review thiReview = reviews.get(position);
                holder.tvReviewPersonName.setText(thiReview.getAuthor_name());
                holder.tvReviewTime.setText(thiReview.getRelative_time_description());
                holder.tvReviewRating.setText(String.valueOf(thiReview.getRating()));
                holder.tvReviewBody.setText(thiReview.getText());
                Picasso.with(context)
                        .load(thiReview.getProfile_photo_url())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error_image)
                        .into(holder.ivReviewProfilePic);
            }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView tvReviewPersonName,tvReviewBody,tvReviewTime,tvReviewRating;
        ImageView ivReviewProfilePic;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            tvReviewBody=(TextView)itemView.findViewById(R.id.tvReviewBody);
            tvReviewPersonName=(TextView)itemView.findViewById(R.id.tvReviewPersonName);
            tvReviewRating=(TextView)itemView.findViewById(R.id.tvReviewRating);
            tvReviewTime=(TextView)itemView.findViewById(R.id.tvReviewTime);

            ivReviewProfilePic=(ImageView)itemView.findViewById(R.id.ivReviewProfilePic);

        }
    }
}
