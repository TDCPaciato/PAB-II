package com.if5b.mnag.contact.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.if5b.mnag.contact.databases.UserDatabase;
import com.if5b.mnag.contact.entities.User;

public class UpdateDataLoader extends AsyncTaskLoader<Integer> {
    private UserDatabase db;
    private User user;

    public UpdateDataLoader(@NonNull Context context, User user) {
        super(context);
        this.user = user;
        db = UserDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        return db.userDao().updateUser(user);
    }
}
