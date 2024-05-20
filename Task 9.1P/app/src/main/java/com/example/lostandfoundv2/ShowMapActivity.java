package com.example.lostandfoundv2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_map_activity);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng location1 = new LatLng(55.6761, 12.5683); // Copenhagen
        LatLng location2 = new LatLng(48.8566, 2.3522);  // Paris
        LatLng location3 = new LatLng(51.5074, -0.1278); // London

        googleMap.addMarker(new MarkerOptions().position(location1).title("Copenhagen"));
        googleMap.addMarker(new MarkerOptions().position(location2).title("Paris"));
        googleMap.addMarker(new MarkerOptions().position(location3).title("London"));

        // Create a LatLngBounds builder to include all the markers
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(location1);
        builder.include(location2);
        builder.include(location3);

        // Get the LatLngBounds
        LatLngBounds bounds = builder.build();

        // Adjust the padding as needed (in pixels)
        int padding = 100; // Offset from edges of the map in pixels

        // Move and animate the camera to show all markers within the bounds
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
    }

}
