/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthquakeInfo>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private static final String USGS_REQUEST_URL="http://earthquake.usgs.gov/fdsnws/event/1/query";

    private ArrayList<EarthquakeInfo> earthquakes = new ArrayList<>();

    private EarthquakeAdapter adapter;

    private TextView emptyView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Find a reference to the {@link ListView} in the layout
        setContentView(R.layout.earthquake_activity);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        emptyView = (TextView)findViewById(R.id.empty_view);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        adapter = new EarthquakeAdapter(this,R.layout.earthquake_layout,earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setEmptyView(emptyView);

        earthquakeListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        EarthquakeInfo info = earthquakes.get(position);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(info.getUrl()));
                        startActivity(intent);
                    }
                }
        );

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this).forceLoad();
        } else {
            progressBar.setVisibility(View.GONE);
            emptyView.setText(R.string.no_network);
        }
    }

    @Override
    public Loader<List<EarthquakeInfo>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "onCreateLoader callback");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String magnitude = preferences.getString
                (getString(R.string.settings_min_magnitude_key), getString(R.string.settings_min_magnitude_default));

        String orderBy = preferences.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );


        Uri baseURI = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder builder = baseURI.buildUpon();

        builder.appendQueryParameter("format", "geojson");
        builder.appendQueryParameter("orderby", orderBy);
        builder.appendQueryParameter("minmag", magnitude);
        builder.appendQueryParameter("limit", "50");

        return new EarthquakeLoader(this, builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeInfo>> loader, List<EarthquakeInfo> data) {

        progressBar.setVisibility(View.GONE);

        adapter.clear();
        if(data != null && !data.isEmpty()) {
            adapter.addAll(data);
        } else{
            emptyView.setText(R.string.no_earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<EarthquakeInfo>> loader) {
        Log.i(LOG_TAG, "onLoadReset callback");
        adapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings) {
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
