package com.if5b.myapplication.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.if5b.myapplication.adapters.PostAdapter;
import com.if5b.myapplication.databinding.ActivityMainBinding;
import com.if5b.myapplication.model.Post;
import com.if5b.myapplication.model.ValueNoData;
import com.if5b.myapplication.model.ValueWithData;
import com.if5b.myapplication.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static final String KEY_USERNAME = "xUsername";
    public static final String KEY_API = "dirumahaja";
    private PostAdapter postAdapter;
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

        if(!ApiService.checkValue(MainActivity.this, KEY_USERNAME)) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }


        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService.deleteValue(MainActivity.this);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });

    }

    private void loadAdapter(List<Post> data) {
        postAdapter = new PostAdapter();
        binding.rvPost.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPost.setAdapter(postAdapter);
        postAdapter.setData(data);
        postAdapter.setOnClickListener(new PostAdapter.OnClickListener() {
            @Override
            public void onEditClicked(Post post) {
                gotoUpdatePostActivity(post);
            }

            @Override
            public void onDeleteClicked(int id) {
                deletePost(id);
            }
        });
    }

    private void gotoUpdatePostActivity(Post post) {
        Intent intent = new Intent(MainActivity.this, InsertActivity.class);
        intent.putExtra("edit", true);
        intent.putExtra("post", post);
        intentActivityResultLauncher.launch(intent);
    }

    private void deletePost(int id) {
        ApiService.endpoint().deletePost(KEY_API, id).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if(response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if(success == 1) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        getAllPost();
                    }
                    else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Response code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllPost();
    }


    private void getAllPost() {
        ApiService.endpoint().getAllPost(KEY_API).enqueue(new Callback<ValueWithData<Post>>() {
            @Override
            public void onResponse(Call<ValueWithData<Post>> call, Response<ValueWithData<Post>> response) {
                if(response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if(success == 1) {
                        data = response.body().getData();
                        loadAdapter(data);
                    }
                    else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Response code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueWithData<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}