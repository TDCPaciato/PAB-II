package com.if5b.mnag.contact.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.if5b.mnag.contact.dao.UserDao;
import com.if5b.mnag.contact.entities.User;

@Database(entities = User.class, exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DB_NAME = "user_db";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}
