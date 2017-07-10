package com.pervy_sage.travvy.models;

/**
 * Created by pervy_sage on 10/7/17.
 */

public class Review {
    String author_name;
    String profile_photo_url;
    String relative_time_description;
    String text;

    public Review(String author_name,
                  String profile_photo_url,
                  String relative_time_description,
                  String text) {
        this.author_name = author_name;
        this.profile_photo_url = profile_photo_url;
        this.relative_time_description = relative_time_description;
        this.text = text;
    }

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
}
