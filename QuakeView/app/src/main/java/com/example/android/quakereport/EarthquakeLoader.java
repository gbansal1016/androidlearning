package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.quakereport.util.QueryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gbans6 on 12/12/2016.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeInfo>> {

    private String serverUrl;
    private String LOG_TAG = EarthquakeLoader.class.getSimpleName();

    public EarthquakeLoader(Context context, String serverUrl) {
        super(context);
        this.serverUrl = serverUrl;
    }

    @Override
    public List<EarthquakeInfo> loadInBackground() {
        Log.i(LOG_TAG, "loadInBackground inside AsyncTaskLoader");
        ArrayList<EarthquakeInfo> earthquakeInfos = null;
        if(TextUtils.isEmpty(serverUrl)) {
            return null;
        }

        earthquakeInfos = QueryUtils.extractEarthquakeInfos(serverUrl);

        return earthquakeInfos;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "onStartLoading inside AsyncTaskLoader");
        forceLoad();
    }
}
