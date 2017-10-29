package com.pervy_sage.travvy.models;

/**
 * Created by pervy_sage on 7/7/17.
 */

public class Photo {
    int height;
    int width;
    String photo_reference;

    public Photo(int height, int width, String photo_reference) {
        this.height = height;
        this.width = width;
        this.photo_reference = photo_reference;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }
}
