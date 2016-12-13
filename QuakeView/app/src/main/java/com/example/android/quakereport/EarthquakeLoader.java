package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;

import com.example.android.quakereport.util.QueryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gbans6 on 12/12/2016.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeInfo>> {

    private String serverUrl;

    public EarthquakeLoader(Context context, String serverUrl) {
        super(context);
        this.serverUrl = serverUrl;
    }

    @Override
    public List<EarthquakeInfo> loadInBackground() {
        ArrayList<EarthquakeInfo> earthquakeInfos = null;
        if(TextUtils.isEmpty(serverUrl)) {
            return null;
        }

        earthquakeInfos = QueryUtils.extractEarthquakeInfos(serverUrl);

        return earthquakeInfos;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
