package com.abhishek.retrofit_demo;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.POST;

public class Post {


    private int userId;

    private int id;

    private String title;

    @SerializedName("body")
    //our text is refered as json body
    private String text;

    //getter
    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId()
    {
        return id;

    }

    public String getTitle()
    {
        return title;

    }

    public String getText()
    {
        return text;
    }




}
