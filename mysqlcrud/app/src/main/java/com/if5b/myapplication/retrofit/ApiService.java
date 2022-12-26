package com.if5b.myapplication.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.MasterKeys;

import com.if5b.myapplication.activities.MainActivity;

import java.io.IOException;
import java.security.GeneralSecurityException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static final String BASE_URL = "https://restapi.mdp.ac.id/tulisaja/";
    private static final String PREFERENCE_FILE_KEY = "badutkelasb";
    private static Retrofit retrofit;

    public static ApiEndpoint endpoint() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndpoint.class);
    }

    public static void deleteValue(Context context) {
        SharedPreferences sharedPreferences = null;
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            sharedPreferences = EncryptedSharedPreferences.create(context,
                    PREFERENCE_FILE_KEY,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MainActivity.KEY_USERNAME, null);
        editor.apply();
    }

    public static void setValue(Context context, String xKey, String xValue) {
        SharedPreferences sharedPreferences = null;
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            sharedPreferences = EncryptedSharedPreferences.create(context,
                    PREFERENCE_FILE_KEY,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(xKey, xValue);
        editor.commit();
    }

    public static String getValue(Context context, String xKey) {
        SharedPreferences sharedPreferences = null;
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            sharedPreferences = EncryptedSharedPreferences.create(context,
                    PREFERENCE_FILE_KEY,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String xValue = sharedPreferences.getString(xKey, null);
        return xValue;
    }

    public static boolean checkValue(Context context, String xKey) {
        SharedPreferences sharedPreferences = null;
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            sharedPreferences = EncryptedSharedPreferences.create(context,
                    PREFERENCE_FILE_KEY,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String xValue = sharedPreferences.getString(xKey, null);
        if(xValue != null) {
            return true;
        }
        else {
            return false;
        }
    }

}
