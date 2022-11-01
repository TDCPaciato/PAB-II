package rizky_kurniawan.if5b.uts.belanjaanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import rizky_kurniawan.if5b.uts.belanjaanku.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    AdapterBelanjaKu adapterBelanjaKu;
    DatabaseHelper databaseHelper;
    ArrayList<String> arrId, arrNama, arrKategori, arrHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(MainActivity.this);
        binding.fabInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityInsert.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrId = new ArrayList<>();
        arrNama = new ArrayList<>();
        arrKategori = new ArrayList<>();
        arrHarga = new ArrayList<>();

        getAllBelanjaKu();
        adapterBelanjaKu = new AdapterBelanjaKu(MainActivity.this, arrId, arrNama, arrKategori, arrHarga);
        binding.rvBelanjaku.setAdapter(adapterBelanjaKu);
        binding.rvBelanjaku.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void getAllBelanjaKu() {
        Cursor cursor = databaseHelper.getBelanjaKu();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "TIDAK ADA BELANJA", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                arrId.add(cursor.getString(0));
                arrNama.add(cursor.getString(1));
                arrKategori.add(cursor.getString(2));
                arrHarga.add(cursor.getString(3));
            }
        }
    }
}