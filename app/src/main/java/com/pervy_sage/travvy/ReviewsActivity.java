package com.pervy_sage.travvy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.pervy_sage.travvy.Adapters.ReviewAdapter;
import com.pervy_sage.travvy.models.Review;

import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {

    RecyclerView rvReviews;
    public static final String TAG="ReviewAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        rvReviews=(RecyclerView)findViewById(R.id.rvReviews);
        rvReviews.setLayoutManager(
                new LinearLayoutManager(this));
        ArrayList<Review> reviews = getIntent().getExtras().getParcelableArrayList("AllReviews");
        Log.d(TAG, "onCreate: "+reviews);
        ReviewAdapter reviewAdapter = new ReviewAdapter(
                this,
                reviews
        );
        rvReviews.setAdapter(reviewAdapter);


    }
}
