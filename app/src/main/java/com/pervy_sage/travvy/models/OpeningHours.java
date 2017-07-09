package com.pervy_sage.travvy.models;

import java.util.ArrayList;

/**
 * Created by pervy_sage on 7/7/17.
 */

class OpeningHours {

    boolean open_now;
    ArrayList<String>weekday_text;

    public ArrayList<String> getWeekday_text() {
        return weekday_text;
    }

    public void setWeekday_text(ArrayList<String> weekday_text) {
        this.weekday_text = weekday_text;
    }

    public OpeningHours(boolean open_now, ArrayList<String>weekday_text) {
        this.open_now = open_now;
        this.weekday_text=weekday_text;

    }

    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }
}
