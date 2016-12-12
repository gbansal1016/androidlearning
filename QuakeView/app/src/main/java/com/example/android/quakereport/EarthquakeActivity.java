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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private ArrayList<EarthquakeInfo> earthquakes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        setContentView(R.layout.earthquake_activity);


        // Create a new {@link ArrayAdapter} of earthquakes
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, earthquakes);
*/
        EarthquakeAdapter adapter = new EarthquakeAdapter(this,null,earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }

    private void setHistory() {
        // Create a fake list of locations.
       earthquakes.add(new EarthquakeInfo("San Francisco", String.valueOf(7), (new Date()).toString()));
        earthquakes.add(new EarthquakeInfo("London", String.valueOf(7), (new Date()).toString()));
        earthquakes.add(new EarthquakeInfo("Tokyo",  String.valueOf(7), (new Date()).toString()));
        earthquakes.add(new EarthquakeInfo("Mexico City", String.valueOf(7), (new Date()).toString()));
        earthquakes.add(new EarthquakeInfo("Moscow", String.valueOf(7), (new Date()).toString()));
        earthquakes.add(new EarthquakeInfo("Rio de Janeiro", String.valueOf(7), (new Date()).toString()));
        earthquakes.add(new EarthquakeInfo("Paris", String.valueOf(7), (new Date()).toString()));

    }
}
