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

import java.util.List;

public class ShowMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_map_activity);

        // Initialize the SupportMapFragment in the show_map_activity.xml
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        // Retrieve all adverts from the database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Advert> adverts = databaseHelper.getAllAdverts();

        // Create a LatLngBounds builder to include all the markers
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        // Loop through each advert and add a marker for its location
        for (Advert advert : adverts) {
            double latitude = advert.getLatitude();
            double longitude = advert.getLongitude();
            LatLng location = new LatLng(latitude, longitude);

            String status; //Was the item lost or found?
            if (advert.isLost()) {
                status = "Lost: ";
            } else {
                status = "Found: ";
            }

            googleMap.addMarker(new MarkerOptions().position(location).title(status + advert.getName()));

            // Include the location in the LatLngBounds builder
            builder.include(location);
        }

        // Get the LatLngBounds
        LatLngBounds bounds = builder.build();

        // Adjust the padding as needed (in pixels)
        int padding = 100;

        // Move and animate the camera to show all markers within the bounds
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
    }

}
