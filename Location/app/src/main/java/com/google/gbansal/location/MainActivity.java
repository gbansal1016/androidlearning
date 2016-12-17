package com.google.gbansal.location;

import android.location.Location;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener{

    private static final String TAG=MainActivity.class.getSimpleName();
    private GoogleApiClient gClient;

    private TextView locationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).
                addOnConnectionFailedListener(this).addConnectionCallbacks(this).build();

        locationView = (TextView) findViewById(R.id.location_output);
    }

    @Override
    protected void onStart() {
        super.onStart();
        gClient.connect();
        Log.i(TAG, "Google API Client connection connected");
    }

    @Override
    protected void onStop() {
        gClient.disconnect();
        super.onStop();
        Log.i(TAG, "Google API Client connection disconnected");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Google API Client connection failed");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "Google API Client connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Google API Client connection suspended");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "Location Changed");
        locationView.setText("Latitude:" + location.getLatitude()+ "Longitude:" + location.getLongitude());
    }
}
