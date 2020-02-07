package com.abhishek.retrofit_demo;

import android.content.Intent;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {

    @GET("posts")
    //this call object  encapsulates single request and response
    Call<List<Post>> getdata
    (

            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getdata (@QueryMap Map<String,String> parameters);



    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postID);


}
