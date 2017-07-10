package com.pervy_sage.travvy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.pervy_sage.travvy.models.*;

import com.pervy_sage.travvy.APIs.PlaceDetailApi;
import com.pervy_sage.travvy.Adapters.PlaceImageAdapter;
import com.pervy_sage.travvy.models.Photo;
import com.pervy_sage.travvy.models.PlaceDetails;
import com.pervy_sage.travvy.models.PlaceResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPlaceActivity extends AppCompatActivity {
    RecyclerView rvPlaceImageView;
    PlaceImageAdapter placeImageAdapter;
    TextView tvPlaceName,tvPlaceTypes,tvPlaceAddress,
    tvOpenOrClose,tvReviewPersonName,tvReviewTime,tvReviewBody,
    tvViewAllReviews,tvPlaceUrl,tvContactNo;
    ImageView ivReviewProfilePic;
    Button btnCallNumber;

    public static final String TAG="ViewPlace";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);
        rvPlaceImageView=(RecyclerView)findViewById(R.id.rvPlaceImageView);
        rvPlaceImageView.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,
                        false));
        placeImageAdapter = new PlaceImageAdapter(
                this,
                new ArrayList<Photo>()
        );
        rvPlaceImageView.setAdapter(placeImageAdapter);

        tvContactNo=(TextView)findViewById(R.id.tvContactNo);
        tvPlaceUrl=(TextView)findViewById(R.id.tvPlaceUrl);
        tvViewAllReviews=(TextView)findViewById(R.id.tvReviewViewAll);
        tvReviewBody=(TextView)findViewById(R.id.tvReviewBody);
        tvReviewTime=(TextView)findViewById(R.id.tvReviewTime);
        tvReviewPersonName=(TextView)findViewById(R.id.tvReviewPersonName);
        tvOpenOrClose=(TextView)findViewById(R.id.tvOpenOrClose);
        tvPlaceAddress=(TextView)findViewById(R.id.tvPlaceAddress);
        tvPlaceTypes=(TextView)findViewById(R.id.tvPlaceTypes);
        tvPlaceName=(TextView)findViewById(R.id.tvPlaceName);
        ivReviewProfilePic=(ImageView)findViewById(R.id.ivReviewProfilePic);
        btnCallNumber=(Button)findViewById(R.id.btnCallNumber);



        String placeId = getIntent().getStringExtra("placeId");
        PlaceDetailApi placeDetailApi =
                RetroFitPlaces.getRetrofitAPIs().getPlaceDetailApi();
        placeDetailApi.
                getPlaceDetails(placeId).
                enqueue(new Callback<PlaceDetails>() {
                    @Override
                    public void onResponse(Call<PlaceDetails> call, Response<PlaceDetails> response) {
                      feedViewPlacePage(response);
                    }

                    @Override
                    public void onFailure(Call<PlaceDetails> call, Throwable t) {

                    }
                });
    }

    private void feedViewPlacePage(Response<PlaceDetails> response){
        PlaceResult result=response.body().getResult();
        Log.d(TAG, "feedViewPlacePage: "+result);
        placeImageAdapter.updateImageList(result.getPhotos());
        tvPlaceName.setText(result.getName());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<result.getTypes().size();i++){
            String type = result.getTypes().get(i);
            Log.d(TAG, "onBindViewHolder: "+type);
            if(!type.equals("point_of_interest")&&!type.equals("establishment")){
                sb.append(type+", ");
            }
        }
        tvPlaceTypes.setText(sb.toString());

        tvPlaceAddress.setText(result.getFormatted_address());

        OpeningHours openingHours = result.getOpening_hours();
        if(openingHours!=null){
            if(openingHours.isOpen_now()){
                tvOpenOrClose.setText("Open");
            }else{
                tvOpenOrClose.setText("Close");
            }
        }else{
            tvOpenOrClose.setText("");
        }
        if(result.getReviews()!=null) {
            Review firstReview = result.getReviews().get(0);
            Picasso.with(ViewPlaceActivity.this)
                    .load(firstReview.getProfile_photo_url())
                    .error(R.drawable.error_image)
                    .into(ivReviewProfilePic);
            tvReviewPersonName.setText(firstReview.getAuthor_name());
            tvReviewTime.setText(firstReview.getRelative_time_description());
            tvReviewBody.setText(firstReview.getText());
        }
        tvPlaceUrl.setText(result.getWebsite());
        tvContactNo.setText(result.getInternational_phone_number());
    }
}
