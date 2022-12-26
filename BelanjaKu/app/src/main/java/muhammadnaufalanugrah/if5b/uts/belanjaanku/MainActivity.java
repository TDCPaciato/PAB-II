package muhammadnaufalanugrah.if5b.uts.belanjaanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import muhammadnaufalanugrah.if5b.uts.belanjaanku.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyDataBaseHelper myDB;
    BelanjaAdapter belanjaAdapter;
    ArrayList<Belanja> arraybelanja;
    public static int dataPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDB = new MyDataBaseHelper(MainActivity.this);

        binding.fabtambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
    }

    private void getAllData(){
        Cursor cursor = myDB.getAllBelanja();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Tidak ada Data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                Belanja belanja = new Belanja(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3));
                arraybelanja.add(belanja);
            }
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        arraybelanja = new ArrayList<>();
        getAllData();
        belanjaAdapter = new BelanjaAdapter(MainActivity.this, arraybelanja);
        binding.rvBelanja.setAdapter(belanjaAdapter);
        binding.rvBelanja.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.rvBelanja.smoothScrollToPosition(dataPosition);
    }
}