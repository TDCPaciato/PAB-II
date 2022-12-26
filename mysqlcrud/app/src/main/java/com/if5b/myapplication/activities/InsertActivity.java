package com.if5b.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.if5b.myapplication.databinding.ActivityInsertBinding;
import com.if5b.myapplication.model.Post;
import com.if5b.myapplication.model.ValueNoData;
import com.if5b.myapplication.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    private ActivityInsertBinding binding;
    private Boolean isEdit;
    private int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        isEdit = getIntent().getBooleanExtra("edit", false);
        if (isEdit) {
            Post post = getIntent().getParcelableExtra("post");
            postId = Integer.parseInt(post.getId());
            setContent(post);
        }

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    String username = ApiService.getValue(InsertActivity.this, MainActivity.KEY_USERNAME);
                    String content = binding.etContent.getText().toString();
                    if (isEdit) {
                        editPost(postId, content);
                    } else {
                        addPost(username, content);
                    }
                }
            }
        });
    }

    private void setContent(Post post) {
        binding.etContent.setText(post.getContent());
        binding.btnAdd.setText("Update");
    }

    private boolean validateFields() {
        if (TextUtils.isEmpty(binding.etContent.getText().toString())) {
            Toast.makeText(this, "Content tidak boleh kosong yaaaaa", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void editPost(int postId, String content) {
        ApiService.endpoint().updatePost(MainActivity.KEY_API, postId, content).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200) {
                    if (response.body().getSuccess() == 1) {
                        Toast.makeText(InsertActivity.this, "Berhasil edit data", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(InsertActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(InsertActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(InsertActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addPost(String username, String content) {
        ApiService.endpoint().insertPost(MainActivity.KEY_API, username, content).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200) {
                    if (response.body().getSuccess() == 1) {
                        Toast.makeText(InsertActivity.this, "Berhasil masukan data", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(InsertActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(InsertActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(InsertActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}