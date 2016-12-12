package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by gbans6 on 12/11/2016.
 */

public class EarthquakeInfo {

    private String location;
    private String magnitude;
    private String date;

    public EarthquakeInfo(String location, String magnitude, String date) {
        this.location = location;
        this.magnitude = magnitude;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getDate() {
        return date;
    }
}
