package com.example.android.quakereport;

/**
 * Created by gbans6 on 12/11/2016.
 */

public class EarthquakeInfo {

    private static final String LOCATION_SEPERATOR = " of ";
    private static final String NEAR = "Near the";

    private String location;
    private Double magnitude;
    private long millisecond;
    private String primaryLocation;
    private String offset;
    private String url;

    public EarthquakeInfo(String location, Double magnitude, long millisecond, String url) {
        this.location = location;
        this.magnitude = magnitude;
        this.millisecond = millisecond;
        this.url = url;
        splitLocation();
    }

    private void splitLocation() {
        if(location == null || location.length() == 0) {
            return;
        }

        if(location.contains(LOCATION_SEPERATOR)) {
            String [] parts = location.split(LOCATION_SEPERATOR);
            offset = parts[0];
            primaryLocation = parts[1];
        } else {
            offset = NEAR;
            primaryLocation = location;
        }

    }

    public String getLocation() {
        return location;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public long getMillisecond() {
        return millisecond;
    }

    public String getPrimaryLocation() {
        return primaryLocation;
    }

    public String getOffset() {
        return offset;
    }

    public String getUrl() {
        return url;
    }
}
