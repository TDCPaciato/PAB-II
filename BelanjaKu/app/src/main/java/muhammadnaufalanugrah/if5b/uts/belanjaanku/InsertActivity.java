package muhammadnaufalanugrah.if5b.uts.belanjaanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import muhammadnaufalanugrah.if5b.uts.belanjaanku.databinding.ActivityInsertBinding;

public class InsertActivity extends AppCompatActivity {
    private ActivityInsertBinding binding;
    private EditText etNama, etKategori, etHarga;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getNama = binding.etnama.getText().toString();
                String getKategori= binding.etkategori.getText().toString();
                String getHarga = binding.etharga.getText().toString();
                if(getNama.trim().equals("")) {
                    binding.etnama.setError("Nama tidak boleh kosong");
                }
                else if(getKategori.trim().equals("")) {
                    binding.etkategori.setError("Kategori tidak boleh kosong");
                }
                else if(getHarga.trim().equals("")) {
                    binding.etharga.setError("Harga tidak boleh kosong");
                }
                else {
                    Belanja belanja = new Belanja(getNama, getKategori, getHarga);
                    MyDataBaseHelper dB = new MyDataBaseHelper(InsertActivity.this);
                    long result = dB.addBelanja(belanja);

                    if(result == -1) {
                        Toast.makeText(InsertActivity.this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show();
                        binding.etnama.requestFocus();
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