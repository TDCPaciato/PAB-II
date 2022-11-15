package com.if5b.contact.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.if5b.contact.R;
import com.if5b.contact.adapters.UserViewAdapter;
import com.if5b.contact.databinding.ActivityMainBinding;
import com.if5b.contact.entities.User;
import com.if5b.contact.loaders.DeleteLoader;
import com.if5b.contact.loaders.getDataLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int GETDATA_LOADER = 123;
    private static final int DELETE_LOADER_CODE = 124;
    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                getData();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fabInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        showProgressBar();
        LoaderManager.getInstance(this).restartLoader(GETDATA_LOADER, null, new LoaderManager.LoaderCallbacks<List<User>>() {
            @NonNull
            @Override
            public Loader<List<User>> onCreateLoader(int id, @Nullable Bundle args) {
                return new getDataLoader(MainActivity.this);
            }

            @Override
            public void onLoadFinished(@NonNull Loader<List<User>> loader, List<User> data) {
                hideProgressBar();
                initAdapter(data);
            }

            @Override
            public void onLoaderReset(@NonNull Loader<List<User>> loader) {

            }
        }).forceLoad();
    }

    private void initAdapter(List<User> data) {
        UserViewAdapter userViewAdapter = new UserViewAdapter();
        binding.rvUser.setLayoutManager(new LinearLayoutManager(this));
        binding.rvUser.setAdapter(userViewAdapter);
        userViewAdapter.setData(data);
        userViewAdapter.setOnClickListener(new UserViewAdapter.onClickListener() {
            @Override
            public void onEditClicked(User user) {
                gotoUpdateUserActivity(user);
            }

            @Override
            public void onDeleteClicked(int userId) {
                deleteUser(userId);
            }
        });
    }

    private void gotoUpdateUserActivity(User user) {
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra("edit", true);
        intent.putExtra("user", user);
        intentActivityResultLauncher.launch(intent);
    }

    private void deleteUser(int userId) {
        showProgressBar();
        
        Bundle args = new Bundle();
        args. putInt("id", userId);
        LoaderManager.getInstance(this).restartLoader(DELETE_LOADER_CODE, args, new LoaderManager.LoaderCallbacks<Integer>() {
            @NonNull
            @Override
            public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
                return new DeleteLoader(MainActivity.this, args.getInt("id"));
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
                hideProgressBar();
                if (data != -1) {
                    itemDeleted();
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Integer> loader) {

            }
        }).forceLoad();
    }

    private void itemDeleted() {
        Toast.makeText(this, "User Deleted!", Toast.LENGTH_SHORT).show();
        getData();
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }
}