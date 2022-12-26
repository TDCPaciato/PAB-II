package com.if5b.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Post implements Parcelable {
    private String id, content, username;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("created_date")
    private String createdDate;
    @SerializedName("modified_date")
    private String modifiedDate;

    protected Post(Parcel in) {
        id = in.readString();
        content = in.readString();
        username = in.readString();
        userId = in.readString();
        createdDate = in.readString();
        modifiedDate = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(content);
        parcel.writeString(username);
        parcel.writeString(userId);
        parcel.writeString(createdDate);
        parcel.writeString(modifiedDate);
    }
}
