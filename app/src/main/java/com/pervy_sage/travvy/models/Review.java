package com.pervy_sage.travvy.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pervy_sage on 10/7/17.
 */

public class Review implements Parcelable {
    String author_name;
    String profile_photo_url;
    String relative_time_description;
    String text;
    double rating;

    public double getRating() {
        return rating;
    }

    public Review(String author_name,
                  String profile_photo_url,
                  String relative_time_description,
                  String text,
                  double rating) {
        this.author_name = author_name;
        this.profile_photo_url = profile_photo_url;
        this.relative_time_description = relative_time_description;
        this.text = text;
        this.rating=rating;

    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getAuthor_name() {
        return author_name;
    }

    public String getProfile_photo_url() {
        return profile_photo_url;
    }

    public String getRelative_time_description() {
        return relative_time_description;
    }

    public String getText() {
        return text;
    }

    private Review(Parcel in){

        author_name = in.readString();
        profile_photo_url = in.readString();
        relative_time_description = in.readString();
        text = in.readString();
        rating=in.readDouble();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author_name);
        dest.writeString(profile_photo_url);
        dest.writeString(relative_time_description);
        dest.writeString(text);
        dest.writeDouble(rating);
    }
}
