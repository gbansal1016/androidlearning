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

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.quakereport.util.QueryUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL="http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    private ArrayList<EarthquakeInfo> earthquakes = new ArrayList<>();

    private EarthquakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Find a reference to the {@link ListView} in the layout
        setContentView(R.layout.earthquake_activity);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        setHistory();

        // Create a new {@link ArrayAdapter} of earthquakes
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, earthquakes);
*/
        adapter = new EarthquakeAdapter(this,null,earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

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

        EarthquakeAsyncTask asyncTask = new EarthquakeAsyncTask();
        asyncTask.execute(USGS_REQUEST_URL);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String,Void, List<EarthquakeInfo>>{

        @Override
        protected List<EarthquakeInfo> doInBackground(String... params) {
            ArrayList<EarthquakeInfo> earthquakeInfos = null;
            if(params == null || params.length <1 || params[0]==null) {
            }
            String serverUrl = params[0];
            earthquakeInfos = QueryUtils.extractEarthquakeInfos(serverUrl);

            return earthquakeInfos;
        }

        @Override
        protected void onPostExecute(List<EarthquakeInfo> earthquakeInfos) {
            super.onPostExecute(earthquakeInfos);

            adapter.clear();
            if(earthquakeInfos != null && !earthquakeInfos.isEmpty()) {
                adapter.addAll(earthquakeInfos);
            }
        }
    }

    private void setHistory() {
        // Create a fake list of locations.
        earthquakes = QueryUtils.extractEarthquakeInfos("");
    }
}
