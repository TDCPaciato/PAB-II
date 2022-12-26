package com.mnag.tokobuku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mnag.tokobuku.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyDatabaseHelper myDB;
    BookAdapter bookAdapter;
    ArrayList<Book> arrayBook;
    public static int dataPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDB = new MyDatabaseHelper(MainActivity.this);


        binding.fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });

    }


    private void getAllData() {
        Cursor cursor = myDB.getAllBook();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                Book book = new Book(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5));
                arrayBook.add(book);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrayBook = new ArrayList<>();
        getAllData();
        bookAdapter = new BookAdapter(MainActivity.this, arrayBook);
        binding.rvBuku.setAdapter(bookAdapter);
        binding.rvBuku.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.rvBuku.smoothScrollToPosition(dataPosition);
    }
}