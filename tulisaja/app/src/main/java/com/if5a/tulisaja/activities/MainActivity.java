package com.if5a.tulisaja.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.if5a.tulisaja.adapters.PostViewAdapter;
import com.if5a.tulisaja.databinding.ActivityMainBinding;
import com.if5a.tulisaja.models.post;
import com.if5a.tulisaja.models.ValueData;
import com.if5a.tulisaja.services.APIService;
import com.if5a.tulisaja.services.Utilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PostViewAdapter postAdapter;
    private List<post> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postAdapter = new PostViewAdapter();
        binding.rvPost.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPost.setAdapter(postAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllPost();
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void getAllPost() {
        showProgressBar();
        APIService apiService = Utilities.getRetrofit().create(APIService.class);
        apiService.getAll(Utilities.API_KEY).enqueue(new Callback<ValueData<post>>() {
            @Override
            public void onResponse(Call<ValueData<post>> call, Response<ValueData<post>> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    if (success == 1) {
                        data = response.body().getData();
                        postAdapter.setData(data);
                        hideProgressBar();
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ValueData<post>> call, Throwable t) {

            }
        });
    }
}