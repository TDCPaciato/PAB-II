package com.if5b.buku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UbahActivity extends AppCompatActivity {

    private EditText etJudul, etPenulis, etTahun;
    private Button btnUbah;
    private String getID, getJudul, getPenulis, getTahun;
    private int getPosisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etJudul = findViewById(R.id.et_judul);
        etPenulis = findViewById(R.id.et_penulis);
        etTahun = findViewById(R.id.et_tahun_terbit);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent terima = getIntent();
        getID = terima.getStringExtra("varID");
        getJudul = terima.getStringExtra("varJudul");
        getPenulis = terima.getStringExtra("varPenulis");
        getTahun = terima.getStringExtra("varTahun");
        getPosisi = terima.getIntExtra("varPosisi", -1);

        etJudul.setText(getJudul);
        etPenulis.setText(getPenulis);
        etTahun.setText(getTahun);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtJudul = etJudul.getText().toString();
                String txtPenulis = etPenulis.getText().toString();
                String txtTahun = etTahun.getText().toString();

                if (txtJudul.trim().equals("")) {
                    etJudul.setError("Judul Tidak Boleh Kosong");
                }
                else if (txtPenulis.trim().equals("")) {
                    etPenulis.setError("Penulis Tidak Boleh Kosong");
                }
                else if (txtTahun.trim().equals("")) {
                    etTahun.setError("Tahun Terbit Tidak Boleh Kosong");
                }
                else {
                    // video 3 menit 36:41
                }
            }
        });
    }
}