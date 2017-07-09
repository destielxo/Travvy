package com.pervy_sage.travvy.models;

import java.util.ArrayList;

/**
 * Created by pervy_sage on 7/7/17.
 */

public class Results {

    Geometry geometry;
    String icon;
    String name;
    ArrayList<Photo> photos;
    String placi_id;
    ArrayList<String>types;
    OpeningHours opening_hours;

    public Results(Geometry geometry,
                   String icon,
                   String name,
                   ArrayList<Photo> photos,
                   String placi_id,
                   ArrayList<String> types,
                   OpeningHours opening_hours) {
        this.geometry = geometry;
        this.icon = icon;
        this.name = name;
        this.photos = photos;
        this.placi_id = placi_id;
        this.types = types;
        this.opening_hours = opening_hours;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public void setPlaci_id(String placi_id) {
        this.placi_id = placi_id;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public Geometry getGeometry() {

        return geometry;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public String getPlaci_id() {
        return placi_id;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }
}
