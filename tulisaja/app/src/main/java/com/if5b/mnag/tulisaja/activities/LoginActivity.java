package com.if5b.mnag.tulisaja.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.if5b.mnag.tulisaja.databinding.ActivityLoginBinding;
import com.if5b.mnag.tulisaja.models.ValueNoData;
import com.if5b.mnag.tulisaja.services.APIService;
import com.if5b.mnag.tulisaja.services.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();

                boolean bolehLogin = true;

                if(TextUtils.isEmpty(username)){
                    bolehLogin = false;
                    binding.etUsername.setError("Username tidak boleh kosong !");
                }
                if(TextUtils.isEmpty(password)){
                    bolehLogin = false;
                    binding.etPassword.setError("Password tidak boleh kosong !");
                }
                if(bolehLogin){
                    login(username,password);
                }
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void login(String username, String password) {
        showProgressBar();
        APIService api = Utilities.getRetrofit().create(APIService.class);
        api.login(Utilities.API_KEY, username, password).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if(response.code() == 200){
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if(success == 1){
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        Utilities.setValue(LoginActivity.this, "xUsername", username);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Response code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(LoginActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressBar(){
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        binding.progressBar.setVisibility(View.GONE);
    }
}