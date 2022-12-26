package com.if5b.mnag.tulisaja.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.if5b.mnag.tulisaja.R;
import com.if5b.mnag.tulisaja.adapters.PostViewAdapter;
import com.if5b.mnag.tulisaja.databinding.ActivityMainBinding;
import com.if5b.mnag.tulisaja.models.Post;
import com.if5b.mnag.tulisaja.models.ValueData;
import com.if5b.mnag.tulisaja.services.APIService;
import com.if5b.mnag.tulisaja.services.Utilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static final String KEY_USERNAME = "xUsername";
    public static final String KEY_API = "dirumahaja";
    private PostViewAdapter postViewAdapter;
    private List<Post> data = new ArrayList<>();
    private ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK) {
                getAllPost();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(!Utilities.checkValue(MainActivity.this, "xUsername")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        postViewAdapter = new PostViewAdapter();
        binding.rvPost.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPost.setAdapter(postViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllPost();
    }

    private void showProgressBar() {
        binding.proggressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.proggressBar.setVisibility(View.GONE);
    }

    private void getAllPost() {
        showProgressBar();
        APIService api = Utilities.getRetrofit().create(APIService.class);
        api.getAllPost(Utilities.API_KEY).enqueue(new Callback<ValueData<Post>>() {
            @Override
            public void onResponse(Call<ValueData<Post>> call, Response<ValueData<Post>> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1) {
                        data = response.body().getData();
                        postViewAdapter.setData(data);
                    } else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Response code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ValueData<Post>> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(MainActivity.this, "Retrofit error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_logout){
            Utilities.clearUser(this);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if(id == R.id.action_refresh){
            getAllPost();
        }
        return super.onOptionsItemSelected(item);
    }
}