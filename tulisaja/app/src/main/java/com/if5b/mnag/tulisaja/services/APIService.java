package com.if5b.mnag.tulisaja.services;

import com.if5b.mnag.tulisaja.models.Post;
import com.if5b.mnag.tulisaja.models.ValueData;
import com.if5b.mnag.tulisaja.models.ValueNoData;

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
    Call<ValueData<Post>> getAllPost(@Field("key") String key);

    @POST("insertPost")
    @FormUrlEncoded
    Call<ValueNoData> insert(@Field("key") String key,
                            @Field("username") String username,
                            @Field("content") String content);

    @POST("updatePost")
    @FormUrlEncoded
    Call<ValueNoData> updatePost(@Field("key") String key,
                            @Field("id") int id,
                            @Field("content") String content);

    @POST("deletePost")
    @FormUrlEncoded
    Call<ValueNoData> login(@Field("key") String key,
                            @Field("id") int id);
}
