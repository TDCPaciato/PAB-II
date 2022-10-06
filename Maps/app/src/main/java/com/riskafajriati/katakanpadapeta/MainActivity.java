package com.riskafajriati.katakanpadapeta;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.riskafajriati.katakanpadapeta.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivityMainBinding binding;
    private GoogleMap mMap;
    private List<Lokasi> restaurantList = new ArrayList<>();
    private List<Lokasi> hospitalList = new ArrayList<>();
    private ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
        Boolean fineLocationGranted = result.getOrDefault(
                Manifest.permission.ACCESS_FINE_LOCATION, false);
        Boolean coarseLOcationGranted = result.getOrDefault(
                Manifest.permission.ACCESS_COARSE_LOCATION, false);
        if (fineLocationGranted != null && fineLocationGranted) {
            // Precise location access granted
        } else if (coarseLOcationGranted != null && coarseLOcationGranted) {
            //only approximate location access granted
        } else {
            // No loaction access granted
        }
    });

    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        restaurantList.add(new Lokasi("Belido Restaurant", new LatLng(-2.9544493129715845, 104.74733438938732)));
        restaurantList.add(new Lokasi("Pecel Lele Shakila", new LatLng(-2.9536211329513455, 104.750980246199)));
        restaurantList.add(new Lokasi("RM Pindang Raja Pegagan", new LatLng(-2.9539454876745976, 104.75165688415095)));
        restaurantList.add(new Lokasi("Warung Nasi Sederhana", new LatLng(-2.9533418274192895, 104.7515756875967)));
        restaurantList.add(new Lokasi("Pecel Lele Gareng", new LatLng(-2.95213450589559, 104.75816163063487)));
        restaurantList.add(new Lokasi("Wakcoy Cafe", new LatLng(-2.9514672237008726, 104.7507922089833)));
        restaurantList.add(new Lokasi("Louise Bistro", new LatLng(-2.9652438844255937, 104.74668660401602)));
        restaurantList.add(new Lokasi("City Restaurant", new LatLng(-2.9617781296133296, 104.74058789372982)));
        restaurantList.add(new Lokasi("Restaurant Jenny", new LatLng(-2.971487312112279, 104.74176170429034)));
        restaurantList.add(new Lokasi("Picasso Restaurant", new LatLng(-2.9713598955654072, 104.7510501164426)));

        hospitalList.add(new Lokasi("RS Charitas", new LatLng(-2.975074910333812, 104.75254548292726)));
        hospitalList.add(new Lokasi("RS Hermina", new LatLng(-2.955810433692043, 104.74849139202414)));
        hospitalList.add(new Lokasi("RS Bhayangkara", new LatLng(-2.9583548472197694, 104.73762553107822)));
        hospitalList.add(new Lokasi("RS Musi", new LatLng(-2.979691839886026, 104.72376213902504)));
        hospitalList.add(new Lokasi("RS Muhammad Hoesin", new LatLng(-2.966201144158307, 104.74912754539834)));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLngLast = new LatLng(latitude, longitude);

                    mMap.clear();
                    mMap.addMarker(new MarkerOptions()
                            .position(latLngLast)
                            .title("I'm here")
                            .snippet("Help me!"))
                            .showInfoWindow();

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngLast, 17));
                }
            }
        });

                binding.fabRestaurant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mMap.clear();

                        for (int i = 0; i < restaurantList.size(); i++) {
                            mMap.addMarker(new MarkerOptions()
                                            .position(restaurantList.get(i).getLatLng())
                                            .title(restaurantList.get(i).getNama())
                                            .snippet("Enak, murah, banyak"))
                                    .showInfoWindow();
                        }
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(restaurantList.get(4).getLatLng(), 17));
                    }
                });

        binding.fabHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();

                for (int i=0; i<hospitalList.size(); i++){
                    mMap.addMarker(new MarkerOptions()
                                    .position(hospitalList.get(i).getLatLng())
                                    .title(hospitalList.get(i).getNama())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
                                    .showInfoWindow();
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospitalList.get(3).getLatLng(), 17));
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMyLocationEnabled(true);
//        LatLng latLngUser = new LatLng(-2.962633, 104.740048);
//        mMap.addMarker(new MarkerOptions().position(latLngUser).title("Lokasi saya"));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngUser, 19));
//
//        mMap.addCircle(new CircleOptions()
//                .center(latLngUser)
//                .radius(100)
//                .strokeColor(Color.TRANSPARENT)
//                .fillColor(R.color.purple_500));
    }
}