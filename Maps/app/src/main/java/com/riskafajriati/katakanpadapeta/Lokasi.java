package com.riskafajriati.katakanpadapeta;

import com.google.android.gms.maps.model.LatLng;

public class Lokasi {
    private String nama;
    private LatLng latLng;

    public Lokasi(String nama) {
        this.nama = nama;
    }

    public Lokasi(String nama, LatLng latLng) {
        this.nama = nama;
        this.latLng = latLng;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
