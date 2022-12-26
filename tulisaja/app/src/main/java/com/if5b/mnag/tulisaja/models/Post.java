package com.if5b.mnag.tulisaja.models;

import com.google.gson.annotations.SerializedName;

public class Post {
    private  String id;
    private  String content;

    @SerializedName("user_id")
    private  String userId;

    @SerializedName("created_date")
    private  String createdDate;

    @SerializedName("modified_date")
    private  String modifiedDate;

    private  String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("user_id")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
