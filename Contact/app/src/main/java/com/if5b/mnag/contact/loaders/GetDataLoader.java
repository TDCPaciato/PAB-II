package com.if5b.mnag.contact.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.if5b.mnag.contact.databases.UserDatabase;
import com.if5b.mnag.contact.entities.User;

import java.util.List;

public class GetDataLoader extends AsyncTaskLoader<List<User>> {
    private UserDatabase db;

    public GetDataLoader(@NonNull Context context) {
        super(context);
        db = UserDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public List<User> loadInBackground() {
        return db.userDao().getAllUsers();
    }
}
