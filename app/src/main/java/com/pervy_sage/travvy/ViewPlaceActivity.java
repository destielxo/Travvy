package com.pervy_sage.travvy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pervy_sage.travvy.models.*;

import com.pervy_sage.travvy.APIs.PlaceDetailApi;
import com.pervy_sage.travvy.Adapters.PlaceImageAdapter;
import com.pervy_sage.travvy.models.Photo;
import com.pervy_sage.travvy.models.PlaceDetails;
import com.pervy_sage.travvy.models.PlaceResult;
import com.pervy_sage.travvy.ReviewsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPlaceActivity extends AppCompatActivity {
    RecyclerView rvPlaceImageView;
    PlaceImageAdapter placeImageAdapter;
    TextView tvPlaceName, tvPlaceTypes, tvPlaceAddress,
            tvOpenOrClose, tvReviewPersonName, tvReviewTime, tvReviewBody,
            tvViewAllReviews, tvPlaceUrl, tvContactNo,tvRating;
    ImageView ivReviewProfilePic;
    Button btnCallNumber;

    public static final String TAG = "ViewPlace";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);
        rvPlaceImageView = (RecyclerView) findViewById(R.id.rvPlaceImageView);
        rvPlaceImageView.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,
                        false));
        placeImageAdapter = new PlaceImageAdapter(
                this,
                new ArrayList<Photo>()
        );
        rvPlaceImageView.setAdapter(placeImageAdapter);

        tvContactNo = (TextView) findViewById(R.id.tvContactNo);
        tvPlaceUrl = (TextView) findViewById(R.id.tvPlaceUrl);
        tvViewAllReviews = (TextView) findViewById(R.id.tvReviewViewAll);
        tvReviewBody = (TextView) findViewById(R.id.tvReviewBody);
        tvReviewTime = (TextView) findViewById(R.id.tvReviewTime);
        tvReviewPersonName = (TextView) findViewById(R.id.tvReviewPersonName);
        tvOpenOrClose = (TextView) findViewById(R.id.tvOpenOrClose);
        tvPlaceAddress = (TextView) findViewById(R.id.tvPlaceAddress);
        tvPlaceTypes = (TextView) findViewById(R.id.tvPlaceTypes);
        tvPlaceName = (TextView) findViewById(R.id.tvPlaceName);
        tvRating=(TextView)findViewById(R.id.tvReviewRating);
        ivReviewProfilePic = (ImageView) findViewById(R.id.ivReviewProfilePic);
        btnCallNumber = (Button) findViewById(R.id.btnCallNumber);


        String placeId = getIntent().getStringExtra("placeId");
        Log.d(TAG, "onCreate: " + placeId);
        PlaceDetailApi placeDetailApi =
                RetroFitPlaces.getRetrofitAPIs().getPlaceDetailApi();
        placeDetailApi.
                getPlaceDetails(placeId).
                enqueue(new Callback<PlaceDetails>() {
                    @Override
                    public void onResponse(Call<PlaceDetails> call, Response<PlaceDetails> response) {
                        if(response.body().getResult()!=null) {
                            feedViewPlacePage(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<PlaceDetails> call, Throwable t) {

                    }
                });
    }

    private void feedViewPlacePage(Response<PlaceDetails> response) {
        PlaceResult result = response.body().getResult();
        Log.d(TAG, "feedViewPlacePage: "+result);
        placeImageAdapter.updateImageList(result.getPhotos());
        tvPlaceName.setText(result.getName());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.getTypes().size(); i++) {
            String type = result.getTypes().get(i);
            if (!type.equals("point_of_interest") && !type.equals("establishment")) {
                sb.append(type + ", ");
            }
        }
        tvPlaceTypes.setText(sb.toString());

        tvPlaceAddress.setText(result.getFormatted_address());

        OpeningHours openingHours = result.getOpening_hours();
        if (openingHours != null) {
            if (openingHours.isOpen_now()) {
                tvOpenOrClose.setText("Open Now");
            } else {
                tvOpenOrClose.setText("Closed Now");
            }
        } else {
            tvOpenOrClose.setText("");
        }
        if (result.getReviews() != null) {
            Log.d(TAG, "feedViewPlacePage: reviews");
            Review firstReview = result.getReviews().get(0);
            Picasso.with(ViewPlaceActivity.this)
                    .load(firstReview.getProfile_photo_url())
                    .error(R.drawable.error_image)
                    .into(ivReviewProfilePic);
            tvReviewPersonName.setText(firstReview.getAuthor_name());
            tvReviewTime.setText(firstReview.getRelative_time_description());
            tvReviewBody.setText(firstReview.getText());
            tvRating.setText(String.valueOf(firstReview.getRating()));
        }else{
            Log.d(TAG, "feedViewPlacePage: setreviewsnull");
                tvReviewPersonName.setText("No Person Name");
                tvReviewTime.setText("No Time");
                tvReviewBody.setText("No Comment");
                tvRating.setText("No Stars");
        }
        if(result.getWebsite()!=null) {
            tvPlaceUrl.setText(result.getWebsite());
        }else{
            tvPlaceUrl.setText("NO URL AVAILABLE");
        }
        tvRating.setText(String.valueOf(result.getRating()));
        if(result.getInternational_phone_number()!=null) {
            tvContactNo.setText(result.getInternational_phone_number());
        }else{
            tvContactNo.setText("NO NUMBER");
        }
        setListeners(result.getReviews());
    }

    private void setListeners(final ArrayList<Review> reviews){
        if(!tvPlaceUrl.getText().toString().equals("NO URL AVAILABLE")){
            tvPlaceUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(tvPlaceUrl.getText().toString()));
                    startActivity(i);
                }
            });
        }
        if(reviews!=null){
            Log.d(TAG, "setListeners: reviews");
            tvViewAllReviews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ViewPlaceActivity.this,
                    ReviewsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("AllReviews",reviews);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            });
        }else{
            tvViewAllReviews.setText("No Reviews");
        }
        if(!tvContactNo.getText().toString().equals("NO NUMBER")) {
            Log.d(TAG, "setListeners: visible");
            btnCallNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, " btn call onClick: ");
                    Intent callIntent = new Intent(Intent.ACTION_CALL,
                            Uri.parse("tel:" + tvContactNo.getText().toString()));
                    if (ActivityCompat.checkSelfPermission(ViewPlaceActivity.this
                            , android.Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callIntent);
                }
            });
        }else{
            Log.d(TAG, "setListeners: invisibilty");
            btnCallNumber.setVisibility(View.INVISIBLE);
        }
    }
}
