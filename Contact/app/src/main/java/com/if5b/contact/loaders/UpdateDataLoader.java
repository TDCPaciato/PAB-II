package com.if5b.contact.loaders;

import android.content.Context;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.if5b.contact.activities.InputActivity;
import com.if5b.contact.databases.UserDatabase;
import com.if5b.contact.entities.User;

public class UpdateDataLoader extends AsyncTaskLoader<Integer> {
    private UserDatabase db;
    private User user;

    public UpdateDataLoader(@NonNull Context context) {
        super(context);
        this.user = user;
        db = UserDatabase.getInstance(context);
    }

    public UpdateDataLoader(InputActivity context, Parcelable user) {
        super(context);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        return db.UserDao().updateUser(user);
    }
}
