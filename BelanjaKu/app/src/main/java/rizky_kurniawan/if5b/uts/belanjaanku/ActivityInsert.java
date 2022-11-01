package rizky_kurniawan.if5b.uts.belanjaanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import rizky_kurniawan.if5b.uts.belanjaanku.databinding.ActivityInsertBinding;

public class ActivityInsert extends AppCompatActivity {
    ActivityInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getNama = binding.etNama.getText().toString();
                String getKategori = binding.etKategori.getText().toString();
                String getHarga = binding.etHarga.getText().toString();

                if (getNama.trim().equals("")) {
                    binding.etNama.setError("Nama Tidak Boleh Kosong!");
                }

                else if (getKategori.trim().equals("")) {
                    binding.etNama.setError("Kategori Tidak Boleh Kosong!");
                }

                else if (getHarga.trim().equals("")) {
                    binding.etNama.setError("Harga Tidak Boleh Kosong!");
                }

                else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(ActivityInsert.this);
                    long hasil = databaseHelper.insertBelanjaKu(getNama, getKategori, getHarga);

                    if (hasil == -1) {
                        Toast.makeText(ActivityInsert.this, "GAGAL MENAMBAH DATA", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        Toast.makeText(ActivityInsert.this, "TAMBAH DATA BERHASIL", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}