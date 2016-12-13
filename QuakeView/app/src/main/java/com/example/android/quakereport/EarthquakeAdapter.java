package com.example.android.quakereport;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by gbans6 on 12/11/2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeInfo> {

    public EarthquakeAdapter(Context context, Integer resource, List<EarthquakeInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View listItemView = contentView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_layout, parent, false);
        }

        EarthquakeInfo earthquakeInfo = getItem(position);

        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitude.setText(formatDecimal(earthquakeInfo.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magColor = getMagnitudeColor(earthquakeInfo.getMagnitude());
        magnitudeCircle.setColor(magColor);

        TextView location = (TextView) listItemView.findViewById(R.id.location);
        location.setText(earthquakeInfo.getPrimaryLocation());

        TextView offset = (TextView) listItemView.findViewById(R.id.offset);
        offset.setText(earthquakeInfo.getOffset());

        Date date = new Date(earthquakeInfo.getMillisecond());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(formatDate(date));

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        timeView.setText(formatTime(date));


        return  listItemView;
    }

    private int getMagnitudeColor(Double magnitude) {
        int magnitudeResourceId;

        int mag = (int) Math.floor(magnitude);
        switch (mag) {
            case    0:
            case    1:
                magnitudeResourceId = R.color.magnitude1;
                break;
            case    2:
                magnitudeResourceId = R.color.magnitude2;
                break;
            case    3:
                magnitudeResourceId = R.color.magnitude3;
                break;
            case    4:
                magnitudeResourceId = R.color.magnitude4;
                break;
            case    5:
                magnitudeResourceId = R.color.magnitude5;
                break;
            case    6:
                magnitudeResourceId = R.color.magnitude6;
                break;
            case    7:
                magnitudeResourceId = R.color.magnitude7;
                break;
            case    8:
                magnitudeResourceId = R.color.magnitude8;
                break;
            case    9:
                magnitudeResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(),magnitudeResourceId);
    }

    private String formatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("LLL dd, yyyy");
        return format.format(date);
    }

    private String formatTime(Date date){
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        return format.format(date);
    }

    private String formatDecimal(Double mag) {
        DecimalFormat formatter = new DecimalFormat("0.00");
        return  formatter.format(mag);
    }

}
