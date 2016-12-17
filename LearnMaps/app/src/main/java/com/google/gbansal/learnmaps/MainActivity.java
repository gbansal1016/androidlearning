package com.google.gbansal.learnmaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap googleMap;
    private boolean mapReady;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MarkerOption
        //PolyLineOption

        Button mapButton = (Button) findViewById(R.id.btn_map);
        Button satButton = (Button) findViewById(R.id.btn_satellite);
        Button hybridButton = (Button) findViewById(R.id.btn_hybrid);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });

        satButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });

        hybridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });

        MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map) {

        mapReady = true;
        LatLng place = new LatLng(29.7857,76.3985);
        this.googleMap = map;

        CameraPosition target = CameraPosition.builder().target(place).zoom(14).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }
}
