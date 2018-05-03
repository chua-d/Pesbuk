package com.example.danceciliochua.pesbuk.API;

import com.example.danceciliochua.pesbuk.Data.Albums;
import com.example.danceciliochua.pesbuk.Data.Comments;
import com.example.danceciliochua.pesbuk.Data.Photos;
import com.example.danceciliochua.pesbuk.Data.Posts;
import com.example.danceciliochua.pesbuk.Data.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIClient {

    @GET("/users")
    Call<List<Users>> listAddress();

    @GET("/users")
    Call<List<Users>> listUsers();

    @GET("/users")
    Call<List<Users>> profile(@Query("id") Integer id);

    @GET("/users/{user}/posts")
    Call<List<Posts>> posts(@Path("user") Integer id);

    @GET("posts/{post}/comments")
    Call<List<Comments>> comments(@Path("post") Integer id);

    @GET("users/{user}/albums")
    Call<List<Albums>> listAlbums(@Path("user") Integer id);

    @GET("/albums/{album}/photos?_sort=id&_order=desc&limit=1")
    Call<List<Photos>> albumThum(@Path("album") Integer id);

    @GET("/albums/{album}/photos")
    Call<List<Photos>> listPhotos(@Path("album") Integer id);

    @GET("/photos")
    Call<List<Photos>> viewPhoto(@Query("id") Integer id);

}

