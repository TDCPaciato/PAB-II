package com.if5b.peta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.if5b.peta.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityMainBinding binding;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment)  getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMyLocationEnabled(true);
        LatLng latLngUser = new LatLng(-2.9405826,104.7782357);
        mMap.addMarker(new MarkerOptions().position(latLngUser).title("Lokasi Saya"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngUser, 100));
    }
}