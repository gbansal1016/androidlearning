package com.google.gbansal.location;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private GoogleApiClient gClient;

    private Location mLastLocation;

    private TextView locationView;

    private LocationRequest mLocationRequest;

    private final int PERMISSION_REQUEST_LOCATION_ACCESS=1;

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
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Google API Client connected");

        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(2000);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION_ACCESS);
        }


   /*     Log.e(TAG, "Issue with Permission");
        int retry =0;
        while((mLastLocation == null) && retry<=100) {
            retry++;
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(gClient);
            if (mLastLocation != null) {
                locationView.setText("Latitude:" + mLastLocation.getLatitude() + "Longitude:" + mLastLocation.getLongitude());
            }
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_LOCATION_ACCESS:
                //Permission granted
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Log.i(TAG,"Permission granted for locations");
                LocationServices.FusedLocationApi.requestLocationUpdates(gClient, mLocationRequest, this);
                break;
            default:
                Log.e(TAG, "Permission to access location is denied");
                break;
        }
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
