package com.if5b.contact.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.if5b.contact.databinding.ActivityInputBinding;

public class InputActivity extends AppCompatActivity {
    private ActivityInputBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}