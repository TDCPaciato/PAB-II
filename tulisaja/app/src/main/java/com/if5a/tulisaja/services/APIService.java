package com.if5a.tulisaja.services;

import com.if5a.tulisaja.models.ValueData;
import com.if5a.tulisaja.models.ValueNoData;
import com.if5a.tulisaja.models.post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("loginUser")
    @FormUrlEncoded
    Call<ValueNoData> login(@Field("key") String key,
                            @Field("username") String username,
                            @Field("password") String password);

    @POST("registerUser")
    @FormUrlEncoded
    Call<ValueNoData> register(@Field("key") String key,
                            @Field("username") String username,
                            @Field("password") String password);

    @POST("getAllPost")
    @FormUrlEncoded
    Call<ValueData<post>> getAll(@Field("key") String key);

    @POST("insertPost")
    @FormUrlEncoded
    Call<ValueNoData> insert(@Field("key") String key,
                            @Field("username") String username,
                            @Field("password") String password);

    @POST("updatePost")
    @FormUrlEncoded
    Call<ValueNoData> update(@Field("key") String key,
                            @Field("id") int id,
                            @Field("content") String content);

    @POST("deletePost")
    @FormUrlEncoded
    Call<ValueNoData> delete(@Field("key") String key,
                            @Field("id") int id);
}
