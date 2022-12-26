package com.if5b.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.if5b.myapplication.databinding.ActivityRegisterBinding;
import com.if5b.myapplication.model.ValueNoData;
import com.if5b.myapplication.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                String konfirmasiPassword = binding.etKonfirmasiPassword.getText().toString();
                Boolean isRegister = true;

                if(TextUtils.isEmpty(username)) {
                    isRegister = false;
                    binding.etUsername.setError("Silahkan isi username");
                }
                if(TextUtils.isEmpty(password)) {
                    isRegister = false;
                    binding.etPassword.setError("Silahkan isi password");
                }
                if(TextUtils.isEmpty(konfirmasiPassword)) {
                    isRegister = false;
                    binding.etKonfirmasiPassword.setError("Silahkan isi konfirmasi password");
                }
                if(!password.equals(konfirmasiPassword)) {
                    isRegister = false;
                    binding.etKonfirmasiPassword.setError("Password atau konfirmasi password salah");
                }
                if(isRegister) {
                    ApiService.endpoint().register(MainActivity.KEY_API, username, password)
                            .enqueue(new Callback<ValueNoData>() {
                                @Override
                                public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                                    if (response.code() == 200) {
                                        if(response.body().getSuccess() == 1) {
                                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            ApiService.setValue(RegisterActivity.this, "xUsername", username);
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ValueNoData> call, Throwable t) {
                                    System.out.println("Retrofit Error : " + t.getMessage());
                                }
                            });
                }
            }
        });
    }
}