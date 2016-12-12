package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.List;

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
        magnitude.setText(earthquakeInfo.getMagnitude());

        TextView location = (TextView) listItemView.findViewById(R.id.location);
        location.setText(earthquakeInfo.getLocation());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(earthquakeInfo.getDate());

        return  listItemView;
    }
}
