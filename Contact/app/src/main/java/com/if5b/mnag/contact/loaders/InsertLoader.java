package com.if5b.mnag.contact.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.if5b.mnag.contact.databases.UserDatabase;
import com.if5b.mnag.contact.entities.User;

public class InsertLoader extends AsyncTaskLoader<Boolean> {
    private UserDatabase db;
    private User user;

    public InsertLoader(@NonNull Context context, User user) {
        super(context);
        this.user = user;
        db = UserDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public Boolean loadInBackground() {
        db.userDao().insertUser(user);
        return true;
    }
}
