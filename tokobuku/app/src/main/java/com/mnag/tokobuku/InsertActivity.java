package com.mnag.tokobuku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mnag.tokobuku.databinding.ActivityInsertBinding;


public class InsertActivity extends AppCompatActivity {

    private ActivityInsertBinding binding;
    private EditText etIsbn, etJudul, etKategori, etDeskripsi, etHarga;
    private Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getIsbn = binding.etIsbn.getText().toString();
                String getJudul= binding.etJudul.getText().toString();
                String getKategori = binding.etKategori.getText().toString();
                String getDesc = binding.etDeskripsi.getText().toString();
                String getHarga = binding.etHarga.getText().toString();
                if(getIsbn.trim().equals("")) {
                    binding.etIsbn.setError("ISBN tidak boleh kosong");
                }
                else if(getJudul.trim().equals("")) {
                    binding.etJudul.setError("Judul tidak boleh kosong");
                }
                else if(getKategori.trim().equals("")) {
                    binding.etKategori.setError("Kategori tidak boleh kosong");
                }
                else if(getDesc.trim().equals("")) {
                    binding.etDeskripsi.setError("Deskripsi tidak boleh kosong");
                }
                else if(getHarga.trim().equals("")) {
                    binding.etHarga.setError("Harga tidak boleh kosong");
                }
                else {
                    Book book = new Book(getIsbn, getJudul, getKategori, getDesc, getHarga);
                    MyDatabaseHelper dB = new MyDatabaseHelper(InsertActivity.this);
                    long result = dB.addBook(book);

                    if(result == -1) {
                        Toast.makeText(InsertActivity.this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show();
                        binding.etJudul.requestFocus();
                    }
                    else {
                        Toast.makeText(InsertActivity.this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                }
            }
        });
    }
}